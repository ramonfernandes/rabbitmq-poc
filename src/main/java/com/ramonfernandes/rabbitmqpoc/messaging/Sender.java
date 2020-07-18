package com.ramonfernandes.rabbitmqpoc.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

  @Autowired
  private RabbitTemplate template;

  @Autowired
  private Queue queue;

  public void send(String order) {
    template.convertAndSend(this.queue.getName(), order);
  }

}
