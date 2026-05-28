package com.automationexercises.report;

import com.automationexercises.utils.OSUtils;
import com.automationexercises.utils.TerminalUtlis;
import com.automationexercises.utils.TimeManger;
import com.automationexercises.utils.dataReader.PropertyReader;
import com.automationexercises.utils.logs.LogsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.automationexercises.report.AllureConstants.HISTORY_FOLDER;
import static com.automationexercises.report.AllureConstants.RESULTS_HISTORY_FOLDER;
import static com.automationexercises.utils.TerminalUtlis.executeTerminalCommand;
import static com.automationexercises.utils.dataReader.PropertyReader.getProperty;

public class AllureReportGenerator {
    //Generate Allure report
    public static void generateReports(boolean isSingleFile) {

        Path outputFolder =
                isSingleFile
                        ? AllureConstants.REPORT_PATH
                        : AllureConstants.FULL_REPORT_PATH;

        List<String> command = new ArrayList<>(List.of(
                AllureBinaryManager.getExecutable().toString(),
                "generate",
                AllureConstants.RESULTS_FOLDER.toString(),
                "-o",
                outputFolder.toString(),
                "--clean"
        ));

        if (isSingleFile) {
            command.add("--single-file");
        }

        TerminalUtlis.executeTerminalCommand(
                command.toArray(new String[0])
        );
    }

    //rename report file to AllureReport_timestamp.html
    public static String renameReport() {

        String newFileName =
                AllureConstants.REPORT_PREFIX
                        + TimeManger.getTimeStamp()
                        + AllureConstants.REPORT_EXTENSION;

        com.automationexercises.FileUtils.renameFile(
                AllureConstants.REPORT_PATH
                        .resolve(AllureConstants.INDEX_HTML)
                        .toString(),
                newFileName
        );

        return newFileName;
    }


    //open Allure report in browser
    public static void openReport(String reportFileName) {

        if (!PropertyReader.getProperty("executionType").toLowerCase().contains("local")) {
            return;
        }

        Path reportPath = AllureConstants.REPORT_PATH.resolve(reportFileName);

        switch (OSUtils.getCurrentOS()) {

            case WINDOWS ->
                    TerminalUtlis.executeTerminalCommand(
                            "cmd",
                            "/c",
                            "start",
                            "",
                            reportPath.toAbsolutePath().toString()
                    );

            case MAC, LINUX ->
                    TerminalUtlis.executeTerminalCommand(
                            "open",
                            reportPath.toAbsolutePath().toString()
                    );

            default ->
                    LogsManager.warn("Opening Allure Report is not supported on this OS.");
        }
    }

    //copy history folder to results folder
    public static void copyHistory() {

        try {
            FileUtils.copyDirectory(
                    HISTORY_FOLDER.toFile(),
                    RESULTS_HISTORY_FOLDER.toFile()
            );

        } catch (Exception e) {
            LogsManager.error(
                    "Error copying history files "
                            + e.getMessage()
            );
        }
    }

}
