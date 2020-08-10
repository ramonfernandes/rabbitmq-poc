package com.ramonfernandes.rabbitmqpoc.controller;

import com.ramonfernandes.rabbitmqpoc.dto.Character;
import com.ramonfernandes.rabbitmqpoc.dto.CharacterClass;
import com.ramonfernandes.rabbitmqpoc.dto.Race;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

  Listener listener;

  public OrderController(Listener listener) {
    this.listener = listener;
  }

  @PostMapping("/send")
  public void sendMessage() throws IOException {
    listener.sendCharacter(new Character(Race.TIEFLING, CharacterClass.BARD));
    listener.sendCharacter(new Character(Race.HALFLING, CharacterClass.MAGE));
    listener.sendCharacter(new Character(Race.HUMAN, CharacterClass.FIGHTER));
  }

}
