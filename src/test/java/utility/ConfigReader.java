package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static String getValue(String key) {
		if(key == null || key.isEmpty()) {
			throw new RuntimeException("Key is null or empty.");
		}
		Properties properties = null;
		FileInputStream fis = null;
		try {
			String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties";
			fis = new FileInputStream(filePath);
			properties = new Properties();
			properties.load(fis);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("config.properties file not found." + e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException("Unable to read config.properties file." + e.getMessage());
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				throw new RuntimeException("Unable to close the config.properties file." + e.getMessage());
			}
		}
		return properties.getProperty(key);
	}
}