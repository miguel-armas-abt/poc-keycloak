package com.demo.poc.commons.service;

import com.demo.poc.commons.enums.OperationType;
import org.openqa.selenium.chrome.ChromeDriver;

public interface OperationService {

  void execute(ChromeDriver driver);

  boolean supports(OperationType operation);
}
