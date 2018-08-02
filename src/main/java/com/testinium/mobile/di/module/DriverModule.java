package com.testinium.mobile.di.module;

import com.testinium.mobile.helper.ConfigurationHelper;
import com.testinium.mobile.model.Timeout;
import com.testinium.mobile.selector.Selector;
import com.testinium.mobile.selector.SelectorFactory;
import com.testinium.mobile.selector.SelectorType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import org.codejargon.feather.Provides;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;

public class DriverModule {

  private final AppiumDriver<MobileElement> driver;

  public DriverModule(AppiumDriver driver) {
    this.driver = driver;
  }

  @Provides
  @Singleton
  public AppiumDriver<MobileElement> provideAppiumDriver() {
    return driver;
  }

  @Provides
  @Singleton
  @Named("AndroidDriver")
  public AndroidDriver<MobileElement> provideAndroidAppiumDriver() {
    return (AndroidDriver<MobileElement>) driver;
  }

  @Provides
  @Singleton
  @Named("IosDriver")
  public IOSDriver<MobileElement> provideIosAppiumDriver() {
    return (IOSDriver<MobileElement>) driver;
  }

  @Provides
  @Singleton
  public FluentWait<AppiumDriver> provideFluentWait(AppiumDriver appiumDriver) {
    Timeout timeout = ConfigurationHelper.INSTANCE.getConfiguration().getWebDriverConfiguration()
        .getTimeout();
    FluentWait<AppiumDriver> appiumFluentWait = new FluentWait<AppiumDriver>(appiumDriver);
    appiumFluentWait.withTimeout(timeout.getExplicitly(), TimeUnit.SECONDS)
        .pollingEvery(timeout.getPooling(), TimeUnit.MILLISECONDS)
        .ignoring(NoSuchElementException.class);
    return appiumFluentWait;
  }

  @Provides
  @Singleton
  public Selector provideSelector(AppiumDriver appiumDriver) {
    return SelectorFactory
        .createElementHelper(
            appiumDriver instanceof AndroidDriver ? SelectorType.ANDROID : SelectorType.IOS);
  }

  @Provides
  @Singleton
  @Named("IOsSelector")
  public Selector provideIosSelector() {
    return SelectorFactory
        .createElementHelper(SelectorType.IOS);
  }

  @Provides
  @Singleton
  @Named("AndroidSelector")
  public Selector provideAndroidSelector() {
    return SelectorFactory
        .createElementHelper(SelectorType.ANDROID);
  }
}
