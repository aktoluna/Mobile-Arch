package com.testinium.mobile.model;

import com.google.gson.annotations.SerializedName;

public class Timeout {
  @SerializedName("page_load")
  private int pageLoad;
  private int script;
  private int implicitly;
  private int explicitly;
  private int pooling;

  public int getPageLoad() {
    return pageLoad;
  }

  public void setPageLoad(int pageLoad) {
    this.pageLoad = pageLoad;
  }

  public int getScript() {
    return script;
  }

  public void setScript(int script) {
    this.script = script;
  }

  public int getImplicitly() {
    return implicitly;
  }

  public void setImplicitly(int implicitly) {
    this.implicitly = implicitly;
  }

  public int getExplicitly() {
    return explicitly;
  }

  public void setExplicitly(int explicitly) {
    this.explicitly = explicitly;
  }

  public int getPooling() {
    return pooling;
  }

  public void setPooling(int pooling) {
    this.pooling = pooling;
  }
}
