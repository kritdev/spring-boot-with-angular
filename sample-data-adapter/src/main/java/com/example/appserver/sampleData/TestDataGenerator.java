package com.example.appserver.sampleData;

import com.example.appserver.rest.model.DataMessage;

public class TestDataGenerator {
  public static DataMessage[] generateDataMessage() {
    int dataCount = 10;
    DataMessage[] resultList = new DataMessage[dataCount];

    for (int i = 0; i < dataCount; i++) {
      DataMessage dataMessage = new DataMessage();
      dataMessage.setId((long) i);
      dataMessage.setContent("Test Data : " + i);

      resultList[i] = dataMessage;
    }

    return resultList;
  }
}
