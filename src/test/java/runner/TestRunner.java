package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import utils.Log;
import static variables.GlobalVariables.*;
import java.io.File;
import java.io.IOException;



@CucumberOptions(features = "src/test/resources/features",
        glue = {"com.qa.mystepdefs"},
        tags = "@TC01",
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        publish = true)
public class TestRunner extends AbstractTestNGCucumberTests {

    public static TestNGCucumberRunner testRunner;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void setup() throws IOException {
        testRunner = new TestNGCucumberRunner(this.getClass());
        Log.info("Project path in runner is : " + projectPath);
       // Log.setLogDirectoryName();

    }

    @AfterClass
    public void tearDown() throws IOException {
        Log.info("End of execution for scenario: " + ScenarioName);
        Log.info("Driver is closed");
        //logFileName = Log.setLogfile(ScenarioName);
        //	Log.copyFile(logFileName);
        testRunner.finish();
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
