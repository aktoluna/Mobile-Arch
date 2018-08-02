package com.testinium.mobile.di;

public interface Injectable {

  default void inject() {
    //LogHelper().getSlnLogger().info("Inject Starting...");
    InjectionHelper.getInstance().getFeather().injectFields(this);
  }
}
