package com.yamakuprina.server1;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.QUEUE)
    public CustomMessage customMessage(CustomMessage customMessage){
        customMessage.setMessage("Listener 1 works");
        return customMessage;
    }
}
