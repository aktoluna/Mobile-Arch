package com.testinium.mobile.model;

import com.google.gson.annotations.SerializedName;

public class Configuration {

  @SerializedName("driver_configuration")
  private WebDriverConfiguration webDriverConfiguration;
  private Environment environment;

  public WebDriverConfiguration getWebDriverConfiguration() {
    return webDriverConfiguration;
  }

  public void setWebDriverConfiguration(
      WebDriverConfiguration webDriverConfiguration) {
    this.webDriverConfiguration = webDriverConfiguration;
  }

  public Environment getEnvironment() {
    return environment;
  }

  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }
}
