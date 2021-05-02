package com.example.appserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.appserver.rest.model.DataMessage;

public interface DataMessageRepository extends JpaRepository<DataMessage, Long> {
}
