package com.demo.poc.entrypoint.realm.service;

import com.demo.poc.commons.enums.OperationType;
import com.demo.poc.commons.logging.Logger;
import com.demo.poc.commons.service.OperationService;
import com.demo.poc.entrypoint.realm.spider.RealmConfigSpider;
import com.demo.poc.entrypoint.realm.spider.RealmCreatorSpider;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class RealmCreationService implements OperationService {

  private static final OperationType OPERATION = OperationType.REALM_CREATION;

  private final RealmCreatorSpider realmCreatorSpider;
  private final RealmConfigSpider realmConfigSpider;

  @Override
  public void execute(ChromeDriver driver) {
    Logger.operationLog.accept(OPERATION);
    realmCreatorSpider.createRealm(driver);
    realmConfigSpider.configRealm(driver);
  }

  @Override
  public boolean supports(OperationType operation) {
    return OPERATION.equals(operation);
  }
}
