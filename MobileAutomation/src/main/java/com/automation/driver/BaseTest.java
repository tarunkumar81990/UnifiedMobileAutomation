package com.automation.driver;

import com.automation.configuration.ConfigManager;
import com.automation.pages.AndroidLandingPage;
import com.automation.pages.IOSLandingPage;
import com.automation.pages.LandingPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	public static AppiumDriver driver;
	AppiumDriverLocalService service;
	String host;
	String port;
	String executionMode;

	public static LandingPage landingPage;

	public void initializeDriver() throws Exception {

		ConfigManager.load("config.yaml");
		host = ConfigManager.get("server", "local", "host");
		port = (ConfigManager.get("server", "local", "port"));
		executionMode = ConfigManager.get("execution", "mode");
		if (executionMode.equalsIgnoreCase("local")) {
			startAppium();
		}
		driverSetUp();

	}

	public void initPages() {
		if (driver instanceof AndroidDriver) {
			landingPage = new AndroidLandingPage(driver);

		} else if (driver instanceof IOSDriver) {

			landingPage = new IOSLandingPage(driver);

		} else {
			throw new RuntimeException("Unsupported platform");
		}
	}

	void driverSetUp() throws Exception {
		AppiumDriver driverObj = DriverFactory.getInstance().createDriver();
		DriverManager.setDriver(driverObj);
		driver = DriverManager.getDriver();
	}

	public void startAppium() {
		service = new AppiumServiceBuilder().withIPAddress(host).usingPort(Integer.parseInt(port)).build();
		service.start();

	}

	public void stopAppium() {
		service.stop();
	}

	public void tearDown() {
		if (DriverManager.getDriver() != null) {
			DriverManager.getDriver().quit();
		}
		DriverManager.unloadDriver();
		if (executionMode.equalsIgnoreCase("local")) {
			stopAppium();
		}
	}
}
