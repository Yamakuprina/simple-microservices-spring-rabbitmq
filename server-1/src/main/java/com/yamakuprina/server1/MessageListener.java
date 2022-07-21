package com.yamakuprina.server1;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.QUEUE)
    public CustomMessage customMessage(CustomMessage customMessage){
        String oldMessage = customMessage.getMessage();
        customMessage.setMessage(oldMessage + " Server-1 is working.");
        return customMessage;
    }
}
