package com.demo.poc.entrypoint.realm.spider;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class RealmCreatorSpider {

  private final PropertiesReader propertiesReader;
  private final DriverHelper driverHelper;
  private final SleepHelper sleepHelper;

  public void createRealm(ChromeDriver driver) {
    Logger.startLog.accept(this.getClass().getSimpleName());

    sleepHelper.basicSleep();
    WebDriverWait wait = driverHelper.getWebDriverWait(driver);
    Configuration properties = propertiesReader.get();

    WebElement masterElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'Master')]")));
    Actions actions = new Actions(driver);
    actions.moveToElement(masterElement).perform();
    WebElement addRealmButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Add realm')]")));
    addRealmButton.click();

    WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
    nameField.sendKeys(properties.getRealm().getName());

    WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Create')]")));
    createButton.click();

    Logger.endLog.accept(this.getClass().getSimpleName());
  }
}
