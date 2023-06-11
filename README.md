# MetalShopTests
#### This is my Selenium automation tests project in Java with Page Object Model implementation, Page Factory and JUnit framework.

Automated Tests of the [Metal Shop](http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/ "Softie's Metal Shop Homepage") website.
Tests are in this repository on separate branches:
- [Login tests](https://github.com/wlodarczakm/MetalShopTests/tree/feature-login-tests "feature-login-tests")
- [Registration tests](https://github.com/wlodarczakm/MetalShopTests/tree/feature-registration-tests "feature-registration-tests")
- [Contact Form tests](https://github.com/wlodarczakm/MetalShopTests/tree/feature-contact-form-tests "feature-contact-form-tests")

![metal-shop-main-pg](https://user-images.githubusercontent.com/120977639/229567796-15e8d500-ab8f-4ec8-8974-f92fc62c31bb.png)

## Automated functional tests include:
- Signing up on the [Register page](#register-page-tests-code-snippet)
- Signing in on the [My account(login) page](#login-page-tests-code-snippet)
- Verifying contact form submission
- Testing visibility of user Dashboard elements

#### #Register page tests code snippet:
```
    @BeforeEach
    void prepareBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/register/");
        driver.manage().window().maximize();
        registerPage = new RegisterPage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    @AfterEach
    void closeBrowser() {
        driver.quit();
    }

    @Test
    void registration_success_when_new_user_data_given() {
        registerPage.FillOutAForm(NEW_USER_DATA);
        registerPage.FormSubmission();

        assertTrue(registerPage.UserRegisteredMessage().contains(registerPage.ShouldDisplayText_RegistrationSuccess));
    }
    
```
The snippet includes a new user registration test

Below you can see a Maven Surfire Report:
![maven-surfire-report-registration-tests](https://user-images.githubusercontent.com/120977639/229592446-3b04929b-9ecd-45a4-b62c-678f0dd27b7d.png)

and here is Maven output log:
```
-------------------------------------------------------------------------------
Test set: selenium.test.RegistrationTests
-------------------------------------------------------------------------------
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 28.073 s - in selenium.test.RegistrationTests
```

## #Login page tests code snippet:
```
    @Test
    void appear_password_input_empty_message_when_only_username_given() {
        loginPage.FillInputs(PASSWORD_NOT_GIVEN);
        loginPage.login();

        assertTrue(loginPage.ErrorMessageText().contains(loginPage.passwordInputIsEmptyMessage()));
    }
    @Test
    void appear_no_such_user_message_when_not_registered_user_try_to_login() {
        loginPage.FillInputs(NEW_USER_DATA);
        loginPage.login();

        assertTrue(loginPage.ErrorMessageText().contains(loginPage.NoSuchUserMessage()));
    }
```
### #My account page code snippet:
```
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void FillInputs(int methodIndex) {
        switch (methodIndex){
            case NEW_USER_DATA:
                UserData_NewUser();
                break;
            case REGISTERED_USER_CREDENTIALS:
                UserData_RegisteredUser();
                break;
            case USERNAME_NOT_GIVEN:
                UserData_UsernameNotGive();
                break;
            case PASSWORD_NOT_GIVEN:
                UserData_NewUser_password_not_given();
                break;
            default:
                throw new IllegalArgumentException("Wrong method index");
        }

    }
    @Deprecated
    public void UserData_RegisteredUser() {
        usernameInput.sendKeys(Users.registeredUsername);
        passwordInput.sendKeys(Users.registeredUserPassword);
    }
```
Maven Surfire Report for Login Tests:

![maven-surfire-report-login-tests](https://user-images.githubusercontent.com/120977639/229604164-6458cc1b-10a0-4c44-8556-1bca56154921.png)
and Maven output log:
```
-------------------------------------------------------------------------------
Test set: selenium.test.LoginTests
-------------------------------------------------------------------------------
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 29.392 s - in selenium.test.LoginTests

```
