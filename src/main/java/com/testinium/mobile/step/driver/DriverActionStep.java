package com.testinium.mobile.step.driver;

import org.openqa.selenium.By;

public interface DriverActionStep {

  void goToUrl(String url);

  void fullScreen();

  void close();

  void back();

  void forward();

  void switchToFrameById(String id);

  void switchToFrameByKey(String key);

  void switchToFrameByIndex(int index);

  String getUrl();

  String getTitle();

  String getPageSource();
}
