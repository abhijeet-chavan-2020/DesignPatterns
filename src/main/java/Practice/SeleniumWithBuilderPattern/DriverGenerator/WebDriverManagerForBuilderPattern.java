package Practice.SeleniumWithBuilderPattern.DriverGenerator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WebDriverManagerForBuilderPattern {
    private  static  volatile WebDriverManagerForBuilderPattern instance;
    private  static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static Properties getProperties() {
        return properties;
    }

    static Properties properties;

    private static String browser;
    protected WebDriverManagerForBuilderPattern(){}


    private void init(){
        loadConfig();
        switch (getBrowser().toLowerCase()){
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

    public static WebDriverManagerForBuilderPattern getInstance(){
        if(instance==null){
            synchronized (WebDriverManagerForBuilderPattern.class){
                if(instance==null){
                    instance= new WebDriverManagerForBuilderPattern();
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
        WebDriverManagerForBuilderPattern.browser = browser;
    }


    public  static void quitBrowser(){
        if(tlDriver.get() !=null){
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
