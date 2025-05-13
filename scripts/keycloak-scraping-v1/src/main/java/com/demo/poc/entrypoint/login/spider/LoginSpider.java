package com.demo.poc.entrypoint.login.spider;

import com.demo.poc.commons.helper.SleepHelper;
import com.demo.poc.commons.logging.Logger;
import com.google.inject.Inject;
import com.demo.poc.commons.properties.PropertiesReader;
import com.demo.poc.commons.properties.configuration.Configuration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class LoginSpider {

  private final PropertiesReader propertiesReader;
  private final SleepHelper sleepHelper;

  public void login(ChromeDriver driver) {
    Logger.startLog.accept(this.getClass().getSimpleName());

    Configuration properties = propertiesReader.get();

    driver.get(properties.getLogin().getUri());
    sleepHelper.basicSleep();

    WebElement adminConsoleButton = driver.findElement(By.linkText("Administration Console"));
    adminConsoleButton.click();
    sleepHelper.basicSleep();

    WebElement usernameField = driver.findElement(By.id("username"));
    usernameField.sendKeys(properties.getLogin().getUsername());

    WebElement passwordField = driver.findElement(By.id("password"));
    passwordField.sendKeys(properties.getLogin().getPassword());

    WebElement signInButton = driver.findElement(By.id("kc-login"));
    signInButton.click();

    Logger.endLog.accept(this.getClass().getSimpleName());
  }
}