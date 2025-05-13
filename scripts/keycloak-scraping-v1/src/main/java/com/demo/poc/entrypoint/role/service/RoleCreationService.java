package com.demo.poc.entrypoint.role.service;

import com.demo.poc.commons.enums.OperationType;
import com.demo.poc.commons.logging.Logger;
import com.demo.poc.commons.service.OperationService;
import com.demo.poc.entrypoint.role.spider.RoleSpider;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class RoleCreationService implements OperationService {

  private static final OperationType OPERATION = OperationType.ROLE_CREATION;

  private final RoleSpider roleSpider;

  @Override
  public void execute(ChromeDriver driver) {
    Logger.operationLog.accept(OPERATION);
    roleSpider.configRole(driver);
  }

  @Override
  public boolean supports(OperationType operation) {
    return OPERATION.equals(operation);
  }
}
