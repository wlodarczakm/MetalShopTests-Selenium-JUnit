package selenium.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.pages.RegisterPage;
import java.util.concurrent.TimeUnit;
import selenium.utils.TestSettings;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static selenium.pages.RegisterPage.*;

public class RegistrationTests {

    private RegisterPage registerPage;
    private WebDriver driver;

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
    @Test
    void appear_user_exist_message_when_registered_user_credentials_given() {
        registerPage.FillOutAForm(REGISTERED_USER_CREDENTIALS);
        registerPage.FormSubmission();

        assertTrue(registerPage.UserExistMessage().contains(registerPage.ShouldDisplay_UserExistMessageText));
    }
    @Test
    void appear_email_required_message_when_email_not_given() {
        registerPage.FillOutAForm(USER_EMAIL_NOT_GIVEN);
        registerPage.FormSubmission();

        assertTrue(registerPage.EmailRequiredMessage().contains(registerPage.emailRequiredMessageText));
    }
    @Test
    void appear_username_required_message_when_username_not_given() {
        registerPage.FillOutAForm(USERNAME_NOT_GIVEN);
        registerPage.FormSubmission();

        assertTrue(registerPage.RequiredInput_Username_Message().contains(registerPage.ShouldDisplayText_UsernameRequired));
    }
}