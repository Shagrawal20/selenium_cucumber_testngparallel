package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static variables.GlobalVariables.*;

public class CommonFunctions {

    private static Duration maxLoadTime = Duration.ofSeconds(30);

    public static boolean waitForElementToLoad(WebElement webElement, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean waitForElementClickable(WebElement webElement, Duration timeToWait) {

        try {

            WebDriverWait wait = new WebDriverWait(driver, timeToWait);
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean waitForVisibilityOfElement(WebElement webElement, Duration timeToWait) {

        try {

            WebDriverWait wait = new WebDriverWait(driver, timeToWait);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean waitForframeToBeAvailableAndSwitchToIt(WebElement webElement, Duration timeToWait) {

        try {

            WebDriverWait wait = new WebDriverWait(driver, timeToWait);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean isElementDisplayed(WebElement webElement) {
        try {
            webElement.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public static boolean isElementSelected(WebElement webElement) {
        try {
            webElement.isSelected();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public static boolean isElementEnabled(WebElement webElement) {
        try {
            webElement.isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public static boolean isElementNotPresent(WebElement webElement) {
        try {
            webElement.isDisplayed();
            throw new RuntimeException();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public static String getAlertText(WebElement webElement) throws InterruptedException {
        waitForElementToLoad(webElement,maxLoadTime);
        Alert alert = driver.switchTo().alert();
        return alert.getText();

    }

    public static void clickAlert(WebElement webElement) throws InterruptedException {
        waitForElementToLoad(webElement,maxLoadTime);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public static void alertDismiss() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public static void SelectFromListByIndex(WebElement webElement,int index) throws InterruptedException {
        waitForElementToLoad(webElement,maxLoadTime);
        Select sel = new Select(webElement);
        sel.selectByIndex(index);
    }

    public static void selectFromListByVisibleText(WebElement webElement,String text) throws InterruptedException {
        waitForElementToLoad(webElement,maxLoadTime);
        Select sel = new Select(webElement);
        sel.selectByVisibleText(text);
    }

    public static String getFirstSelectedTxtFromList(WebElement webElement) throws InterruptedException {
        waitForElementToLoad(webElement,maxLoadTime);
        String selectedListTxt = new Select(webElement).getFirstSelectedOption().getText();
        return selectedListTxt;
    }

    public static List getAllListOptions(WebElement webElement) throws InterruptedException {
        List<WebElement> selectedListTxt = null;
        waitForElementToLoad(webElement,maxLoadTime);
        selectedListTxt = new Select(webElement).getOptions();
        return selectedListTxt;
    }
    public static void mouseOver(WebElement element) {
        JavascriptExecutor js = null;
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;

            String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
            js.executeScript(mouseOverScript, element);
        } else {
            Log.error("Not able to do mouse over action");
        }

    }

    public static void doubleClick(WebElement element) {
        JavascriptExecutor js = null;
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;

            String doubleClick = "if(document.createEvent){var evtObj = document.createEvent('MouseEvents');evtObj.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null); arguments[0].dispatchEvent(evtObj);}";
            js.executeScript(doubleClick, element);
        } else {
            Log.error("Not able to do double click action");
        }

    }

    public static void dragAndDrop(WebElement source) {
        JavascriptExecutor js = null;

        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;

            js.executeScript(
                    "function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; "
                            + "simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
                    source, 500, 500);
        } else {
            Log.error("Not able to do drag and drop");
        }
    }
    public static boolean switchToWindowByTitle(String title) {

        String parentWindowhandler = null;
        try {
            parentWindowhandler = driver.getWindowHandle();
        } catch (NoSuchWindowException e) {
            Log.error("There is no window available to switch");
        }
        boolean childWindowFlag = false;
        for (String Child_Window : driver.getWindowHandles()) {
            driver.switchTo().window(Child_Window);
            if (driver.getTitle().toString().isEmpty()) {
                childWindowFlag = true;
                break;
            }
            if (driver.getTitle().contains(title)) {
                childWindowFlag = true;
                break;
            }
        }
        if (!childWindowFlag) {
            driver.switchTo().window(parentWindowhandler);
            Log.error("The window having title " + title + " is not available");
        }
        return childWindowFlag;
    }

    public static void switchToFrameByWebelement(WebElement webElement) throws InterruptedException {
        waitForElementToLoad(webElement,maxLoadTime);
        driver.switchTo().frame(webElement);
    }

    public static void switchToMainFrame(WebElement webElement) throws InterruptedException {
        waitForElementToLoad(webElement,maxLoadTime);
        driver.switchTo().defaultContent();
    }
    public static void JSClick(WebDriver driver, WebElement webElement)
    {
        waitForElementToLoad(webElement,maxLoadTime);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", webElement);

    }
    public static String handleAlert(WebDriver driver,Alert alert)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        String alertTxt = alert.getText();
        alert.accept();

        return alertTxt;
    }

    public static int generateRandomNumber(int min, int max) {

        return (int) Math.round(Math.random() * (max - min) + min);
    }
    public static void doScroll(WebDriver driver, int heightPixel) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0," + heightPixel + ")");
    }

    public static void doScrollByWebelement(WebDriver driver, WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", webElement);
    }

}
