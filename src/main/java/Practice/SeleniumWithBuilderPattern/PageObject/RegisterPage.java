package Practice.SeleniumWithBuilderPattern.PageObject;

import Practice.SeleniumWithBuilderPattern.Builder.Register;
import Practice.SeleniumWithBuilderPattern.DriverGenerator.WebDriverManagerForBuilderPattern;
import org.openqa.selenium.By;

public class RegisterPage extends  BasePage {
   // WebDriver driver;

    private final By firstName= By.id("input-firstname");
    private final By lastName= By.id("input-lastname");
    private final By email= By.id("input-email");
    private final By telephone= By.id("input-telephone");
    private final By password= By.id("input-password");
    private final By confirmpassword= By.id("input-confirm");

    public RegisterPage() {
    }


    public void registerUser(Register register){
        driver.findElement(firstName).sendKeys(register.getFirstName());
        driver.findElement(lastName).sendKeys(register.getLastName());
        driver.findElement(email).sendKeys(register.getEmail());
        driver.findElement(telephone).sendKeys(register.getTelephone());
        driver.findElement(password).sendKeys(register.getPassword());
        driver.findElement(confirmpassword).sendKeys(register.getConfirmpassword());

    }

    public void navigateToRegistrationPage(){
        driver.navigate().to(WebDriverManagerForBuilderPattern.getProperties().get("url").toString());
    }

}
