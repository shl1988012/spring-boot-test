package com.spring.test.Sync.pool;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class SpringPool {

    @Bean
    public ThreadPoolTaskExecutor createPool(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.initialize();
        executor.setCorePoolSize(NumberUtils.toInt("16"));
        executor.setMaxPoolSize(NumberUtils.toInt("16"));
        executor.setQueueCapacity(NumberUtils.toInt("1024"));
        executor.setThreadNamePrefix("spring-test-");
        return executor;
    }

}
