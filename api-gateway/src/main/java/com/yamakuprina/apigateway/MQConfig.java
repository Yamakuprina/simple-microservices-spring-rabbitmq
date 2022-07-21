package com.yamakuprina.apigateway;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String QUEUE_FIRST ="message_queue_first";
    public static final String QUEUE_SECOND ="message_queue_second";
    public static final String EXCHANGE ="message_exchange";
    public static final String ROUTING_KEY_FIRST ="message_routingKey_first";
    public static final String ROUTING_KEY_SECOND ="message_routingKey_second";

    @Bean
    public Queue queueFirst(){
        return new Queue(QUEUE_FIRST);
    }

    @Bean
    public Queue queueSecond(){
        return new Queue(QUEUE_SECOND);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingFirst(Queue queueFirst, TopicExchange topicExchange){
        return BindingBuilder.bind(queueFirst).to(topicExchange).with(ROUTING_KEY_FIRST);
    }

    @Bean
    public Binding bindingSecond(Queue queueSecond, TopicExchange topicExchange){
        return BindingBuilder.bind(queueSecond).to(topicExchange).with(ROUTING_KEY_SECOND);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setPort(5672);
        return connectionFactory;
    }
}
