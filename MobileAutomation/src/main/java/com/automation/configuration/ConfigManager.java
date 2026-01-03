package com.automation.configuration;

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public final class ConfigManager {

	private static Map<String, Object> config;

	private ConfigManager() {
	}

	public static void load(String yamlName) {

		if (config == null) {
			Yaml yaml = new Yaml();
			InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(yamlName);
			if (input == null) {
				throw new RuntimeException("Config file not found: " + yamlName);
			}
			config = yaml.load(input);
		}

	}

	@SuppressWarnings("unchecked")
	public static String get(String... keys) {

		Object value = config;

		for (String key : keys) {

			if (!(value instanceof Map)) {
				throw new RuntimeException("Invalid config path: " + String.join(".", keys));
			}

			value = ((Map<String, Object>) value).get(key);
		}

		return String.valueOf(value);
	}
}
