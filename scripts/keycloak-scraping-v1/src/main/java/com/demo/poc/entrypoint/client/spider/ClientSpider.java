package com.demo.poc.entrypoint.client.spider;

import com.demo.poc.commons.helper.SleepHelper;
import com.demo.poc.commons.logging.Logger;
import com.google.inject.Inject;
import com.demo.poc.commons.helper.DriverHelper;
import com.demo.poc.commons.properties.PropertiesReader;
import com.demo.poc.commons.properties.configuration.Configuration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ClientSpider {

  private final PropertiesReader propertiesReader;
  private final DriverHelper driverHelper;
  private final SleepHelper sleepHelper;

  public void configClient(ChromeDriver driver) {
    Logger.startLog.accept(this.getClass().getSimpleName());

    sleepHelper.basicSleep();
    createClient(driver);
    fillForm(driver);

    Logger.endLog.accept(this.getClass().getSimpleName());
  }

  private void createClient(ChromeDriver driver) {
    WebDriverWait wait = driverHelper.getWebDriverWait(driver);
    Configuration properties = propertiesReader.get();

    WebElement clientsOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/realms/poc-management/clients')]")));
    clientsOption.click();

    WebElement createClientButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("createClient")));
    createClientButton.click();

    WebElement clientIdField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("clientId")));
    clientIdField.sendKeys(properties.getClient().getName());

    WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Save')]")));
    saveButton.click();
  }

  private void fillForm(ChromeDriver driver) {
    WebDriverWait wait = driverHelper.getWebDriverWait(driver);
    Configuration properties = propertiesReader.get();

    WebElement validRedirectUriField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newRedirectUri")));
    validRedirectUriField.sendKeys(properties.getClient().getRedirectUriFields());

    WebElement addRedirectUriButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and contains(@class, 'btn-default')]//span[contains(@class, 'fa-plus')]")));
    addRedirectUriButton.click();

    WebElement finalSaveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Save')]")));
    finalSaveButton.click();
  }
}
