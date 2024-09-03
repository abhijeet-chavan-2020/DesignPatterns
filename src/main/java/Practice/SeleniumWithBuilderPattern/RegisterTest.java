package Practice.SeleniumWithBuilderPattern;

import org.testng.annotations.Test;

public class RegisterTest {

    @Test
    public void userRegisterTest(){
        Register register=new Register.RegisterBuilder()
                        .setFirstName("Abhijeet")
                        .setLastName("Chavan")
                        .setTelephone("8787878787")
                        .setEmail("test@test.com")
                        .setPassword("test")
                        .setConfirmpassword("test")
                        .build();
        RegisterPage registerPage = new RegisterPage();
        registerPage.registerUser(register);
    }
}
