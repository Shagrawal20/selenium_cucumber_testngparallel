package variables;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * This Class contains all the global variables set for the framework
 * 
 * 
 */
public class GlobalVariables {
    
	public static String featureName,currentScenarioName,ScenarioName,logFolderName,logFolder,logFileName;
    public static boolean skipStatusFlag=false;
    public static WebDriver driver = null;

}
