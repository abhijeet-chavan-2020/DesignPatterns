package Practice.SeleniumWithBuilderPattern.stepDefiition;

import Practice.SeleniumWithBuilderPattern.PageObject.BasePage;
import Practice.SeleniumWithBuilderPattern.DriverGenerator.WebDriverManagerForBuilderPattern;
import io.cucumber.java.After;

public class BasePageStepDef  {
    BasePage basePage ;
    public BasePageStepDef() {
        basePage= new BasePage();
    }

    @After
    public void tearDown(){

        WebDriverManagerForBuilderPattern.quitBrowser();
    }
}
