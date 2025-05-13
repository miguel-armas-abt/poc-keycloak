package com.demo.poc.entrypoint.user.spider;

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
public class UserSpider {

  private final PropertiesReader propertiesReader;
  private final DriverHelper driverHelper;
  private final SleepHelper sleepHelper;

  public void configUser(ChromeDriver driver) {
    Logger.startLog.accept(this.getClass().getSimpleName());

    sleepHelper.basicSleep();
    createUser(driver);
    assignCredentials(driver);

    Logger.endLog.accept(this.getClass().getSimpleName());
  }

  private void createUser(ChromeDriver driver) {
    WebDriverWait wait = driverHelper.getWebDriverWait(driver);
    Configuration properties = propertiesReader.get();
    String realmName = properties.getRealm().getName();

    WebElement usersOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/realms/" + realmName + "/users')]")));
    usersOption.click();

    WebElement addUserButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("createUser")));
    addUserButton.click();

    WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
    usernameField.sendKeys(properties.getLogin().getUsername());

    WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and contains(text(),'Save')]")));
    saveButton.click();
  }

  private void assignCredentials(ChromeDriver driver) {
    WebDriverWait wait = driverHelper.getWebDriverWait(driver);
    Configuration properties = propertiesReader.get();


    WebElement credentialsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'user-credentials')]")));
    credentialsTab.click();

    WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newPas")));
    passwordField.sendKeys(properties.getLogin().getPassword());

    WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPas")));
    confirmPasswordField.sendKeys(properties.getLogin().getPassword());

    WebElement temporarySwitch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='temporaryPassword']")));
    Actions actions = new Actions(driver);
    actions.moveToElement(temporarySwitch).click().perform();

    WebElement setPasswordButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Set Password')]")));
    setPasswordButton.click();

    WebElement confirmSetPasswordButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Set password')]")));
    confirmSetPasswordButton.click();
  }
}
