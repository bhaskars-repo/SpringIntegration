/*
 * Name:   KafkaProcessConfig
 * Author: Bhaskar S
 * Date:   05/15/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p5;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.messaging.MessageChannel;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableIntegration
@PropertySource("classpath:p5/kafka.properties")
public class KafkaProcessConfig {
    @Value("${bootstrap.servers}")
    private String bootstrapServer;

    @Value("${key.deserializer}")
    private String keyDeserializer;

    @Value("${value.deserializer}")
    private String valueDeserializer;

    @Value("${group.id}")
    private String groupId;

    @Value("${auto.offset.reset}")
    private String autoOffsetReset;

    @Value("${enable.auto.commit}")
    private String enableAutoCommit;

    @Bean
    public MessageChannel inKafkaChannel() {
        return new DirectChannel();
    }

    @Bean
    public ConsumerFactory<String, String> kafkaConsumerFactory() {
        Map<String, Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        map.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        map.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        return new DefaultKafkaConsumerFactory<>(map);
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> kafkaContainer() {
        ContainerProperties properties = new ContainerProperties("si_topic");
        return new KafkaMessageListenerContainer<>(kafkaConsumerFactory(), properties);
    }

    @Bean
    public KafkaMessageDrivenChannelAdapter<String, String> kafkaListener() {
        KafkaMessageDrivenChannelAdapter<String, String> adapter =
                new KafkaMessageDrivenChannelAdapter<>(kafkaContainer(),
                        KafkaMessageDrivenChannelAdapter.ListenerMode.record);
        adapter.setOutputChannel(inKafkaChannel());
        return adapter;
    }
}
