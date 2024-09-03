package Practice.SeleniumWithBuilderPattern;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/java/Practice/SeleniumWithBuilderPattern/feature",
        tags = "@TestNew",
        monochrome = true,
        plugin = {
        "pretty", "html:target/html",
        "json:target/cucumber.json",
        "rerun:target/rerun.txt"},
        glue = "Practice.SeleniumWithBuilderPattern.stepDefiition")
public class TestRunner extends AbstractTestNGCucumberTests {
}
