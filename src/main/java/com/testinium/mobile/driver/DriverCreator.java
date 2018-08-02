package com.testinium.mobile.driver;

import com.saha.slnarch.common.helper.StringHelper;
import com.testinium.mobile.helper.ConfigurationHelper;
import com.testinium.mobile.model.Configuration;
import com.testinium.mobile.model.WebDriverConfiguration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverCreator {

  private DriverCreator() {

  }

  public static AppiumDriver<MobileElement> createDriver() throws MalformedURLException {
    AppiumDriver<MobileElement> appiumDriver;
    if (StringHelper.isEmpty(System.getenv("key"))) {
      appiumDriver = createLocalDriver();
    } else {
      appiumDriver = createTestiniumDriver();
    }
    setUpTimeout(appiumDriver);
    return appiumDriver;
  }

  private static void setUpTimeout(AppiumDriver<MobileElement> appiumDriver) {
    WebDriverConfiguration webDriverConfiguration = ConfigurationHelper.INSTANCE.getConfiguration()
        .getWebDriverConfiguration();
    appiumDriver.manage().timeouts()
        .implicitlyWait(webDriverConfiguration.getTimeout().getImplicitly(),
            TimeUnit.SECONDS);
    if (webDriverConfiguration.isMobileWeb()) {
      appiumDriver.manage().timeouts()
          .pageLoadTimeout(webDriverConfiguration.getTimeout().getPageLoad(), TimeUnit.SECONDS);
      appiumDriver.manage().timeouts()
          .setScriptTimeout(webDriverConfiguration.getTimeout().getScript(), TimeUnit.SECONDS);
    }

  }

  private static AppiumDriver<MobileElement> createLocalDriver()
      throws MalformedURLException {
    Configuration configuration = ConfigurationHelper.INSTANCE.getConfiguration();
    AppiumDriver<MobileElement> appiumDriver;
    URL url = new URL(configuration.getWebDriverConfiguration().getHubUrl());
    DesiredCapabilities capabilities = new DesiredCapabilities();
    if (configuration.getWebDriverConfiguration().isAndroid()) {
      capabilities.merge(getDefaultAndroidCapabilities());
      capabilities.merge(getAndroidAppCapabilities());
      appiumDriver = new AndroidDriver<MobileElement>(url, capabilities);
    } else {
      capabilities.merge(getDefaultIosCapabilities());
      capabilities.merge(getIosAppCapabilities());
      appiumDriver = new IOSDriver<MobileElement>(url, capabilities);
    }
    return appiumDriver;
  }

  private static AppiumDriver<MobileElement> createTestiniumDriver()
      throws MalformedURLException {
    AppiumDriver<MobileElement> appiumDriver;
    URL url = new URL("http://hub.testinium.io/wd/hub");
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("key", System.getenv("key"));
    if (System.getenv("platform").equals("ANDROID")) {
      capabilities.merge(getDefaultAndroidCapabilities());
      capabilities.merge(getAndroidAppCapabilities());
      appiumDriver = new AndroidDriver<MobileElement>(url, capabilities);
    } else {
      capabilities.merge(getDefaultIosCapabilities());
      capabilities.merge(getIosAppCapabilities());
      appiumDriver = new IOSDriver<MobileElement>(url, capabilities);
    }

    return appiumDriver;
  }


  private static DesiredCapabilities getDefaultAndroidCapabilities() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
    capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
    capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
    capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, false);
    capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, false);
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
        "uiautomator2");
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
        "emulator-5455");
    return capabilities;
  }

  private static DesiredCapabilities getDefaultIosCapabilities() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.PLATFORM, Platform.MAC);
    capabilities
        .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3.5");
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone5");
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
    capabilities
        .setCapability(MobileCapabilityType.UDID, "6cb0c419906a1e0bc9b5db9a018446a2f4a17c10");
    capabilities.setCapability(IOSMobileCapabilityType.WAIT_FOR_APP_SCRIPT, "$.delay(1000);");
    capabilities.setCapability("xcodeOrgId", "PMLH8MF4G9");
    capabilities.setCapability("xcodeSigningId", "iPhone Developer");
    return capabilities;
  }

  private static DesiredCapabilities getAndroidAppCapabilities() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    Configuration configuration = ConfigurationHelper.INSTANCE.getConfiguration();
    if (configuration.getWebDriverConfiguration().isMobileWeb()) {
      capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
      capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
      capabilities.setCapability("autoAcceptAlerts", true);
      capabilities.setCapability("autoDismissAlerts", true);
      capabilities.setCapability("disable-popup-blocking", false);
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--disable-notifications");
      options.addArguments("test-type");
      options.addArguments("disable-popup-blocking");
      options.addArguments("ignore-certificate-errors");
      options.addArguments("disable-translate");
      options.setExperimentalOption("androidPackage", "com.android.chrome");
      capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    } else {
      capabilities
          .setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
              configuration.getEnvironment().getMobileEnvironment().getSelectedEnv()
                  .getAppPackage());
      capabilities
          .setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
              configuration.getEnvironment().getMobileEnvironment().getSelectedEnv()
                  .getAppActivity());
    }
    return capabilities;
  }

  private static DesiredCapabilities getIosAppCapabilities() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    Configuration configuration = ConfigurationHelper.INSTANCE.getConfiguration();
    if (configuration.getWebDriverConfiguration().isMobileWeb()) {
      capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.SAFARI);
      capabilities.setCapability(MobileCapabilityType.ACCEPT_SSL_CERTS, "true");
      capabilities.setCapability(IOSMobileCapabilityType.NATIVE_WEB_TAP, true);
      capabilities.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
      capabilities.setCapability("safari.ensureCleanSession", true);
      capabilities.setCapability("preventWDAAttachments", true);
      capabilities.setCapability(IOSMobileCapabilityType.SAFARI_INITIAL_URL,
          configuration.getEnvironment().getWebEnvironment().getSelectedEnv());
    } else {
      capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID,
          configuration.getEnvironment().getMobileEnvironment().getSelectedEnv().getAppPackage());
    }
    return capabilities;
  }
}
