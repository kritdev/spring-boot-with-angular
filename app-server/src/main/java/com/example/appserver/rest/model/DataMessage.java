package com.example.appserver.rest.model;

public class DataMessage {
  private final long id;
  private final String content;

  public DataMessage() {
    this.id = -1;
    this.content = "This is DataMessage";
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

}
