package com.automationexercises.listeners;

import com.automationexercises.FileUtils;
import com.automationexercises.drivers.UITest;
import com.automationexercises.drivers.WebDriverProvider;
import com.automationexercises.media.ScreenRecordManager;
import com.automationexercises.media.ScreenshotsManager;
import com.automationexercises.report.AllureAttachmentManager;
import com.automationexercises.report.AllureConstants;
import com.automationexercises.report.AllureEnvironmentManger;
import com.automationexercises.report.AllureReportGenerator;
import com.automationexercises.utils.dataReader.PropertyReader;
import com.automationexercises.utils.logs.LogsManager;
import com.automationexercises.validations.Validation;

import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;

public class TestNGListeners implements IExecutionListener,IInvokedMethodListener,ITestListener {
    @Override
    public void onExecutionStart() {
        LogsManager.info("TestNG execution started.");
        cleanTestOutputDirectories();
        LogsManager.info("directories cleaned.");
        createTestOutputDirectories();
        LogsManager.info("directories created.");
        PropertyReader.loadProperties();
        LogsManager.info("Properties loaded.");
        AllureEnvironmentManger.setEnvironmentVariables();
        LogsManager.info("Allure environment variables set.");
    }

    @Override
    public void onExecutionFinish() {
        AllureReportGenerator.generateReports(false);
        AllureReportGenerator.copyHistory();
        AllureReportGenerator.generateReports(true);
        AllureReportGenerator.openReport(AllureReportGenerator.renameReport());
        LogsManager.info("TestNG execution finished.");
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            if(testResult.getInstance() instanceof UITest){
                ScreenRecordManager.startRecording();
            }
            LogsManager.info("Test Case " + testResult.getName() + " started.");
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver=null;
          if (method.isTestMethod()) {
              if(testResult.getInstance() instanceof UITest){
                  ScreenRecordManager.stopRecording(testResult.getName());
                  if (testResult.getInstance() instanceof WebDriverProvider provider)
                      driver=provider.getWebDriver();
                  switch (testResult.getStatus()) {
                      case ITestResult.SUCCESS -> ScreenshotsManager.takeFullPageScreenshot(driver,"passed-" + testResult.getName());
                      case ITestResult.FAILURE -> ScreenshotsManager.takeFullPageScreenshot(driver,"failed-" + testResult.getName());
                      case ITestResult.SKIP -> ScreenshotsManager.takeFullPageScreenshot(driver,"skipped-" + testResult.getName());
                  }
                  AllureAttachmentManager.attachRecords(testResult.getName());

              }

              Validation.assertAll(testResult);

              AllureAttachmentManager.attachLogs();
          }
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        LogsManager.info("Test Case " + testResult.getName() + " passed.");
    }
    @Override
    public void onTestFailure(ITestResult testResult) {
        LogsManager.info("Test Case " + testResult.getName() + " failed.");
    }
    @Override
    public void onTestSkipped(ITestResult testResult) {
        LogsManager.info("Test Case " + testResult.getName() + " skipped.");
    }
   // cleaning and creating dirs
    private void cleanTestOutputDirectories(){
        // Implement logic to clean test output directories
        FileUtils.cleanDirectory(AllureConstants.RESULTS_FOLDER.toFile());
        FileUtils.cleanDirectory(new File(ScreenshotsManager.SCREENSHOTS_PATH));
        FileUtils.cleanDirectory(new File(ScreenRecordManager.RECORDINGS_PATH));
        FileUtils.cleanDirectory(new File("src/test/resources/downloads/"));
        FileUtils.forceDelete(new File(LogsManager.LOGS_PATH+File.separator+"logs.log"));


    }
    private void createTestOutputDirectories(){
        // Implement logic to clean test output directories
        FileUtils.createDirectory(ScreenshotsManager.SCREENSHOTS_PATH);
        FileUtils.createDirectory(ScreenRecordManager.RECORDINGS_PATH);
        FileUtils.createDirectory("src/test/resources/downloads/");

    }


}
