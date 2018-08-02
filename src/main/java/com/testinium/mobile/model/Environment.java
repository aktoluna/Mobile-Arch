package com.testinium.mobile.model;

import com.google.gson.annotations.SerializedName;

public class Environment {

  @SerializedName("web")
  private WebEnvironment webEnvironment;
  @SerializedName("mobile")
  private MobileEnvironment mobileEnvironment;

  public WebEnvironment getWebEnvironment() {
    return webEnvironment;
  }

  public void setWebEnvironment(WebEnvironment webEnvironment) {
    this.webEnvironment = webEnvironment;
  }

  public MobileEnvironment getMobileEnvironment() {
    return mobileEnvironment;
  }

  public void setMobileEnvironment(MobileEnvironment mobileEnvironment) {
    this.mobileEnvironment = mobileEnvironment;
  }
}
