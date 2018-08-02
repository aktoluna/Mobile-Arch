package com.testinium.mobile.model;

public class MobileEnvironment {

  private AppEnvironment test;
  private AppEnvironment prep;
  private AppEnvironment prod;
  private String env;


  public AppEnvironment getTest() {
    return test;
  }

  public void setTest(AppEnvironment test) {
    this.test = test;
  }

  public AppEnvironment getPrep() {
    return prep;
  }

  public void setPrep(AppEnvironment prep) {
    this.prep = prep;
  }

  public AppEnvironment getProd() {
    return prod;
  }

  public void setProd(AppEnvironment prod) {
    this.prod = prod;
  }

  public String getEnv() {
    return env;
  }

  public void setEnv(String env) {
    this.env = env;
  }

  public AppEnvironment getSelectedEnv() {
    AppEnvironment appEnvironment;
    if (env.equalsIgnoreCase("prep")) {
      appEnvironment = prep;
    }
    if (env.equalsIgnoreCase("prod")) {
      appEnvironment = prod;
    } else {
      appEnvironment = test;
    }
    return appEnvironment;
  }
}
