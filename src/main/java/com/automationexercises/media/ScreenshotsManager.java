package com.automationexercises.media;
import com.automationexercises.utils.TimeManager;
import com.automationexercises.utils.logs.LogsManager;
import com.automationexercises.utils.report.AllureAttachmentManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotsManager {

    public static final String SCREENSHOTS_PATH = "test-output/screenshots/";

    public static void takeFullPageScreenshot(WebDriver driver, String screenshotName) {
        try {
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + "-" + TimeManager.getTimestamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);
            AllureAttachmentManager.attachScreenshot(screenshotName,screenshotFile.getAbsolutePath());
            LogsManager.info("Capturing Screenshot Succeeded");
        } catch (Exception e) {
            LogsManager.error("Failed to Capture Screenshot " + e.getMessage());
        }
    }

    public static void takeElementScreenshot(WebDriver driver, By elementSelector) {
        try {
            String ariaName = driver.findElement(elementSelector).getAccessibleName();
            File screenshotSrc = driver.findElement(elementSelector).getScreenshotAs(OutputType.FILE);

            File screenshotFile = new File(SCREENSHOTS_PATH + ariaName + "-" + TimeManager.getTimestamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);

            LogsManager.info("Capturing Screenshot Succeeded");
        } catch (Exception e) {
            LogsManager.error("Failed to Capture Element Screenshot" , e.getMessage());
        }
    }
}
