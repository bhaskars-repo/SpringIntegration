/*
 * Name:   CarServiceConfig2
 * Author: Bhaskar S
 * Date:   05/22/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.handler.MessageHandlerChain;
import org.springframework.integration.handler.MethodInvokingMessageProcessor;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.integration.stream.CharacterStreamWritingMessageHandler;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.MessageTransformingHandler;
import org.springframework.integration.transformer.support.ExpressionEvaluatingHeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableIntegration
public class CarServiceConfig2 {
    private RefNoHandler2 refNoHandler2;
    private CarServiceHandler2 carServiceHandler2;

    @Autowired
    public void setRefNoHandler2(RefNoHandler2 refNoHandler2) {
        this.refNoHandler2 = refNoHandler2;
    }

    @Autowired
    public void setCarServiceHandler2(CarServiceHandler2 carServiceHandler2) {
        this.carServiceHandler2 = carServiceHandler2;
    }

    @Bean
    public MessageChannel inChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel outChannel() {
        return new DirectChannel();
    }

    /* --- BEGIN: For RefNoHandler2 --- */
    @Bean
    public MethodInvokingMessageProcessor<?> refNoMessageHandler2() {
        return new MethodInvokingMessageProcessor<>(refNoHandler2, "assignRefNo");
    }

    @Bean
    public MessageHandler refNoEndpoint2() {
        return new ServiceActivatingHandler(refNoMessageHandler2());
    }
    /* --- END: For RefNoHandler2 --- */

    /* --- BEGIN: For Header Enricher --- */
    @Bean
    public HeaderEnricher carMakeHeader() {
        Map<String, HeaderValueMessageProcessor<?>> headers = new HashMap<>();
        Expression expression = new SpelExpressionParser().parseExpression("payload.getMake()");
        headers.put("CAR_MAKE", new ExpressionEvaluatingHeaderValueMessageProcessor<>(expression, String.class));
        return new HeaderEnricher(headers);
    }

    @Bean
    public MessageHandler carMakeTransformer() {
        return new MessageTransformingHandler(carMakeHeader());
    }
    /* --- END: For Header Enricher --- */

    /* --- BEGIN: For CarServiceHandler2 --- */
    @Bean
    public MethodInvokingMessageProcessor<?> carServiceMessageHandler2() {
        return new MethodInvokingMessageProcessor<>(carServiceHandler2, "recommendedServices");
    }

    @Bean
    public MessageHandler carServiceEndpoint2() {
        return new ServiceActivatingHandler(carServiceMessageHandler2());
    }
    /* --- END: For CarServiceHandler2 --- */

    @Bean
    @ServiceActivator(inputChannel = "inChannel")
    public MessageHandler chainEndpoints() {
        MessageHandlerChain chain = new MessageHandlerChain();
        chain.setHandlers(Arrays.asList(refNoEndpoint2(),
                carMakeTransformer(),
                carServiceEndpoint2()));
        chain.setOutputChannel(outChannel());
        return chain;
    }

    @Bean
    @ServiceActivator(inputChannel = "outChannel")
    public MessageHandler stdoutAdapter() {
        CharacterStreamWritingMessageHandler handler = CharacterStreamWritingMessageHandler.stdout();
        handler.setShouldAppendNewLine(true);
        return handler;
    }
}
