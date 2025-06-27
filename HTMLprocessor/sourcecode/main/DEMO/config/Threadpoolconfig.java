package com.ritesh.demo.htmlfilewoker.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreaPoolConifg {

    @Bean(name="urlProcessor")
    public Executor urlExecutorBean(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("url-processor-executor-");
        executor.initialize();
        
        return executor;
    }
}
