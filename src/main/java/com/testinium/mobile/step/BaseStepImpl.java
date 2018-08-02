package com.testinium.mobile.step;

import com.testinium.mobile.di.Injectable;
import com.testinium.mobile.helper.StoreHelper;
import com.testinium.mobile.model.SelectorInfo;
import com.testinium.mobile.selector.Selector;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSDriver;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseStepImpl implements Injectable, StepDefinition {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Inject
  AppiumDriver<MobileElement> appiumDriver;
  @Inject
  FluentWait<AppiumDriver> appiumFluentWait;
  @Inject
  Selector selector;

  public BaseStepImpl() {
    inject();
  }

  @Override
  public MobileElement findElement(By by) {
    return appiumFluentWait.until(new ExpectedCondition<MobileElement>() {
      @Nullable
      @Override
      public MobileElement apply(@Nullable WebDriver webDriver) {
        try {
          MobileElement mobileElement = appiumDriver.findElement(by);
          return mobileElement;
        } catch (WebDriverException e) {
          throw e;
        }
      }
    });
  }

  @Override
  public List<MobileElement> findElements(By by) {
    return appiumFluentWait.until(new ExpectedCondition<List<MobileElement>>() {
      @Nullable
      @Override
      public List<MobileElement> apply(@Nullable WebDriver driver) {
        List<MobileElement> elements = driver.findElements(by);
        return elements.size() > 0 ? elements : null;
      }
    });
  }

  @Override
  public void clickByKey(String key) {
    findElementByKey(key).click();
  }

  @Override
  public void existClickByKey(String key) {
    WebElement element = null;
    try {
      element = findElementByKey(key);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (element != null) {
      element.click();
    }
  }

  @Override
  public void sendKeysByKey(String key, String text) {
    MobileElement webElement = findElementByKey(key);
    webElement.clear();
    webElement.setValue(text);
  }

  @Override
  public void sendKeysByKeyNotClear(String key, String text) {
    findElementByKey(key).setValue(text);
  }

  @Override
  public void sendKeysBySaveKeyNotClear(String key, String saveKey) {
    findElementByKey(key).setValue(StoreHelper.INSTANCE.getValue(saveKey));
  }

  @Override
  public void saveTextByKey(String key, String saveKey) {
    StoreHelper.INSTANCE.saveValue(saveKey, findElementByKey(key).getText());
  }

  @Override
  public void equalsSaveTextByKey(String key, String saveKey) {
    Assert.assertEquals(StoreHelper.INSTANCE.getValue(saveKey), findElementByKey(key).getText());
  }

  @Override
  public MobileElement findElementByKey(String key) {
    SelectorInfo selectorInfo = selector.getSelectorInfo(key);
    return selectorInfo.getIndex() > 0 ? findElements(selectorInfo.getBy())
        .get(selectorInfo.getIndex()) : findElement(selectorInfo.getBy());
  }

  @Override
  public List<MobileElement> findElemenstByKey(String key) {
    SelectorInfo selectorInfo = selector.getSelectorInfo(key);
    return findElements(selectorInfo.getBy());
  }

  @Override
  public void clickByText(String text) {
    findElement(By.xpath(".//*[contains(@text,'" + text + "')]")).click();
  }

  @Override
  public void clickByIdWithContains(String key, String text) {
    List<MobileElement> elements = findElemenstByKey(key);
    for (MobileElement element : elements) {
      if (element.getText().toLowerCase().contains(text.toLowerCase())) {
        element.click();
        break;
      }
    }
  }

  @Override
  public void clickByKeyWithSwipe(String key, String text) {
    boolean find = false;
    int maxRetryCount = 2;
    while (!find && maxRetryCount > 0) {
      List<MobileElement> elements = findElemenstByKey(key);
      for (MobileElement element : elements) {
        if (element.getText().contains(text)) {
          element.click();
          find = true;
          break;
        }
      }
      if (!find) {
        maxRetryCount--;
        swipeUpAccordingToPhoneSize();
      }
    }
  }

  @Override
  public void sendKeysByKeyWithSwipe(String key, String text) {
    boolean find = false;
    int maxRetryCount = 2;
    while (!find && maxRetryCount > 0) {
      List<MobileElement> elements = findElemenstByKey(key);
      for (MobileElement element : elements) {
        if (element.getText().contains(text)) {
          element.setValue(text);
          find = true;
          break;
        }
      }
      if (!find) {
        maxRetryCount--;
        swipeUpAccordingToPhoneSize();
      }
    }
  }

  @Override
  public void isElementExist(String key, String message) {
    Assert.assertTrue(message, findElementByKey(key) != null);
  }

  @Override
  public void containsTextByKey(String key, String text) {
    By by = selector.getElementInfoToBy(key);
    Assert.assertTrue(appiumFluentWait.until(new ExpectedCondition<Boolean>() {
      private String currentValue = null;

      @Override
      public Boolean apply(WebDriver driver) {
        try {
          currentValue = driver.findElement(by).getText();
          return currentValue.contains(text);
        } catch (Exception e) {
          return false;
        }
      }

      @Override
      public String toString() {
        return String.format("text contains be \"%s\". Current text: \"%s\"", text, currentValue);
      }
    }));
  }

  @Override
  public void equalsTextByKey(String key, String text) {
    Assert.assertTrue(appiumFluentWait.until(
        ExpectedConditions.textToBe(selector.getElementInfoToBy(key), text)));
  }

  @Override
  public void waitBySecond(int seconds) throws InterruptedException {
    Thread.sleep(seconds * 1000);
  }

  @Override
  public void swipeUpAccordingToPhoneSize() {
    Dimension d = appiumDriver.manage().window().getSize();
    int height = d.height;
    int width = d.width;

    int swipeStartWidth = width / 2, swipeEndWidth = width / 2;

    int swipeStartHeight = (height * 30) / 100;
    int swipeEndHeight = (height * 80) / 100;

    appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
  }

  @Override
  public void swipeDownAccordingToPhoneSize() {
    if (appiumDriver instanceof IOSDriver) {
      WebElement element = appiumDriver.findElementByClassName("UIAWindow");
      int x = element.getSize().getWidth() / 2;
      int yStart = element.getSize().getHeight();
      //appiumDriver.swipe(x, yStart, x, 0 - yStart, 500);
    } else {
      Dimension d = appiumDriver.manage().window().getSize();
      int height = d.height;
      int width = d.width;

      int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
      int swipeStartHeight = (height * 80) / 100;
      int swipeEndHeight = (height * 30) / 100;
      appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
    }
  }

  @Override
  public String getSMSToken(String phoneNo, String phoneKey) {
    String password = null;
    String smsContent;
    int size = 6;

    try {
      InputStream in = new URL("http://dev.testinium.com:10256/code?phone=" + phoneNo).openStream();
      try {
        smsContent = IOUtils.toString(in);
        Pattern r = Pattern.compile("([0-9]){" + size + "}");
        Matcher m = r.matcher(smsContent);
        if (m.find()) {
          password = m.group(0).trim();
          System.out.println("sms pass : " + password);
          StoreHelper.INSTANCE.saveValue(phoneKey, password);
        }
      } finally {
        IOUtils.closeQuietly(in);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return password;
  }

  @Override
  public void swipe(int times) throws InterruptedException {
    for (int i = 0; i < times; i++) {
      swipeDownAccordingToPhoneSize();
      waitBySecond(1);
    }
  }

  @Override
  public void debug() throws InterruptedException {
    Thread.sleep(1000);
  }

  @Override
  public void back() {
    if (appiumDriver instanceof AndroidDriver) {
      ((AndroidDriver) appiumDriver).pressKeyCode(AndroidKeyCode.BACK);
    }
  }

  @Override
  public void hideAndroidKeyboard() {
    try {
      appiumDriver.hideKeyboard();
    } catch (Exception ex) {
      System.err.println(String.format("Android klavye kapatılamadı. Hata: %s", ex.getMessage()));
    }
  }
}
