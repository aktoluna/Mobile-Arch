package com.testinium.mobile.step;

import io.appium.java_client.MobileElement;
import java.util.List;
import org.openqa.selenium.By;

public interface StepDefinition {

  MobileElement findElement(By by);

  List<MobileElement> findElements(By by);

  void clickByKey(String key);

  void existClickByKey(String key);

  void sendKeysByKey(String key, String text);

  void sendKeysByKeyNotClear(String key, String text);

  void sendKeysBySaveKeyNotClear(String key, String saveKey);

  void saveTextByKey(String key, String saveKey);

  void equalsSaveTextByKey(String key, String saveKey);

  MobileElement findElementByKey(String key);

  List<MobileElement> findElemenstByKey(String key);

  void clickByText(String text);

  void clickByIdWithContains(String key, String text);

  void clickByKeyWithSwipe(String key, String text);

  void sendKeysByKeyWithSwipe(String key, String text);

  void isElementExist(String key, String message);

  void containsTextByKey(String key, String text);

  void equalsTextByKey(String key, String text);

  void waitBySecond(int seconds) throws InterruptedException;

  void swipeUpAccordingToPhoneSize();

  void swipeDownAccordingToPhoneSize();

  String getSMSToken(String phoneNo, String phoneKey);

  void swipe(int times) throws InterruptedException;

  void debug() throws InterruptedException;

  void back();

  void hideAndroidKeyboard();
}
