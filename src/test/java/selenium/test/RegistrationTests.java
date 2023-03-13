package selenium.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.utils.Users;
import selenium.pages.RegisterPage;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class RegistrationTests {
    WebDriver driver;
    RegisterPage registerPage;
    @BeforeEach
    void prepareBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/register/");
        registerPage = new RegisterPage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
    @Test
    void registration_success_when_new_user_credentials_given() {
//        registerPage.FillInAForm(Users.randomRegistrationUser);
        registerPage.RegisterANewUser();
        registerPage.FormSubmission();

        assertTrue(registerPage.SubmitMessageText().contains(registerPage.successRegistrationMessage));
    }
    @Test
    void appear_user_exist_message_when_registered_user_credentials_given() {
        registerPage.FillInAForm(Users.registeredUser);
        registerPage.FormSubmission();

        assertTrue(registerPage.UserExistMessage().contains(registerPage.userExistMessage));
    }
    @Test
    void appear_email_required_message_when_email_not_given() {
        registerPage.FillInAForm(Users.userWithNoEmail);
        registerPage.FormSubmission();

        assertTrue(registerPage.EmailRequiredMessage().contains(registerPage.emailRequiredMessageText));
    }
}