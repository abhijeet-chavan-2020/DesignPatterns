package Practice.SeleniumWithFactoryPattern;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SimpleTest {
    private WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public  void setup(String browser){
        driver= DriverFactory.getDriver(browser).createDriver();
    }

    @Test
    public void testGoogle(){
        driver.get("https://www.google.com");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName()+ " and Title= "+ driver.getTitle());

    }

    @Test
    public void testGoogle1(){
        driver.get("https://www.google.com");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName()+ " and Title= "+ driver.getTitle());

    }
    @Test
    public void testGoogle2(){
        driver.get("https://www.google.com");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName()+ " and Title= "+ driver.getTitle());

    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
