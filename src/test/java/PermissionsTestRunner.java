import Utils.ExtentReportListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        features = "src/test/java/feature/permissionsTest.feature",
        tags = "@PermissionTest",
        plugin = {
        "pretty", "html:target/cucumber-html-report.html",
        "json:target/cucumber.json",
        "rerun:target/rerun.txt"},
        glue = {"test.java.Utils", "Practice/SeleniumPractice/BrowserPermissionPopUps/stepDefinition"})
public class PermissionsTestRunner extends AbstractTestNGCucumberTests {

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
