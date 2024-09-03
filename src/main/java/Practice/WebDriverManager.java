package Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WebDriverManager {
    private  static  volatile  WebDriverManager instance;
    private  static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    static Properties properties;
    private static String browser;
    protected WebDriverManager(){}


    private void init(){
        loadConfig();
        switch (browser){
            case "chrome":
                tlDriver.set(new ChromeDriver());
                break;
            case "firefox":
                tlDriver.set(new FirefoxDriver());
                break;
            case "edge":
                tlDriver.set(new EdgeDriver());
                break;
        }
    }

    public static WebDriverManager getInstance(String browser){
        if(instance==null){
            synchronized (WebDriverManager.class){
                if(instance==null){
                    instance= new WebDriverManager();
                }
            }
        }

        if(tlDriver.get()==null){
            instance.init();
        }
        return  instance;
    }

    public  WebDriver getDriver(){
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
        return browser;
    }

    public static void setBrowser(String browser) {
        WebDriverManager.browser = browser;
    }


    public  static void quitBrowser(){
        if(tlDriver.get() !=null){
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
