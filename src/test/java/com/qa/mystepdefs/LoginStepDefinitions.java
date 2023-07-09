package com.qa.mystepdefs;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.DriverFactory;
import utils.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

import static variables.GlobalVariables.ScenarioName;
import static variables.GlobalVariables.skipStatusFlag;

public class LoginStepDefinitions {

    private WebDriver driver=DriverFactory.getDriver();
    private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

    public String scenarioID="";
    Date ScenarioTimeStart=null;
    Date ScenarioTimeEnd=null;
    SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy HH:MM:SS.SSSS");
    SimpleDateFormat dateformat2 = new SimpleDateFormat("HH:mm:ss.SSSS");
    boolean tableHeaderWrote=false;
    String projectPath=System.getProperty("user.dir");

//    @Before(order=1)
//    public void setup(){
//        driver = new ChromeDriver();
//    }
//
//    @Before(order=2)
//    public void beforeScenario(Scenario scenario) {
//        ScenarioTimeStart= new Date();
//        ScenarioName=scenario.getName();
//        //check feature name is equivalent to current feature name
////	if(ScenarioName==null) {
////		ScenarioName=currentScenarioName;
////		Log.info("Scenario Name :"+ScenarioName);
////	}else if(!ScenarioName.equals(currentScenarioName)) {
////		ScenarioName=currentScenarioName;
////		Log.info("Scenario Name :"+ScenarioName);
////	}else {
////		Log.info("Scenario Name :"+ScenarioName);
////	}
//        Log.info("Scenario Name :"+ScenarioName);
//        System.out.println("Running scenario : "+scenario.getName());
////	scenarioVal=scenario;
//
//        //scenarioID =scenario.getName().substring(0,scenario.getName().length());
//        //Log.debug("<p style=color:Tomato;><b> ScenarioName :-</p> " + "<p style=color:green;><b>" + ScenarioName+ "</p>");
//    }
//
//
//    @After(order=1)
//    public void tearDown(){
//        if(driver!=null){
//            driver.quit();
//        }
//    }
//    @After(order=2)
//    public void afterScenario(Scenario scenario) {
//        ScenarioTimeEnd=new Date();
//        Log.info(scenario.getName());
//        Log.info("Status :"+scenario.getStatus());
//
//        // if status of the scenario execution is skipped
//        if(!skipStatusFlag) {
//            Log.endTestScript(scenario.getName(), scenario.getStatus().toString());
//        }else {
//            Log.endTestScript(scenario.getName(), "SKIPPED");
//            skipStatusFlag=false;
//        }
//        String s=scenario.getStatus().toString();
//        System.out.println("\n\nScenario execution status :"+s+"\n\n");
//
//
//        //if status of scenario execution failed
//        if(scenario.isFailed()) {
//            String screenshotName=scenario.getName().replaceAll(" ", "_");
//            try {
//                //take screenshot to specified location
//                File sourcepath=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//
//                //destination path to store screenshot
//                File destinationPath=new File(projectPath+"/ExecutionReports/screenshots/"+screenshotName+".png");
//                Log.info("Screenshot path "+destinationPath);
//
//
//                //copy screenshot from source to destination path
//                FileUtils.copyFile(sourcepath,destinationPath);
//                scenario.attach(FileUtils.readFileToByteArray(destinationPath), "image/png",screenshotName);
//                FileInputStream is= new FileInputStream(destinationPath);
//                byte[] imageBytes= IOUtils.toByteArray(is);
//                String base64= Base64.encodeBase64String(imageBytes);
//                //Reporter.addScreenCaptureFromPath("data:image/png;base64,"+base64);
//                //Reporter.addScreenCaptureFromPath(projectPath+"/ExecutionReports/screenshots/"+screenshotName+".jpg");
//            }catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
////	long difference=(ScenarioTimeEnd.getTime()- ScenarioTimeStart.getTime())/1000;
////	String formattedStatus="";
////	if(scenario.getStatus().toString().equalsIgnoreCase("passed"))
////		formattedStatus="<b>font style='color:green'>PASSED</font></b>";
////	else
////		formattedStatus="<b>font style='color:red'>FAILED</font></b>";
////	tableDataOutput+=
////	"          <tr>\r\n"+
////	"          <td>"+scenario.getSourceTagNames()+"</td>\r\n"+
////	"          <td>"+scenario.getName()+"</td>\r\n"+
////	"          <td>"+formattedStatus+"</td>\r\n"+
////	"          <td>"+dateformat.format(ScenarioTimeStart)+"</td>\r\n"+
////	"          <td>"+dateformat.format(ScenarioTimeEnd)+"</td>\r\n"+
////	"          <td>"+difference+" s</td>\r\n"+
////	"          </tr>\r\n";
//
//
//    }
//    @AfterStep
//    public void addScreenshot(Scenario scenario) throws IOException {
//        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
//        scenario.attach(fileContent, "image/png", "screenshot");
//
//    }

    @Given("I am on the OrangeHRM login page")
    public void i_am_on_the_orange_hrm_login_page() {
       driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Log.info("I am on the OrangeHRM login page");
    }

    @Given("I have entered a valid username and password")
    public void i_have_entered_a_valid_username_and_password() {

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        Log.info("Valid username and password entered successfully");
    }

    @Given("I have entered invalid {string} and {string}")
    public void i_have_entered_invalid_and(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Log.info("Invalid username "+username+" and password "+password+" entered successfully");
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
       Assert.assertEquals(loginPage.checkDashboardPage(), true);
    }



    @Then("I should see an error message indicating {string}")
    public void i_should_see_an_error_message_indicating(String errorMessage) {
        // Assert that an error message is displayed on the page matching the expected error message
        Assert.assertEquals( driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).isDisplayed(), true);
        Log.info("Error message appear as "+errorMessage);
    }

}

