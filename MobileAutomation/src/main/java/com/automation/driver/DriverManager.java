package com.automation.driver;

import io.appium.java_client.AppiumDriver;

public class DriverManager {

	private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

	public static AppiumDriver getDriver() {

		return driver.get();
	}

	public static void setDriver(AppiumDriver driverObj) {
		driver.set(driverObj);
	}

	public static void unloadDriver() {
		driver.remove();
	}
}
