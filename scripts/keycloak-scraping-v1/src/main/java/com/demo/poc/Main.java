package com.demo.poc;

import com.demo.poc.commons.enums.OperationType;
import com.demo.poc.commons.helper.DriverHelper;
import com.demo.poc.commons.service.OperationSelectorService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.demo.poc.commons.config.ComponentsConfig;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new ComponentsConfig());

    DriverHelper driverHelper = injector.getInstance(DriverHelper.class);
    ChromeDriver driver = driverHelper.openBrowser();

    OperationSelectorService selectorService = injector.getInstance(OperationSelectorService.class);
    selectorService.selectService(OperationType.LOGIN).execute(driver);
    selectorService.selectService(OperationType.REALM_CREATION).execute(driver);
    selectorService.selectService(OperationType.USER_CREATION).execute(driver);
    selectorService.selectService(OperationType.ROLE_CREATION).execute(driver);
    selectorService.selectService(OperationType.ROLE_ASSIGNMENT).execute(driver);
    selectorService.selectService(OperationType.CLIENT_CREATION).execute(driver);
  }

}