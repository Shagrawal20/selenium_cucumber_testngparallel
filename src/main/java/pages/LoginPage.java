package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(passwordTextbox);
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(loginButtonloc);
        loginButton.click();
    }

    public void clickForgottenPasswordLink() {
        WebElement forgottenPasswordLink = driver.findElement(forgottenPasswordLinkLocator);
        forgottenPasswordLink.click();
    }

    public boolean checkForgotPwdLink() {
        return driver.findElement(forgottenPasswordLinkLocator).isDisplayed();
    }

    public boolean checkDashboardPage() {
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
