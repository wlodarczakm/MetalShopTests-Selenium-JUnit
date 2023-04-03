# MetalShopTests
#### This is my Selenium automation tests project in Java with Page Object Model implementation, Page Factory and JUnit framework.

Automated Tests of the [Metal Shop](http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/ "Softie's Metal Shop Homepage") website.
![metal-shop-main-pg](https://user-images.githubusercontent.com/120977639/229567796-15e8d500-ab8f-4ec8-8974-f92fc62c31bb.png)

## Automated functional tests include:
- Signing up on the [Register page](#register-page-tests-code-snippet)
- Signing in on the My account page
- Verifying contact form submission
- Testing visibility of user Dashboard elements

## #Register page tests code snippet:
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
