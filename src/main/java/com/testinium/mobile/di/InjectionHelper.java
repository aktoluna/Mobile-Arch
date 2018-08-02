package com.testinium.mobile.di;

import org.codejargon.feather.Feather;

public class InjectionHelper {

  private static InjectionHelper instance;
  private static Object LOCK = new Object();

  private Feather feather;

  private InjectionHelper() {
  }

  public static InjectionHelper getInstance() {
    synchronized (LOCK) {
      if (instance == null) {
        instance = new InjectionHelper();
      }
    }
    return instance;
  }

  public Feather getFeather() {
    return feather;
  }

  public void setFeather(Feather feather) {
    this.feather = feather;
  }

}
