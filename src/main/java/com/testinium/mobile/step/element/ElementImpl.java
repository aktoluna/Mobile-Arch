package com.testinium.mobile.step.element;

import io.appium.java_client.MobileElement;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ElementImpl implements ElementFindStep {

  @Override
  public MobileElement findElement(ExpectedCondition<MobileElement> expectedCondition,
      boolean assertionCheck) {
    return null;
  }

  @Override
  public MobileElement findElement(ExpectedCondition<MobileElement> expectedCondition) {
    return null;
  }

  @Override
  public MobileElement findElement(By by) {
    return null;
  }

  @Override
  public List<MobileElement> findElements(
      ExpectedCondition<List<MobileElement>> expectedCondition) {
    return null;
  }

  @Override
  public List<MobileElement> findElements(By by) {
    return null;
  }

  @Override
  public MobileElement findElement(By by, int index) {
    return null;
  }

  @Override
  public MobileElement findElementByKey(String key) {
    return null;
  }

  @Override
  public List<MobileElement> findElementsByKey(String key) {
    return null;
  }

  @Override
  public MobileElement findElementsByKey(String key, int index) {
    return null;
  }
}
