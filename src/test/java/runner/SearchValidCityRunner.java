package runner;

import java.io.File;

import org.junit.AfterClass;
import org.testng.annotations.Listeners;

import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import environment.CustomListener;

@CucumberOptions(
        features = {"src/test/java/features/SearchValidCity.feature"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
        glue = "steps")

@Listeners(value = CustomListener.class)
public class SearchValidCityRunner extends AbstractTestNGCucumberTests {

    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/src/main/resources/extent-config.xml"));
        Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        Reporter.setSystemInfo("Machine", 	"Mac OS" + "64 Bit");
    }
}

