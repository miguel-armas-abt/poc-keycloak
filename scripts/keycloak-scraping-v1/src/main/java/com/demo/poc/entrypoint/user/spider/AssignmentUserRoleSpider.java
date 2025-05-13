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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AssignmentUserRoleSpider {

  private final PropertiesReader propertiesReader;
  private final DriverHelper driverHelper;
  private final SleepHelper sleepHelper;

  public void configRoleMappings(ChromeDriver driver) {
    Logger.startLog.accept(this.getClass().getSimpleName());

    sleepHelper.basicSleep();
    findUser(driver);
    assignRole(driver);

    Logger.endLog.accept(this.getClass().getSimpleName());
  }

  private void findUser(ChromeDriver driver) {
    WebDriverWait wait = driverHelper.getWebDriverWait(driver);
    Configuration properties = propertiesReader.get();
    String realmName = properties.getRealm().getName();

    WebElement rolesOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/realms/" + realmName + "/users')]")));
    rolesOption.click();

    WebElement viewAllUsersButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("viewAllUsers")));
    viewAllUsersButton.click();

    WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search...']")));
    searchField.sendKeys(properties.getLogin().getUsername());

    WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("userSearch")));
    searchButton.click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-table")));

    WebElement userLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[td[text()='admin']]/td[1]/a")));
    userLink.click();
  }

  private void assignRole(ChromeDriver driver) {
    WebDriverWait wait = driverHelper.getWebDriverWait(driver);
    WebElement roleMappingsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'role-mappings')]")));
    roleMappingsTab.click();

    Configuration properties = propertiesReader.get();
    String roleName = properties.getRole().getName();

    WebElement availableRoles = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@title='" + roleName + "']")));
    availableRoles.click();

    WebElement addSelectedButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add selected')]")));
    addSelectedButton.click();
  }
}
