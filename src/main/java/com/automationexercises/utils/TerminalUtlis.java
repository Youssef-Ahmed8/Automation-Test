package com.automationexercises.utils;

import com.automationexercises.utils.logs.LogsManager;

import java.io.IOException;

public class TerminalUtlis {
    public static void executeTerminalCommand(String... commandsParts) {
        try {
            Process process = Runtime.getRuntime().exec(commandsParts);
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                LogsManager.error("Command failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            LogsManager.error("Error occurred while executing command: " + String.join(" ",commandsParts), e.getMessage());
        }
    }

}
