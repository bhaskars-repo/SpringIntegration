/*
 * Name:   StrUpCase4Config
 * Author: Bhaskar S
 * Date:   04/24/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableIntegration
public class StrUpCase4Config {
    @Bean
    public MessageChannel inChannel() {
        return new ExecutorChannel(threadPoolTaskExecutor());
    }

    @Bean
    public MessageChannel outChannel() {
        return new QueueChannel(5);
    }

    @Bean(name = "executor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setQueueCapacity(5);
        executor.setCorePoolSize(3);
        executor.setBeanName("executor");
        return executor;
    }
}
