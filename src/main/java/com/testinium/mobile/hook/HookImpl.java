package com.testinium.mobile.hook;

import com.testinium.mobile.di.InjectionHelper;
import com.testinium.mobile.di.module.DriverModule;
import com.testinium.mobile.driver.DriverCreator;
import com.testinium.mobile.helper.ConfigurationHelper;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import java.net.MalformedURLException;
import org.codejargon.feather.Feather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HookImpl {

  private Logger logger = LoggerFactory.getLogger(getClass());
  private AppiumDriver<MobileElement> appiumDriver;

  @BeforeScenario
  public void beforeScenario() throws MalformedURLException {
    appiumDriver = DriverCreator.createDriver();
    if (ConfigurationHelper.INSTANCE.getConfiguration().getWebDriverConfiguration().isMobileWeb()) {
      appiumDriver.get(
          ConfigurationHelper.INSTANCE.getConfiguration().getEnvironment().getWebEnvironment()
              .getSelectedEnv());
    }
    InjectionHelper.getInstance().setFeather(Feather.with(new DriverModule(appiumDriver)));
  }

  @AfterScenario
  public void afterScenario() {
    appiumDriver.quit();
  }
}
