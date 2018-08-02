package com.testinium.mobile.model;

import com.google.gson.annotations.SerializedName;

public class AppEnvironment {

  @SerializedName("app_activity")
  private String appActivity;
  @SerializedName("app_package")
  private String AppPackage;

  public String getAppActivity() {
    return appActivity;
  }

  public void setAppActivity(String appActivity) {
    this.appActivity = appActivity;
  }

  public String getAppPackage() {
    return AppPackage;
  }

  public void setAppPackage(String appPackage) {
    AppPackage = appPackage;
  }
}
