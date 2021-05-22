/*
 * Name:   AmqpProcessConfig
 * Author: Bhaskar S
 * Date:   05/15/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p5;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.amqp.inbound.AmqpInboundChannelAdapter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
@PropertySource("classpath:p5/amqp.properties")
public class AmqpProcessConfig {
    @Value("${amqp.host}")
    private String amqpHost;

    @Value("${amqp.port}")
    private int amqpPort;

    @Value("${amqp.username}")
    private String amqpUsername;

    @Value("${amqp.password}")
    private String amqpPassword;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory("localhost");
        factory.setHost(amqpHost);
        factory.setPort(amqpPort);
        factory.setUsername(amqpUsername);
        factory.setPassword(amqpPassword);
        return factory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    DirectExchange amqpDirectExchange() {
        return new DirectExchange("si_exchange");
    }

    @Bean
    Binding amqpBinding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("si_queue");
    }

    @Bean
    public SimpleMessageListenerContainer amqpMessageListenerContainer(ConnectionFactory factory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
        container.setQueueNames("si_queue");
        return container;
    }

    @Bean
    public AmqpInboundChannelAdapter amqpInboundChannelAdapter(SimpleMessageListenerContainer container,
                                                               @Qualifier("amqpMsgsChannel") MessageChannel channel) {
        AmqpInboundChannelAdapter adapter = new AmqpInboundChannelAdapter(container);
        adapter.setOutputChannel(channel);
        return adapter;
    }

    @Bean
    public Queue amqpQueue() {
        return new Queue("si_queue");
    }

    @Bean
    public RabbitTemplate amqpTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public MessageChannel amqpMsgsChannel() {
        return new DirectChannel();
    }
}
