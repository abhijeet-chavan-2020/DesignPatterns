package Utils;

import Practice.SeleniumWithBuilderPattern.DriverGenerator.AppTestForBuilderPattern;
import Practice.SeleniumWithBuilderPattern.DriverGenerator.WebDriverManagerForBuilderPattern;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ExtentReportHooks extends AppTestForBuilderPattern {

    @AfterStep
    public void afterStep(Scenario scenario) {
        System.out.println("Into ExtentReportHooks and afterStep");

        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) WebDriverManagerForBuilderPattern.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }
    }
}

