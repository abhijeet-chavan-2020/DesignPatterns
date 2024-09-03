package Practice.SeleniumWithBuilderPattern;

import Practice.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppTestForBuilderPattern extends  WebDriverManager {
    public static WebDriver driver;
    static Properties properties;
    private static String browser= getBrowser();
    public AppTestForBuilderPattern(){
        super();
    }

    @BeforeTest
    public static void setup(){
        loadConfig();
        driver= WebDriverManager.getInstance(browser).getDriver();
        if(driver==null){
            System.out.println("Driver is null");
        }
    }

    public WebDriver getDriver(){
       return WebDriverManager.getInstance(browser).getDriver();
    }



    @BeforeMethod
    public void launchApp(){
        driver.get(properties.get("url").toString());
    }

    @AfterClass
    public void tearDown(){
        WebDriverManager.quitBrowser();
    }
}
