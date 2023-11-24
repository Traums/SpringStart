package ru.mart.Practice.Scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class BusinessServiceImpl implements BusinessService {
    @Override
    public void someMethod() {
        log.warn("Фоновое выполнение процесса");
    }
}
