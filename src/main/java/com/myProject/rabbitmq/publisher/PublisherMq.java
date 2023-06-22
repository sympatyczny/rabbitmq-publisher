package com.myProject.rabbitmq.publisher;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
public class PublisherMq {

    @Value("${user}")
    private String user;
    @Value("${env}")
    private String env;
    @Value("${queueName}")
    private String queueName;
    private RabbitTemplate rabbitTemplate;
    private AmqpAdmin amqpAdmin;
    private QueueConfig queueConfig;

    @Autowired
    public PublisherMq(RabbitTemplate rabbitTemplate, AmqpAdmin amqpAdmin, QueueConfig queueConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.amqpAdmin = amqpAdmin;
        this.queueConfig = queueConfig;
    }

    public PublisherMq() {
    }

    @ApiOperation(value="send new message", notes = "here you can send a new message in param")
    @GetMapping("/addMessage")
    public String addMassage(@ApiParam(value = "your message to send:", example = "test message") @RequestParam String message){

        Properties properties = amqpAdmin.getQueueProperties(queueName);
        if ( properties == null) queueConfig.createQueues();

        rabbitTemplate.convertAndSend(queueName, message);
       return "sent: '" + message + "', from user: '" + user +"'" + "//env:" + env;
    }

}

