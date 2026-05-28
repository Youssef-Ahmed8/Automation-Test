package com.automationexercises;
import com.automationexercises.utils.dataReader.PropertyReader;
import com.automationexercises.utils.logs.LogsManager;

import java.io.File;
import java.io.IOException;

import static org.aspectj.util.FileUtil.copyFile;

public class FileUtils {
    private static final String USER_DIR= PropertyReader.getProperty("user.dir")+File.separator;
    private FileUtils() {
    }
    //Renaming
    public static void renameFile(String oldName, String newName) {
        try {
            var targetFile = new File(oldName);
            String targetDirectory = targetFile.getParentFile().getAbsolutePath();

            File newFile = new File(targetDirectory + File.separator + newName);

            if (!targetFile.getPath().equals(newFile.getPath())) {
                copyFile(targetFile, newFile);
                org.apache.commons.io.FileUtils.deleteQuietly(targetFile);

                LogsManager.info(
                        "Target File Path: \"" + oldName + "\", file was renamed to \"" + newName + "\"."
                );
            } else {
                LogsManager.info(
                        "Target File Path: \"" + oldName + "\", already has the desired name \"" + newName + "\"."
                );
            }

        } catch (IOException e) {
            LogsManager.error(e.getMessage());
        }
    }

    static void main() {
        // Example usage
        renameFile("oldFile.txt", "newFile.txt");
    }




    //creating directory
    public static void createDirectory(String path) {
        try {
            File file = new File(USER_DIR + path);
            if (!file.exists()) {
                file.mkdirs();
                LogsManager.info("Directory created at: " + path);
            }
        } catch (Exception e) {
            LogsManager.error("Error occurred while creating directory: " + path, e.getMessage());
        }
    }



    //clean directory
    public static void cleanDirectory(File file) {
        try {
            org.apache.commons.io.FileUtils.deleteQuietly(file);
        } catch (Exception e) {
            LogsManager.error("Error occurred while cleaning directory: " + file.getAbsolutePath(), e.getMessage());
        }
    }
    //force delete
    public static void forceDelete(File file) {
        try {
            org.apache.commons.io.FileUtils.forceDelete(file);
            LogsManager.info("File force deleted: " + file.getAbsolutePath());
        } catch (IOException e) {
            LogsManager.error("Error occurred while force deleting file: " + file.getAbsolutePath(), e.getMessage());
        }
    }
   //check if the file exists
    public static boolean isFileExists(String fileName) {
        String downloadsPath = System.getProperty("user.dir") + File.separator +"/src/test/resources/";
        File file = new File(downloadsPath + fileName);
        return file.exists();
    }
}
