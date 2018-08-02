package com.testinium.mobile.selector;

import com.testinium.mobile.model.ElementInfo;
import org.openqa.selenium.By;

public class IOSSelector implements Selector {

  @Override
  public By getElementInfoToBy(ElementInfo elementInfo) {
    By by = null;
    if (elementInfo.getIosType().equals("css")) {
      by = By.cssSelector(elementInfo.getAndroidValue());
    } else if (elementInfo.getIosValue().equals("id")) {
      by = By.id(elementInfo.getAndroidValue());
    } else if (elementInfo.getIosType().equals("xpath")) {
      by = By.xpath(elementInfo.getAndroidValue());
    } else if (elementInfo.getIosType().equals("class")) {
      by = By.className(elementInfo.getAndroidValue());
    }
    return by;
  }

  @Override
  public int getElementInfoToIndex(ElementInfo elementInfo) {
    return elementInfo.getIosIndex();
  }
}
