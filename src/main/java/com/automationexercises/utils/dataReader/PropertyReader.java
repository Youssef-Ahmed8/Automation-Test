package com.automationexercises.utils.dataReader;

import com.automationexercises.utils.logs.LogsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.Properties;

public class PropertyReader {

    static {
        loadProperties();
    }

    public static Properties loadProperties() {
        try {
            Properties properties = new Properties();

            Collection<File> propertiesFiles = FileUtils.listFiles(
                    new File("src/main/resources"),
                    new String[]{"properties"},
                    true
            );

            propertiesFiles.forEach(file -> {
                try {
                    properties.load(FileUtils.openInputStream(file));
                } catch (Exception e) {
                    LogsManager.error("Error loading properties file: " + file.getName() + " - " + e.getMessage());
                }
            });

            properties.putAll(System.getProperties());
            System.getProperties().putAll(properties);

            return properties;

        } catch (Exception e) {
            LogsManager.error("Error loading properties files: " + e.getMessage());
            return null;
        }
    }

    public static String getProperty(String key) {
        return System.getProperty(key);
    }
}