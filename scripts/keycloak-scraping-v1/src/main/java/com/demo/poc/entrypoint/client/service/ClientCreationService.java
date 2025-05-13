package com.demo.poc.entrypoint.client.service;

import com.demo.poc.commons.enums.OperationType;
import com.demo.poc.commons.logging.Logger;
import com.demo.poc.commons.service.OperationService;
import com.demo.poc.entrypoint.client.spider.ClientSpider;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ClientCreationService implements OperationService {

  private static final OperationType OPERATION = OperationType.CLIENT_CREATION;

  private final ClientSpider clientSpider;

  @Override
  public void execute(ChromeDriver driver) {
    Logger.operationLog.accept(OPERATION);
    clientSpider.configClient(driver);
  }

  @Override
  public boolean supports(OperationType operation) {
    return OPERATION.equals(operation);
  }
}
