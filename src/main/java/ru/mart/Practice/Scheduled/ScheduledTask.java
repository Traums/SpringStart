package ru.mart.Practice.Scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class ScheduledTask {
    @Autowired
    public BusinessService businessService;
    //4.11.6 @Scheduled  расказать про использование и параметры
    @Scheduled(fixedRate = 150)
    public void doTask(){
        businessService.someMethod();
    }
}
