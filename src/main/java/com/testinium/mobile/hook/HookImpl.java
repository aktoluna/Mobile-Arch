package com.testinium.mobile.hook;

import com.google.common.base.Stopwatch;
import com.testinium.mobile.di.InjectionHelper;
import com.testinium.mobile.di.module.DriverModule;
import com.testinium.mobile.driver.DriverCreator;
import com.testinium.mobile.helper.ConfigurationHelper;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.BeforeStep;
import com.thoughtworks.gauge.ExecutionContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.codejargon.feather.Feather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HookImpl {

  private Logger logger = LoggerFactory.getLogger(getClass());
  private AppiumDriver<MobileElement> appiumDriver;
  private Stopwatch stopwatch = Stopwatch.createUnstarted();

  @BeforeScenario
  public void beforeScenario(ExecutionContext context) throws MalformedURLException {
    logger.info("Start scenario {}", context.getCurrentScenario().getName());
    appiumDriver = DriverCreator.createDriver();
    if (ConfigurationHelper.INSTANCE.getConfiguration().getWebDriverConfiguration().isMobileWeb()) {
      appiumDriver.get(
          ConfigurationHelper.INSTANCE.getConfiguration().getEnvironment().getWebEnvironment()
              .getSelectedEnv());
    }
    InjectionHelper.getInstance().setFeather(Feather.with(new DriverModule(appiumDriver)));
    stopwatch.start();
  }

  @BeforeStep
  public void beforeStep(ExecutionContext executionContext) {
    logger.debug("Running step {}", executionContext.getCurrentStep().getText());
  }

  @BeforeStep
  public void afterStep(ExecutionContext executionContext) {
    logger.debug("End step {} with {}", executionContext.getCurrentStep().getText(),
        executionContext.getCurrentStep().getIsFailing() ? "FAIL" : "SUCCESS");
  }

  @AfterScenario
  public void afterScenario(ExecutionContext context) {
    stopwatch.stop();
    logger.info("End scenario {} with {}", context.getCurrentScenario().getName(),
        context.getCurrentScenario().getIsFailing() ? "FAIL" : "SUCCESS");
    logger.info(String.format("Time = %d sn.", stopwatch.elapsed(TimeUnit.SECONDS)));
    appiumDriver.quit();
    stopwatch.reset();
  }
}
