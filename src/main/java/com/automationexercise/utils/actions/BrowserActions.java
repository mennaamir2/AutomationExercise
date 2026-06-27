package com.automationexercise.utils.actions;
import com.automationexercise.utils.WaitManager;
import com.automationexercise.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class BrowserActions {
    private final WebDriver driver;
    private WaitManager waitManager;
    public BrowserActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        LogsManager.info("Current URL: " + url);
        return url;
    }

    public void navigateTo(String url) {
        driver.get(url);
        LogsManager.info("Navigated to URL: " + url);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void closeCurrentWindow() {
        driver.close();
    }

    public void openNewWindow() {
        driver.switchTo().newWindow(WindowType.WINDOW);
    }

    public void closeExtensionTab() {
        String currentWindowHandle = driver.getWindowHandle(); //0 1
        waitManager.fluentWait().until(
                d ->
                {
                    return d.getWindowHandles().size() > 1; //wait until extension tab is opened
                }
        );
        for (String windowHandle : driver.getWindowHandles()) //extension tab is opened
        {
            if (!windowHandle.equals(currentWindowHandle))
                driver.switchTo().window(windowHandle).close(); //close the extension tab
        }
        driver.switchTo().window(currentWindowHandle); //switch back to the main window
        LogsManager.info("Extension tab closed");
    }


}