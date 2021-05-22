/*
 * Name:   CarServiceConfig5
 * Author: Bhaskar S
 * Date:   05/22/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p8;

import com.polarsparc.si.p6.CarServiceHandler2;
import com.polarsparc.si.p6.RefNoHandler2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.handler.MethodInvokingMessageProcessor;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.integration.stream.CharacterStreamWritingMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@EnableIntegration
public class CarServiceConfig5 {
    private RefNoHandler2 refNoHandler2;
    private CarInspectionHandler2 carInspectionHandler2;
    private CarServiceHandler2 carServiceHandler2;

    @Autowired
    public void setRefNoHandler2(RefNoHandler2 refNoHandler2) {
        this.refNoHandler2 = refNoHandler2;
    }

    @Autowired
    public void setCarInspectionHandler2(CarInspectionHandler2 carInspectionHandler2) {
        this.carInspectionHandler2 = carInspectionHandler2;
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
    public MessageChannel splitChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel routerChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel inspChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel recoChannel() {
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
    @ServiceActivator(inputChannel = "inChannel")
    public MessageHandler refNoEndpoint2() {
        ServiceActivatingHandler handler = new ServiceActivatingHandler(refNoMessageHandler2());
        handler.setOutputChannel(splitChannel());
        return handler;
    }
    /* --- END: For RefNoHandler2 --- */

    /* --- BEGIN: For CarInspectionHandler2 --- */
    @Bean
    public MethodInvokingMessageProcessor<?> carInspectionMessageHandler2() {
        return new MethodInvokingMessageProcessor<>(carInspectionHandler2, "inspectionServices");
    }

    @Bean
    @ServiceActivator(inputChannel = "inspChannel")
    public MessageHandler carInspectionEndpoint2() {
        ServiceActivatingHandler handler = new ServiceActivatingHandler(carInspectionMessageHandler2());
        handler.setOutputChannel(outChannel());
        return handler;
    }
    /* --- END: For CarInspectionHandler2 --- */

    /* --- BEGIN: For CarServiceHandler2 --- */
    @Bean
    public MethodInvokingMessageProcessor<?> carServiceMessageHandler2() {
        return new MethodInvokingMessageProcessor<>(carServiceHandler2, "recommendedServices");
    }

    @Bean
    @ServiceActivator(inputChannel = "recoChannel")
    public MessageHandler carServiceEndpoint2() {
        ServiceActivatingHandler handler = new ServiceActivatingHandler(carServiceMessageHandler2());
        handler.setOutputChannel(outChannel());
        return handler;
    }
    /* --- END: For CarServiceHandler2 --- */

    @Bean
    @ServiceActivator(inputChannel = "routerChannel")
    public HeaderValueRouter router() {
        HeaderValueRouter router = new HeaderValueRouter("SERVICE_TYPE");
        router.setChannelMapping("INSP", "inspChannel");
        router.setChannelMapping("RECO", "recoChannel");
        return router;
    }

    @Bean
    @ServiceActivator(inputChannel = "outChannel")
    public MessageHandler stdoutAdapter() {
        CharacterStreamWritingMessageHandler handler = CharacterStreamWritingMessageHandler.stdout();
        handler.setShouldAppendNewLine(true);
        return handler;
    }
}
