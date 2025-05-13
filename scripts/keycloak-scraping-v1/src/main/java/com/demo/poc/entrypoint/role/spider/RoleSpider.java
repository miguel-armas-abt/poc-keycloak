package com.demo.poc.entrypoint.role.spider;

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
public class RoleSpider {

  private final PropertiesReader propertiesReader;
  private final DriverHelper driverHelper;
  private final SleepHelper sleepHelper;

  public void configRole(ChromeDriver driver) {
    Logger.startLog.accept(this.getClass().getSimpleName());

    sleepHelper.basicSleep();
    createRole(driver);

    Logger.endLog.accept(this.getClass().getSimpleName());
  }

  private void createRole(ChromeDriver driver) {
    WebDriverWait wait = driverHelper.getWebDriverWait(driver);
    Configuration properties = propertiesReader.get();
    String realmName = properties.getRealm().getName();

    WebElement rolesOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/realms/" + realmName + "/roles')]")));
    rolesOption.click();

    WebElement addRoleButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("createRole")));
    addRoleButton.click();

    WebElement roleNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
    roleNameField.sendKeys(properties.getRole().getName());

    WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
    saveButton.click();
  }
}
