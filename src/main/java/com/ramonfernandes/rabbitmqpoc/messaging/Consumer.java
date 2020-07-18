package com.ramonfernandes.rabbitmqpoc.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

  @RabbitListener(queues = {"${queue.order.name}"})
  public void receive(@Payload String filebody) {
    System.out.println(filebody);
  }

}
