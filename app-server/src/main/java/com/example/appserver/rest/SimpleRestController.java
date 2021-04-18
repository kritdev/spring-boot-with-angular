package com.example.appserver.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.appserver.rest.model.DataMessage;

@RestController
public class SimpleRestController {

  @RequestMapping("/api/message")
  DataMessage home() {
    return new DataMessage();
  }
}
