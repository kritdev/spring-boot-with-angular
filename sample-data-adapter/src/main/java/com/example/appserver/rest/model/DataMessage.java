package com.example.appserver.rest.model;

public class DataMessage {
  private Long id;
  private String content;

  public DataMessage() {}

  public Long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "DataMessage{" + "id=" + getId() + ", content='" + getContent() + "'}";
  }
}
