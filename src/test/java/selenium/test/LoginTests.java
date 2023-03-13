package selenium.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.pages.MyAccountPage;
import selenium.utils.TestSettings;
import selenium.utils.Users;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class LoginTests {
    WebDriver driver;
    MyAccountPage loginPage;
//    TestSettings aDriverSetup;
    List<WebElement> elements;
    MyAccountPage elementsAfterLogin;
    @BeforeEach
    void prepareBrowser() {
//        aDriverSetup = new TestSettings();
//        aDriverSetup.setupDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/moje-konto/");
        loginPage = new MyAccountPage(driver);
        elementsAfterLogin = new MyAccountPage(driver);
    }
    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
    @Test
    void login_when_credentials_are_correct() {
        loginPage.FillInputs(Users.registeredUser);
        loginPage.login();
        assertTrue(loginPage.getLogoutButton().isDisplayed());
    }
    @Test
    void displays_dashboard_buttons_when_registered_user_login() {
        loginPage.FillInputs(Users.registeredUser);
        loginPage.login();

        elements = elementsAfterLogin.DashboardElements();
        for (WebElement element : elements) {
            assertTrue(element.isDisplayed());
        }
    }
    @Test
    void appear_username_required_message_when_only_password_given() {
        loginPage.FillInputs(Users.onlyPasswordGivenUser);
        loginPage.login();

        assertTrue(loginPage.ErrorMessageText().contains(loginPage.usernameInputIsRequiredMessage()));
    }
    @Test
    void appear_password_input_empty_message_when_only_username_given() {
        loginPage.FillInputs(Users.onlyUsernameGivenUser);
        loginPage.login();

        assertTrue(loginPage.ErrorMessageText().contains(loginPage.passwordInputIsEmptyMessage()));
    }
    @Test
    void appear_no_such_user_message_when_not_registered_user_try_login() {
        loginPage.FillInputs(Users.randomUser);
        loginPage.login();

        assertTrue(loginPage.ErrorMessageText().contains(loginPage.NoSuchUserMessage()));
    }
}