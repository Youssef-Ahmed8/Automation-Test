package com.automationexercises.report;

import com.automationexercises.utils.dataReader.PropertyReader;
import com.automationexercises.utils.logs.LogsManager;
import com.google.common.collect.ImmutableMap;

import java.io.File;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class AllureEnvironmentManger {
    public static void setEnvironmentVariables() {

        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", PropertyReader.getProperty("os.name"))
                        .put("Java Version", PropertyReader.getProperty("java.runtime.version"))
                        .put("Browser", PropertyReader.getProperty("browserType"))
                        .put("Execution Type", PropertyReader.getProperty("executionType"))
                        .put("URL", PropertyReader.getProperty("baseUrlWeb"))
                        .build(),
                String.valueOf(AllureConstants.RESULTS_FOLDER) + File.separator
        );

        LogsManager.info("Allure environment variables set.");
        AllureBinaryManager.downloadAndExtract();
    }
}
