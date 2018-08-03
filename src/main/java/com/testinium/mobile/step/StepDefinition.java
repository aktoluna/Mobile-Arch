package com.testinium.mobile.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface StepDefinition {

  Logger logger = LoggerFactory.getLogger(StepDefinition.class);

  default void waitBySecond(int seconds) throws InterruptedException {
    logger.info("Wait by seconds {}", seconds);
    waitByMillis(seconds * 1000);
  }

  default void waitByMillis(int millis) throws InterruptedException {
    logger.info("Wait by millis {}", millis);
    Thread.sleep(millis);
  }

  default void debug() {
    logger.debug("Debug Step");
  }

//  void swipeUpAccordingToPhoneSize();
//
//  void swipeDownAccordingToPhoneSize();
//
//  void swipe(int times) throws InterruptedException;

//  void back();
//
//  void hideAndroidKeyboard();
}
