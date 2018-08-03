package com.testinium.mobile.step.driver;

public interface DriverCheckStep {

  void urlEqualsText(String equalUrl);

  void urlEqualsSavedKey(String savedKey);

  void titleEqualsText(String equalTitle);

  void titleEqualsSavedKey(String savedKey);

  void titleContainsText(String containTitle);

  void titleContainsSavedKey(String savedKey);

  void pageSourceContainsText(String containsPageSource);

  void pageSourceContainsSavedKey(String savedKey);
}
