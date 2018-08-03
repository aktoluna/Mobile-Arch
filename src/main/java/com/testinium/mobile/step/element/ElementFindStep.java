package com.testinium.mobile.step.element;

import io.appium.java_client.MobileElement;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;

public interface ElementFindStep {

  MobileElement findElement(ExpectedCondition<MobileElement> expectedCondition,
      boolean assertionCheck);

  MobileElement findElement(ExpectedCondition<MobileElement> expectedCondition);

  MobileElement findElement(By by);

  List<MobileElement> findElements(
      ExpectedCondition<List<MobileElement>> expectedCondition);

  List<MobileElement> findElements(By by);

  MobileElement findElement(By by, int index);

  MobileElement findElementByKey(String key);

  List<MobileElement> findElementsByKey(String key);

  MobileElement findElementsByKey(String key, int index);
}
