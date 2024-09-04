package Practice.SeleniumWithBuilderPattern.stepDefiition;

import Practice.SeleniumWithBuilderPattern.PageObject.RegisterPage;
import Practice.SeleniumWithBuilderPattern.Builder.Register;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class RegisterPageStepDef  {
    RegisterPage registerPage;
    Register register;


    public RegisterPageStepDef() {
        this.registerPage= new RegisterPage() ;
    }

    @When("Registration data is prepared")
    public void prepareRegistrationData(){
         register=new Register.RegisterBuilder()
                .setFirstName("Abhijeet")
                .setLastName("Chavan")
                .setTelephone("8787878787")
                .setEmail("test@test.com")
                .setPassword("test")
                .setConfirmpassword("test")
                .build();
    }

    @When("Fill Registration page data")
    public void fillRegistration(){
        registerPage.registerUser(register);
    }

    @And("Registration page is launched")
    public void registrationPageLaunch(){
        registerPage.navigateToRegistrationPage();

    }

    @Then("Verify field labels")
    public  void verifyFieldLabels(List<List<String>> list){
        for(List<String> lists: list){
            registerPage.verifyFieldLabels(lists);
        }
    }
}
