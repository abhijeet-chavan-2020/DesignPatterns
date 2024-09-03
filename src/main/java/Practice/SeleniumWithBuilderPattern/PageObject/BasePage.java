package Practice.SeleniumWithBuilderPattern.PageObject;

import Practice.SeleniumWithBuilderPattern.DriverGenerator.WebDriverManagerForBuilderPattern;
import org.openqa.selenium.WebDriver;

public class BasePage  {
    WebDriver driver;


    public BasePage() {
        this.driver = WebDriverManagerForBuilderPattern.getInstance().getDriver();
    }
}
