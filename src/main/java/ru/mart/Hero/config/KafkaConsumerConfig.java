package ru.mart.Hero.config;


import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.CommonLoggingErrorHandler;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.mart.Hero.dto.HeroDTO;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@RequiredArgsConstructor
@Scope("singleton")
public class KafkaConsumerConfig {
    @Autowired
    KafkaCommonConfig commonConfig;
    @Bean
    public CommonLoggingErrorHandler defaultErrorHandler(){
        return new CommonLoggingErrorHandler();
    }
    public Map<String,Object> consumerConfig(String method){
        final Map<String,Object> config = new HashMap<>(commonConfig.consumerConfig());
        config.put(ConsumerConfig.GROUP_ID_CONFIG,commonConfig.getGroupId()+ "_" +
                commonConfig.getUniqueGroupPrefix() + "_ "+ method);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        return config;
    }
    public ConsumerFactory<String,String> defaultStringConsumerFactory(){
        return new DefaultKafkaConsumerFactory<>(commonConfig.consumerConfig(),
                                                                new StringDeserializer(),
                                                                new StringDeserializer());
    }
    private <T> ConsumerFactory<String,T> consumerFactory(Class<T> tClass,Map<String,Object> props){
        ErrorHandlingDeserializer<T> errorHandlingDeserializer = new ErrorHandlingDeserializer<>(
                new JsonDeserializer<>( tClass, commonConfig.getMapper(),false ));
        return new DefaultKafkaConsumerFactory<>(props,new StringDeserializer(),errorHandlingDeserializer);
    }
    private <T>ConcurrentKafkaListenerContainerFactory<String,T> commonContainerFactory(ConsumerFactory<String,T> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String,T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setPollTimeout(2000);
        return factory;
    }
    private <T>ConcurrentKafkaListenerContainerFactory<String,T> concurrentContainerFactory(ConsumerFactory<String,T> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String,T> factory = commonContainerFactory(consumerFactory);
        factory.setConcurrency(4);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
//        factory.setErrorHandler();
        factory.getContainerProperties().setSyncCommits(true);
        return factory;
    }
    private <T> ConcurrentKafkaListenerContainerFactory<String,T> concurrentBatchContainerFactory(ConsumerFactory<String,T> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String,T> factory = commonContainerFactory(consumerFactory);
        factory.setConcurrency(1);
        factory.setBatchListener(true);
        return factory;
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> defaultStringContainerFactory(){
        return concurrentBatchContainerFactory(defaultStringConsumerFactory());
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, HeroDTO> getCreateHeroContainerFactory(){
        return concurrentContainerFactory(consumerFactory(HeroDTO.class,consumerConfig("getCreateNewHero")));
    }


}
