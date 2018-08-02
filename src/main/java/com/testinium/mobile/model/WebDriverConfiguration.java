package com.testinium.mobile.model;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class WebDriverConfiguration {
  @SerializedName("mobile_web")
  private boolean mobileWeb;
  @SerializedName("hub_url")
  private String hubUrl;
  private boolean remote;
  private boolean android;
  private Timeout timeout;
  private Map<String, Object> capability;

  public boolean isMobileWeb() {
    return mobileWeb;
  }

  public void setMobileWeb(boolean mobileWeb) {
    this.mobileWeb = mobileWeb;
  }

  public String getHubUrl() {
    return hubUrl;
  }

  public void setHubUrl(String hubUrl) {
    this.hubUrl = hubUrl;
  }

  public boolean isRemote() {
    return remote;
  }

  public void setRemote(boolean remote) {
    this.remote = remote;
  }

  public boolean isAndroid() {
    return android;
  }

  public void setAndroid(boolean android) {
    this.android = android;
  }

  public Timeout getTimeout() {
    return timeout;
  }

  public void setTimeout(Timeout timeout) {
    this.timeout = timeout;
  }

  public Map<String, Object> getCapability() {
    return capability;
  }

  public void setCapability(Map<String, Object> capability) {
    this.capability = capability;
  }
}
