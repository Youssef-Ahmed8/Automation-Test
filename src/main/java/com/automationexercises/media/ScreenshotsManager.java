package com.automationexercises.media;

import com.automationexercises.report.AllureAttachmentManager;
import com.automationexercises.utils.TimeManger;
import com.automationexercises.utils.logs.LogsManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotsManager {
public static final String SCREENSHOTS_PATH="test-output/screenshots/";
    // take full page screenshot
    public static void takeFullPageScreenshot(WebDriver driver, String screenshotName) {

        try {
            // Capture screenshot using TakesScreenshot
            File screenshotSrc =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Save screenshot to a file if needed
            File screenshotFile =
                    new File(SCREENSHOTS_PATH + screenshotName + "-" + TimeManger.getTimeStamp() + ".png");

            FileUtils.copyFile(screenshotSrc, screenshotFile);

            AllureAttachmentManager.attachScreenshot(screenshotName, screenshotFile.getAbsolutePath());
            LogsManager.info("Capturing Screenshot Succeeded");

        } catch (Exception e) {
            LogsManager.error("Failed to capture screenshot " + e.getMessage());
        }
    }
    // take highlight element screenshot
    public static void takeElementScreenshot(WebDriver driver, By elementSelector) {

        try {
            // Capture screenshot using TakesScreenshot
            String ariaName=driver.findElement(elementSelector).getAccessibleName();
            File screenshotSrc = driver.findElement(elementSelector).getScreenshotAs(OutputType.FILE);
            // Save screenshot to a file if needed
            File screenshotFile = new File(SCREENSHOTS_PATH + ariaName + "-" + TimeManger.getTimeStamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);
            // TODO: Attach the screenshot to Allure report
            LogsManager.info("Capturing Screenshot Succeeded");

}
        catch (Exception e) {
            LogsManager.error("Failed to capture element screenshot " + e.getMessage());
        }
    }
}