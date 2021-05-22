/*
 * Name:   StrUpCase3Config
 * Author: Bhaskar S
 * Date:   04/24/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.AbstractMessageChannel;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.channel.interceptor.WireTap;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class StrUpCase3Config {
    @Bean
    public MessageChannel inChannel() {
        AbstractMessageChannel channel = new DirectChannel();
        channel.addInterceptor(wireTapOne());
        return channel;
    }

    @Bean
    public MessageChannel outChannel() {
        AbstractMessageChannel channel = new QueueChannel(5);
        channel.addInterceptor(wireTapTwo());
        return channel;
    }

    @Bean
    @ServiceActivator(inputChannel = "logger1")
    public LoggingHandler loggerOne() {
        return new LoggingHandler(LoggingHandler.Level.INFO);
    }

    @Bean
    @ServiceActivator(inputChannel = "logger2")
    public LoggingHandler loggerTwo() {
        LoggingHandler logger = new LoggingHandler(LoggingHandler.Level.INFO);
        logger.setShouldLogFullMessage(true);
        return logger;
    }

    @Bean
    public WireTap wireTapOne() {
        return new WireTap("logger1");
    }

    @Bean
    public WireTap wireTapTwo() {
        return new WireTap("logger2");
    }
}
