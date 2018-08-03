package com.testinium.mobile.step.driver;

import com.testinium.mobile.selector.Selector;
import com.testinium.mobile.step.ISelector;
import com.testinium.mobile.step.IStore;
import io.appium.java_client.AppiumDriver;
import javax.inject.Inject;
import org.assertj.core.api.Assertions;

public class DriverImpl implements DriverActionStep, DriverCheckStep, IStore, ISelector {

  @Inject
  AppiumDriver driver;
  @Inject
  Selector selector;

  @Override
  public void goToUrl(String url) {
    driver.get(url);
  }

  @Override
  public void fullScreen() {
    driver.manage().window().fullscreen();
  }

  @Override
  public void close() {
    driver.close();
  }

  @Override
  public void back() {
    driver.navigate().back();
  }

  @Override
  public void forward() {
    driver.navigate().forward();
  }

  @Override
  public void switchToFrameById(String id) {
    driver.switchTo().frame(id);
  }

  @Override
  public void switchToFrameByKey(String key) {
    driver.switchTo().frame(driver.findElement(selector.getElementInfoToBy(key)));
  }

  @Override
  public void switchToFrameByIndex(int index) {
    driver.switchTo().frame(index);
  }

  @Override
  public String getUrl() {
    return driver.getCurrentUrl();
  }

  @Override
  public String getTitle() {
    return driver.getTitle();
  }

  @Override
  public String getPageSource() {
    return driver.getPageSource();
  }

  @Override
  public void urlEqualsText(String equalUrl) {
    Assertions.assertThat(getUrl()).isEqualTo(equalUrl);
  }

  @Override
  public void urlEqualsSavedKey(String savedKey) {
    urlEqualsText(getValueByKey(savedKey));
  }

  @Override
  public void titleEqualsText(String equalTitle) {
    Assertions.assertThat(getTitle()).isEqualTo(equalTitle);
  }

  @Override
  public void titleEqualsSavedKey(String savedKey) {
    titleEqualsText(getValueByKey(savedKey));
  }

  @Override
  public void titleContainsText(String containTitle) {
    Assertions.assertThat(getTitle()).contains(containTitle);
  }

  @Override
  public void titleContainsSavedKey(String savedKey) {
    titleContainsText(getValueByKey(savedKey));
  }

  @Override
  public void pageSourceContainsText(String containsPageSource) {
    Assertions.assertThat(getPageSource()).contains(containsPageSource);
  }

  @Override
  public void pageSourceContainsSavedKey(String savedKey) {
    pageSourceContainsText(getValueByKey(savedKey));
  }

  @Override
  public Selector getSelector() {
    return selector;
  }
}
