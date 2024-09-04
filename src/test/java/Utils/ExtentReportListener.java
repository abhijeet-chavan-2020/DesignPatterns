package Utils;

import Practice.SeleniumWithBuilderPattern.DriverGenerator.AppTestForBuilderPattern;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ExtentReportListener extends AppTestForBuilderPattern {

    public static ExtentReports getExtent() {
        return extent;
    }

    public static ExtentTest getFeature() {
        return feature;
    }

    public static ExtentTest getScenario() {
        return scenario;
    }

    private static ExtentReports extent;

    public static void setFeature(ExtentTest feature) {
        ExtentReportListener.feature = feature;
    }

    private static ExtentTest feature;
    private static ExtentTest scenario;

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Into ExtentReportListener and setup");
        if (feature == null) {
            feature = extent.createTest("Feature Name");
        }
        ExtentReportListener.scenario = getFeature().createNode(scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        System.out.println("Into ExtentReportListener and after step");

        if (scenario.isFailed()) {
            ExtentReportListener.scenario.fail("Step failed: " + scenario.getName());
        } else {
            String stepInfo = scenario.getName();
            System.out.println("stepInfo = " + stepInfo);
            ExtentReportListener.scenario.pass("Step passed: " + scenario.getName());
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            ExtentReportListener.scenario.fail("Scenario failed: " + scenario.getName());
        } else {
            ExtentReportListener.scenario.pass("Scenario passed: " + scenario.getName());
        }
    }

    public static void initReport() {
        System.out.println("Into ExtentReportListener and Initializing Report");

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-reports/extent-report.html");
        sparkReporter.config().setDocumentTitle("Cucumber Automation Report");
        sparkReporter.config().setReportName("Cucumber Test Report");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        feature = extent.createTest("Default Feature"); // Initialize here if needed

    }

    public static void flushReport() {
        System.out.println("Into ExtentReportListener and flushReport Report");

        if (extent != null) {
            extent.flush();
        }
    }
}
