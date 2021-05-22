/*
 * Name:   SpotPricesConfig
 * Author: Bhaskar S
 * Date:   05/08/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.AbstractMessageChannel;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.channel.interceptor.WireTap;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableIntegration
public class SpotPricesConfig {
    @Bean
    @ServiceActivator(inputChannel = "logger")
    public LoggingHandler logHandler() {
        LoggingHandler logger = new LoggingHandler(LoggingHandler.Level.INFO);
        logger.setShouldLogFullMessage(true);
        return logger;
    }

    @Bean
    public MessageChannel inChannel() {
        AbstractMessageChannel channel = new DirectChannel();
        channel.addInterceptor(wireTap());
        return channel;
    }

    @Bean
    public WireTap wireTap() {
        return new WireTap("logger");
    }

    @Bean
    public MessageChannel outChannel() {
        return new QueueChannel(5);
    }

    @Bean
    @ServiceActivator(inputChannel = "inChannel")
    public MessageHandler httpGateway() {
        HttpRequestExecutingMessageHandler handler =
                new HttpRequestExecutingMessageHandler("http://services.packetizer.com/spotprices/?f={f}");
        handler.setHttpMethod(HttpMethod.GET);
        handler.setExpectedResponseType(String.class);
        handler.setSendTimeout(10000);
        handler.setOutputChannel(outChannel());

        ExpressionParser parser = new SpelExpressionParser();
        Map<String, Expression> variables = new HashMap<>();
        variables.put("f", parser.parseExpression("headers.f"));

        handler.setUriVariableExpressions(variables);
        return handler;
    }
}
