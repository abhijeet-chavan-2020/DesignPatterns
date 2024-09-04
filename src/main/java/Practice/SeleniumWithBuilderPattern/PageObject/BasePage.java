package Practice.SeleniumWithBuilderPattern.PageObject;

import Practice.SeleniumWithBuilderPattern.DriverGenerator.AppTestForBuilderPattern;
import Practice.SeleniumWithBuilderPattern.DriverGenerator.WebDriverManagerForBuilderPattern;
import org.openqa.selenium.WebDriver;

public class BasePage  {
    WebDriver driver;


    public BasePage() {
        AppTestForBuilderPattern apptest= new AppTestForBuilderPattern();
        apptest.setup();
        this.driver = apptest.getDriver();
       // this.driver = WebDriverManagerForBuilderPattern.getInstance().getDriver();
    }
}
