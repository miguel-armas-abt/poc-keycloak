package com.demo.poc.commons.service;

import java.util.Set;

import com.demo.poc.commons.enums.OperationType;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OperationSelectorService {

  private final Set<OperationService> operationServices;

  @Inject
  public OperationSelectorService(Set<OperationService> operationServices) {
    this.operationServices = operationServices;
  }

  public OperationService selectService(OperationType operationType) {
    return operationServices.stream()
        .filter(service -> service.supports(operationType))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("no such operation service for: " + operationType));
  }
}