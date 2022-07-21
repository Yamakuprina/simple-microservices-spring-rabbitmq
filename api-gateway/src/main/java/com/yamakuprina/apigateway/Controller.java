package com.yamakuprina.apigateway;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class Controller {

    private final AmqpTemplate template;

    public Controller(@Autowired AmqpTemplate template) {
        this.template = template;
    }

    @PostMapping("/send-first")
    public CustomMessage sendMessageFirst(@RequestBody CustomMessage customMessage){
        customMessage.setMessageId(UUID.randomUUID().toString());
        customMessage.setMessageDate(new Date());
        return template.convertSendAndReceiveAsType(
                MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY_FIRST,
                customMessage,
                ParameterizedTypeReference.forType(CustomMessage.class)
        );
    }

    @PostMapping("/send-second")
    public CustomMessage sendMessageSecond(@RequestBody CustomMessage customMessage){
        customMessage.setMessageId(UUID.randomUUID().toString());
        customMessage.setMessageDate(new Date());
        return template.convertSendAndReceiveAsType(
                MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY_SECOND,
                customMessage,
                ParameterizedTypeReference.forType(CustomMessage.class)
        );
    }
}
