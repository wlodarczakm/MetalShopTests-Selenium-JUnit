import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import webpages.MyAccountPage;
import utils.Users;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class LoginTests {
    WebDriver driver = new ChromeDriver();
    MyAccountPage loginPage;
    List<WebElement> elements;
    private MyAccountPage elementsAfterLogin;
    @BeforeEach
    void prepareBrowser() {
        driver.manage().window().maximize();
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
    void displays_dashboard_when_registered_user_login() {
        loginPage.FillInputs(Users.registeredUser);
        loginPage.login();

        elements = elementsAfterLogin.DashboardElements();
        for (WebElement element : elements) {
            assertTrue(element.isDisplayed());
        }
    }
    @Test
    void appear_error_message_when_only_password_given() {
        loginPage.FillInputs(Users.onlyPasswordGivenUser);
        loginPage.login();

        assertTrue(loginPage.errorMessageText().contains(loginPage.usernameInputIsRequiredMessage()));
    }
    @Test
    void appear_error_message_when_only_username_given() {
        loginPage.FillInputs(Users.onlyUsernameGivenUser);
        loginPage.login();

        assertTrue(loginPage.errorMessageText().contains(loginPage.passwordInputIsEmptyMessage()));
    }
    @Test
    void appear_error_message_when_not_registered_user_try_login() {
        loginPage.FillInputs(Users.randomUser);
        loginPage.login();

        assertTrue(loginPage.errorMessageText().contains(loginPage.wrongUserMessage()));
    }
}


