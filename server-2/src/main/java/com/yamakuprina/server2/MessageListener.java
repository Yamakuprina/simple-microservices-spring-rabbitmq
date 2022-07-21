package com.yamakuprina.server2;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.QUEUE)
    public CustomMessage customMessage(CustomMessage customMessage){
        customMessage.setMessage("Listener 2 works");
        return customMessage;
    }
}
