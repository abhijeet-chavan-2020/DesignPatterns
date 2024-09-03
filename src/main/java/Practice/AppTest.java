package Practice;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppTest {
    public static WebDriver driver;
    static Properties properties;

    public AppTest(){
    }

    @Parameters("browser")
    @BeforeTest
    public  void setup(String browser){
        loadConfig();
        driver= WebDriverManager.getInstance(browser).getDriver();
    }

    public static void loadConfig(){
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("ssrc/main/resources/config.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
