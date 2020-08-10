package com.ramonfernandes.rabbitmqpoc.controller;

import static com.ramonfernandes.rabbitmqpoc.QueueProperties.CHARACTER_EXCHANGE;
import static com.ramonfernandes.rabbitmqpoc.QueueProperties.CHARACTER_HALFLING_QUEUE;
import static com.ramonfernandes.rabbitmqpoc.QueueProperties.CHARACTER_TIEFLNG_QUEUE;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.ramonfernandes.rabbitmqpoc.dto.Character;
import com.ramonfernandes.rabbitmqpoc.dto.Race;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Listener {

  private ConnectionFactory factory;
  private Channel channel;

  public Listener() {
    try {
      factory = new ConnectionFactory();
      channel = factory.newConnection().createChannel();
      channel.exchangeDeclare(CHARACTER_EXCHANGE, "headers");
      declareQueue(CHARACTER_TIEFLNG_QUEUE);
      declareQueue(CHARACTER_HALFLING_QUEUE);
      channel.queueBind(CHARACTER_TIEFLNG_QUEUE, CHARACTER_EXCHANGE, String.valueOf(Race.TIEFLING));
      channel.queueBind(CHARACTER_HALFLING_QUEUE, CHARACTER_EXCHANGE, String.valueOf(Race.HALFLING));
    } catch (IOException | TimeoutException e) {
      e.printStackTrace();
    }
  }

  private void declareQueue(String queueName) throws IOException {
    channel.queueDeclare(queueName, true, false, false, new HashMap<>());
  }

  public void sendCharacter(Character character) throws IOException {
    channel.basicPublish(CHARACTER_EXCHANGE,
        character.getRace().toString(),
        new BasicProperties(),
        character.toString().getBytes());
  }

  @PostConstruct
  public void onMessage() throws IOException {
    channel.basicConsume(CHARACTER_TIEFLNG_QUEUE, true,
        getDeliverCallback("RECEIVED A TIEFLING CHARACTER"), consumerTag -> {
        });
    channel.basicConsume(CHARACTER_HALFLING_QUEUE, true,
        getDeliverCallback("RECEIVED A HALFLING CHARACTER"), consumerTag -> {
        });
  }

  private DeliverCallback getDeliverCallback(String logMessage) {
    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String message = new String(delivery.getBody(), "UTF-8");
      System.out.println(logMessage);
      System.out.println(message);
    };
    return deliverCallback;
  }
}
