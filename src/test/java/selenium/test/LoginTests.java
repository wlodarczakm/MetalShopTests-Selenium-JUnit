package selenium.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.pages.HelperClaseeTest;
import selenium.pages.MyAccountPage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static selenium.pages.MyAccountPage.*;

public class LoginTests {
    WebDriver driver;
    MyAccountPage loginPage;

    HelperClaseeTest LOGOUTBUT;
//    TestSettings aDriverSetup;
    List<WebElement> elements;
    MyAccountPage elementsAfterLogin;
    WebDriver setupDRIVER;
    @BeforeEach
    void prepareDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/moje-konto/");
        loginPage = new MyAccountPage(driver);
        elementsAfterLogin = new MyAccountPage(driver);

        LOGOUTBUT = new HelperClaseeTest(driver);
    }
    @AfterEach
    void closeDriver() {
        driver.quit();
    }
    @Test
    void login_when_credentials_are_correct() {
        loginPage.FillInputs(REGISTERED_USER_CREDENTIALS);
        loginPage.pressLoginButton();
        assertTrue(loginPage.getLogoutButton().isDisplayed());
    }
    @Test
    void displays_dashboard_buttons_when_registered_user_login() {
        loginPage.FillInputs(REGISTERED_USER_CREDENTIALS);
        loginPage.pressLoginButton();

        elements = elementsAfterLogin.DashboardElements();
        for (WebElement element : elements) {
            assertTrue(element.isDisplayed());
        }
    }
    @Test
    void appear_username_required_message_when_only_password_given() {
        loginPage.FillInputs(USERNAME_NOT_GIVEN);
        loginPage.pressLoginButton();

        assertTrue(loginPage.ErrorMessageText().contains(loginPage.usernameInputIsRequiredMessage()));
    }
    @Test
    void appear_password_input_empty_message_when_only_username_given() {
        loginPage.FillInputs(PASSWORD_NOT_GIVEN);
        loginPage.pressLoginButton();

        assertTrue(loginPage.ErrorMessageText().contains(loginPage.passwordInputIsEmptyMessage()));
    }
    @Test
    void appear_no_such_user_message_when_not_registered_user_try_to_login() {
        loginPage.FillInputs(NEW_USER_DATA);
        loginPage.pressLoginButton();

        assertTrue(loginPage.ErrorMessageText().contains(loginPage.NoSuchUserMessage()));
    }
}