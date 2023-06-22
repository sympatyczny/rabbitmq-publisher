package com.myProject.rabbitmq.publisher;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueConfig {

    @Value("${queueName}")
    private String queueName;

    @Autowired
    private AmqpAdmin amqpAdmin;

    public QueueConfig() {
    }

    public QueueConfig(String queueName, AmqpAdmin amqpAdmin) {
        this.queueName = queueName;
        this.amqpAdmin = amqpAdmin;
    }

    public void createQueues() {
        amqpAdmin.declareQueue(new Queue(queueName, true));
    }

}
