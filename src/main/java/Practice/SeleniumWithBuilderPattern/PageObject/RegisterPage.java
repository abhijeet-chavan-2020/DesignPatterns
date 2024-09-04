package Practice.SeleniumWithBuilderPattern.PageObject;

import Practice.SeleniumWithBuilderPattern.Builder.Register;
import Practice.SeleniumWithBuilderPattern.DriverGenerator.WebDriverManagerForBuilderPattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class RegisterPage extends  BasePage {
   // WebDriver driver;
   SoftAssert sa= new SoftAssert();
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
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//label[@for='input-firstname']"))));
    }

    public void verifyFieldLabels(List<String> labels){
        String labelValue= driver.findElement(By.xpath("//label[@for='"+labels.get(0)+"']")).getText().trim();
        System.out.println("labelValue = " + labelValue);
        sa.assertEquals(labelValue, labels.get(1), "Verify label value is correct");
    }
}
