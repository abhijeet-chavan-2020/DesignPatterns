package Practice.SeleniumWithBuilderPattern.Builder;

public class Register {
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String password;
    private String confirmpassword;

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public String getPassword() {
        return password;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Register (RegisterBuilder registerBuilder)
    {
        this.firstName= registerBuilder.firstName;
        this.lastName= registerBuilder.lastName;
        this.email= registerBuilder.email;
        this.telephone= registerBuilder.telephone;
        this.password= registerBuilder.password;
        this.confirmpassword= registerBuilder.confirmpassword;
    }




    public static class  RegisterBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private String telephone;
        private String password;
        private String confirmpassword;

        public String getFirstName() {
            return firstName;
        }

        public RegisterBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public String getLastName() {
            return lastName;
        }

        public RegisterBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public RegisterBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public String getTelephone() {
            return telephone;
        }

        public RegisterBuilder setTelephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public RegisterBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public String getConfirmpassword() {
            return confirmpassword;
        }

        public RegisterBuilder setConfirmpassword(String confirmpassword) {
            this.confirmpassword = confirmpassword;
            return this;
        }

        public Register build(){
            return  new Register(this);
        }
    }
}
