package com.example.appserver.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.sun.istack.NotNull;

@Entity
@Table(name = "app_data_message")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DataMessage {
  private Long id;
  private String content;

  public DataMessage() {}

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  public Long getId() {
    return id;
  }

  @NotNull
  @Column(name = "content", length = 100, nullable = false)
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
