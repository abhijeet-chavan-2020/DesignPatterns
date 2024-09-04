package Practice.SeleniumWithBuilderPattern.DriverGenerator;

import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class AppTestForBuilderPattern {


    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver driver;

    private  String url;

    @BeforeClass
    public void setup(){
        System.out.println("BeforeClass: Setting up driver");

        this.driver= WebDriverManagerForBuilderPattern.getInstance().getDriver();
        if(driver==null){
            System.out.println("Driver is null");
        }
        driver.manage().window().maximize();
        System.out.println("Window maximized");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        launchApp();
    }

    public void launchApp(){
        System.out.println("App is now launching with url: "+ WebDriverManagerForBuilderPattern.properties.get("url").toString());
        url= WebDriverManagerForBuilderPattern.properties.get("url").toString();
        driver.get(url);
    }

    @AfterClass
    public void tearDown(){

        WebDriverManagerForBuilderPattern.quitBrowser();
    }
}
