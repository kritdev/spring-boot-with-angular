package com.example.appserver.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.appserver.repository.DataMessageRepository;
import com.example.appserver.rest.model.DataMessage;

@RestController
@RequestMapping("/api")
public class SimpleRestController {

  private final Logger log = LoggerFactory.getLogger(SimpleRestController.class);

  private final DataMessageRepository dataMessageRepository;

  public SimpleRestController(DataMessageRepository dataMessageRepository) {
    this.dataMessageRepository = dataMessageRepository;
  }

  @GetMapping("/message")
  public List<DataMessage> getAllDataMessage() {
    log.debug("REST request to get all DataMessage");
    return dataMessageRepository.findAll();
  }

  @GetMapping("/message/{id}")
  public ResponseEntity<DataMessage> getDataMessage(@PathVariable Long id) {
    log.debug("REST request to get DataMessage : {}", id);
    Optional<DataMessage> result = dataMessageRepository.findById(id);
    return AppResponseUtil.wrapOrNotFound(result);
  }

  @PostMapping("/message")
  public ResponseEntity<DataMessage> createDataMessage(@Valid @RequestBody DataMessage dataMessage)
      throws URISyntaxException {
    log.debug("REST request to save DataMessage : {}", dataMessage);

    if (dataMessage.getId() != null) {
      log.debug("- A new data must not have an ID : {}", dataMessage);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A new data must not have an ID");
    }
    DataMessage result = dataMessageRepository.save(dataMessage);
    return AppResponseUtil.wrapOrNotFound(result);
  }

  @PutMapping("/message")
  public ResponseEntity<DataMessage> updateDataMessage(@Valid @RequestBody DataMessage dataMessage)
      throws URISyntaxException {
    log.debug("REST request to update DataMessage : {}", dataMessage);
    if (dataMessage.getId() == null) {
      log.debug("- The updated data must have an ID : {}", dataMessage);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The updated data must have an ID");
    }
    DataMessage result = dataMessageRepository.save(dataMessage);
    return ResponseEntity.ok().body(result);
  }

  @DeleteMapping("/message/{id}")
  public ResponseEntity<Void> deleteDataMessage(@PathVariable Long id) {
    log.debug("REST request to delete DataMessage : {}", id);
    try {
      dataMessageRepository.deleteById(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error in delete DataMessage", e);
    }

    return ResponseEntity.ok().build();
  }
}
