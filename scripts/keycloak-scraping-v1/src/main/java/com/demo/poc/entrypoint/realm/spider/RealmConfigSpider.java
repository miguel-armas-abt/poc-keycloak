package com.demo.poc.entrypoint.realm.spider;

import com.demo.poc.commons.helper.SleepHelper;
import com.demo.poc.commons.logging.Logger;
import com.demo.poc.commons.utils.YamlReader;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class RealmConfigSpider {

  private final PropertiesReader propertiesReader;
  private final DriverHelper driverHelper;
  private final SleepHelper sleepHelper;

  public void configRealm(ChromeDriver driver) {
    Logger.startLog.accept(this.getClass().getSimpleName());

    sleepHelper.basicSleep();
    getRS256Key(driver);
    updateAccessTokenLifespan(driver);

    Logger.endLog.accept(this.getClass().getSimpleName());
  }

  private void getRS256Key(ChromeDriver driver) {
    WebDriverWait wait = driverHelper.getWebDriverWait(driver);
    Configuration properties = propertiesReader.get();

    WebElement keysTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Keys')]")));
    keysTab.click();

    WebElement rs256Row = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), 'RS256')]/following-sibling::td[2]")));

    String kidValue = rs256Row.getText();
    YamlReader.create(properties.getOutputFile().toString() , Map.of("rs256", kidValue));
  }

  private void updateAccessTokenLifespan(ChromeDriver driver) {
    WebDriverWait wait = driverHelper.getWebDriverWait(driver);
    Configuration properties = propertiesReader.get();

    WebElement keysTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Tokens')]")));
    keysTab.click();

    WebElement accessTokenLifespanInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accessTokenLifespan")));
    accessTokenLifespanInput.clear();
    accessTokenLifespanInput.sendKeys(properties.getRealm().getTokenTtlMinutes());

    WebElement accessTokenLifespanUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("accessTokenLifespanUnit")));
    Select selectUnit = new Select(accessTokenLifespanUnit);
    selectUnit.selectByVisibleText("Minutes");

    WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@kc-save]")));
    saveButton.click();
  }
}
