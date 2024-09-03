package Practice.SeleniumWithFactoryPattern;

public class DriverFactory {

    public static BrowserDriver getDriver(String browserType){
        switch (browserType.toLowerCase()){
            case "chrome":
                return new ChromeDriverManager();
            case "edge":
                return new EdgeDriverManager();
            default:
                throw new IllegalArgumentException("Invalid Browser Type: "+browserType);
        }
    }
}
