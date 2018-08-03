package com.testinium.mobile.step.element;

public interface ElementActionStep {

  void clickByKey(String key, boolean exist);

  void clickByKey(String key);

  void clickByKey(String key, int index);

  void clearByKey(String key);

  void clearByKey(String key, int index);

  void sendKeysByKey(String key, String text, boolean clear);

  void sendKeysByKey(String key, String text);

  void sendKeysByKey(String key, String text, int index);

  void sendKeysByKeyWithSavedKey(String key, String savedKey);

  void sendKeysByKeyWithSavedKey(String key, String savedKey, int index);

  void getTextByKey(String key);

  void getTextByKey(String key, int index);

  void saveTextByKey(String key, String saveKey);

  void saveTextByKey(String key, String saveKey, int index);

  void hoverByKey(String key);

  void hoverByKey(String key, int index);

  void scrollByKey(String key);

  void scrollByKey(String key, int index);

  void moveSubByKey(String key, String subKey);

  void swipeUpAccordingToPhoneSize();

  void swipeDownAccordingToPhoneSize();

  void swipe(int times) throws InterruptedException;

}
