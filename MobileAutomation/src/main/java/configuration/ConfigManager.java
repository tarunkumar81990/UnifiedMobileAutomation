package configuration;

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public final class ConfigManager {

	private static Map<String, Object> config;

	private ConfigManager() {
	}

	public static void load(String yamlPath) {

		if (config == null) {
			Yaml yaml = new Yaml();
			InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(yamlPath);
			if (input == null) {
				throw new RuntimeException("Config file not found: " + yamlPath);
			}
			config = yaml.load(input);
		}

	}

	@SuppressWarnings("unchecked")
	public static Object get(String... keys) {

		Object value = config;

		for (String key : keys) {

			if (!(value instanceof Map)) {
				throw new RuntimeException("Invalid config path: " + String.join(".", keys));
			}

			value = ((Map<String, Object>) value).get(key);
		}

		return value;
	}
}
