package com.automation.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.options.BaseOptions;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.automation.configuration.ConfigManager;

public class DriverFactory {
	private static DriverFactory instance = null;
	String platform;
	String device_name;
	String app;
	String timeout;
	String executionMode;
	String host;
	String port;
	String parallel;

	private DriverFactory() {
	}

	public static DriverFactory getInstance() {
		if (instance == null) {
			instance = new DriverFactory();
		}
		return instance;
	}

	public AppiumDriver createDriver() throws MalformedURLException {
		AppiumDriver driver = null;
		platform = ConfigManager.get("execution", "platform");
		device_name = ConfigManager.get("platform_config", platform, "device_name");
		app = ConfigManager.get("platform_config", platform, "app_path");
		timeout = ConfigManager.get("timeouts", "implicit");
		host = ConfigManager.get("server", "local", "host");
		port = (ConfigManager.get("server", "local", "port"));
		executionMode = ConfigManager.get("execution", "mode");
		 parallel = ConfigManager.get("execution", "parallel");
		if (executionMode.equalsIgnoreCase("local")) {
			if (platform.equalsIgnoreCase("android")) {
				UiAutomator2Options options = new UiAutomator2Options();
				options.setDeviceName(device_name);
				options.setApp(app);
				options.setFullReset(true);
				URL url = URI.create("http://" + host + ":" + port).toURL();
				driver = new AndroidDriver(url, options);

			} else if (platform.equalsIgnoreCase("android")) {
			} else {
				throw new IllegalArgumentException("Execution platform is not valid");
			}

		} else if (executionMode.equalsIgnoreCase("cloud")) {

			String bsUrl = ConfigManager.get("cloud", "browserstack", "url");
			String username = ConfigManager.get("cloud", "browserstack", "username");
			String accessKey = ConfigManager.get("cloud", "browserstack", "access_key");

			String strategy = ConfigManager.get("cloud", "browserstack", "device_pool", "device_strategy");
			String deviceType = ConfigManager.get("cloud", "browserstack", "device_pool", "device_type");
			int deviceCount = Integer.parseInt(
					ConfigManager.get("cloud", "browserstack", "device_pool", "device_count"));
			List<Map<String, String>> executionPool = new ArrayList<>();
			if (strategy.equalsIgnoreCase("single")) {

				String platform = deviceType;

				String device = ConfigManager.get(
						"cloud", "browserstack", "device_pool", platform, "0");

				String osVersion = ConfigManager.get(
						"cloud", "browserstack", "os_versions", platform, "0");

				executionPool.add(Map.of(
						"platform", platform,
						"device", device,
						"os", osVersion));
			}



		} else {
			throw new IllegalArgumentException("Execution Mode is not valid");
		}

		return driver;
	}
}
