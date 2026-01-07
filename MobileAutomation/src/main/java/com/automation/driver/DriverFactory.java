package com.automation.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.options.BaseOptions;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
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
			String bsUrl = ConfigManager.get("cloud", "browserstack", "url");
			String username = ConfigManager.get("cloud", "browserstack", "username");
			String accessKey = ConfigManager.get("cloud", "browserstack", "access_key");
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

			String appId;
			String device;
			String osVersion;

			if (platform.equalsIgnoreCase("android")) {
				appId = ConfigManager.get("cloud", "browserstack", "app_id_android");
				device = ConfigManager.get("cloud", "browserstack", "device_pool", "0");
				osVersion = ConfigManager.get("cloud", "browserstack", "os_versions", "android", "0");
			} else {
				appId = ConfigManager.get("cloud", "browserstack", "app_id_ios");
				device = ConfigManager.get("cloud", "browserstack", "device_pool", "2");
				osVersion = ConfigManager.get("cloud", "browserstack", "os_versions", "ios", "0");
			}BaseOptions<?> options = new BaseOptions<>();

			options.setCapability("platformName", platform);
			options.setCapability("deviceName", device);
			options.setCapability("os_version", osVersion);
			options.setCapability("app", appId);

			Map<String, Object> bsOptions = new HashMap<>();
			bsOptions.put("userName", username);
			bsOptions.put("accessKey", accessKey);
			bsOptions.put("projectName", ConfigManager.get("project_name"));
			bsOptions.put("buildName", "Mobile Automation Build");
			bsOptions.put("sessionName", "Cloud Execution");

			options.setCapability("bstack:options", bsOptions);

			driver = new AppiumDriver(new URL(bsUrl), options);


		} else {
			throw new IllegalArgumentException("Execution Mode is not valid");
		}

		return driver;
	}
}
