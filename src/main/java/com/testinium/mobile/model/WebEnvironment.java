package com.testinium.mobile.model;

import com.google.gson.annotations.SerializedName;

public class WebEnvironment {

  @SerializedName("test_url")
  private String testUrl;
  @SerializedName("prep_url")
  private String prepUrl;
  @SerializedName("prod_url")
  private String prodUrl;
  private String env;

  public String getTestUrl() {
    return testUrl;
  }

  public void setTestUrl(String testUrl) {
    this.testUrl = testUrl;
  }

  public String getPrepUrl() {
    return prepUrl;
  }

  public void setPrepUrl(String prepUrl) {
    this.prepUrl = prepUrl;
  }

  public String getProdUrl() {
    return prodUrl;
  }

  public void setProdUrl(String prodUrl) {
    this.prodUrl = prodUrl;
  }

  public String getEnv() {
    return env;
  }

  public void setEnv(String env) {
    this.env = env;
  }

  public String getSelectedEnv() {
    String appEnvironment;
    if (env.equalsIgnoreCase("prep")) {
      appEnvironment = prepUrl;
    }
    if (env.equalsIgnoreCase("prod")) {
      appEnvironment = prodUrl;
    } else {
      appEnvironment = testUrl;
    }
    return appEnvironment;
  }
}
