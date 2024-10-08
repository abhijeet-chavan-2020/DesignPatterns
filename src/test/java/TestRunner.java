import com.aventstack.extentreports.ExtentTest;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import Utils.*;

@CucumberOptions(
        features = "src/test/java/feature/permissionsTest.feature",
        tags = "@PermissionTest",
        plugin = {
        "pretty", "html:target/cucumber-html-report.html",
        "json:target/cucumber.json",
        "rerun:target/rerun.txt"},
        glue = {"Practice.SeleniumWithBuilderPattern.stepDefiition","test.java.Utils", "Practice/SeleniumPractice/BrowserPermissionPopUps/stepDefinition"})
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void beforeSuite() {
        ExtentReportListener.initReport();
        ExtentReportListener.setFeature( ExtentReportListener.getExtent().createTest("Feature Name"));  // Initialize the feature here
    }

    @AfterSuite
    public void afterSuite() {
        ExtentReportListener.flushReport();
    }
}
