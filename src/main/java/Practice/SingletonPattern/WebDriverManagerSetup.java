package Practice.SingletonPattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WebDriverManagerSetup {
    private  static  volatile WebDriverManagerSetup instance;
    private  static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    static Properties properties;
    private static String browser;
    protected WebDriverManagerSetup(){}


    @BeforeTest
    private void init(){
        loadConfig();
        switch (browser){
            case "chrome":
                tlDriver.set(new ChromeDriver());
                System.out.println("WebDriver initialized with chrome driver ");
                break;
            case "firefox":
                tlDriver.set(new FirefoxDriver());
                break;
            case "edge":
                tlDriver.set(new EdgeDriver());
                break;
        }
    }

    public static WebDriverManagerSetup getInstance(){
        if(instance==null){
            synchronized (WebDriverManagerSetup.class){
                if(instance==null){
                    instance= new WebDriverManagerSetup();
                    System.out.println("New Instance is created");

                }
            }
        }

        if(tlDriver.get()==null){
            instance.init();
            System.out.println("Instance is initialized");
        }
        return  instance;
    }

    public WebDriver getDriver(){
        return  tlDriver.get();
    }

    public static void loadConfig(){
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(fileInputStream);
            setBrowser( properties.get("browser").toString());
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBrowser() {
        System.out.println("Browser is :"+ browser);
        return browser;
    }

    public static void setBrowser(String browser) {
        WebDriverManagerSetup.browser = browser;
    }

    public  static void quitBrowser(){
        if(tlDriver.get() !=null){
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
