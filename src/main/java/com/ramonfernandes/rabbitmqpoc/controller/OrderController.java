package com.ramonfernandes.rabbitmqpoc.controller;

import com.ramonfernandes.rabbitmqpoc.messaging.Sender;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

  Sender sender;

  public OrderController(Sender sender) {
    this.sender = sender;
  }

  @PostMapping("/send")
  public void sendMessage() {
    sender.send("Ramon Order");
  }

}
