package selenium.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.pages.RegisterPage;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static selenium.pages.RegisterPage.*;

public class RegistrationTests {

    private RegisterPage registerPage;
    private WebDriver driver;

    @BeforeEach
    void prepareBrowser() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver();
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
    void registration_success_when_new_user_credentials_given() {
        registerPage.FillInAForm(NEW_USER_REGISTER);
        registerPage.FormSubmission();

        assertTrue(registerPage.SubmitMessageText().contains(registerPage.successRegistrationMessage));
    }
    @Test
    void appear_user_exist_message_when_registered_user_credentials_given() {
        registerPage.FillInAForm(REGISTERED_USER_CREDENTIALS);
        registerPage.FormSubmission();

        assertTrue(registerPage.UserExistMessage().contains(registerPage.userExistMessage));
    }
    @Test
    void appear_email_required_message_when_email_not_given() {
        registerPage.FillInAForm(USER_EMAIL_NOT_GIVEN);
        registerPage.FormSubmission();

        assertTrue(registerPage.EmailRequiredMessage().contains(registerPage.emailRequiredMessageText));
    }
}