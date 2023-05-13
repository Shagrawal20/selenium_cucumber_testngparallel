package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Log;

public class LoginPage {

    WebDriver driver;

    // Locators
    By userenameTextbox = By.name("username");
    By passwordTextbox = By.name("password");
    By loginButtonloc = By.xpath("//*[@type='submit']");
    By forgottenPasswordLinkLocator=By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']");
    By dashboardPageloc = By.xpath("//*[text()='Time at Work']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void enterUsername(String username) {
        WebElement usernameInput = driver.findElement(userenameTextbox);
        usernameInput.sendKeys(username);
        Log.info("Enter Username");
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(passwordTextbox);
        passwordInput.sendKeys(password);
        Log.info("Enter password");
    }

    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(loginButtonloc);
        loginButton.click();
        Log.info("Click login button");
    }

    public void clickForgottenPasswordLink() {
        WebElement forgottenPasswordLink = driver.findElement(forgottenPasswordLinkLocator);
        forgottenPasswordLink.click();
    }

    public boolean checkForgotPwdLink() {
        return driver.findElement(forgottenPasswordLinkLocator).isDisplayed();
    }

    public boolean checkDashboardPage() {
        Log.info("Dashboard page is displayed");
        return driver.findElement(dashboardPageloc).isDisplayed();
    }


    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getForgotPwdPageUrl() {
        String forgotPwdPageUrl = driver.getCurrentUrl();
        return forgotPwdPageUrl;
    }


}
