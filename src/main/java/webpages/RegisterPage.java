import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Users;
import webpages.RegisterPage;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class RegistrationTests {
    static WebDriver driver = new ChromeDriver();
    static Wait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    RegisterPage registerPage;

    @BeforeEach
    void prepareBrowser() {
        driver.manage().window().maximize();
        driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/register/");
        registerPage = new RegisterPage(driver);
    }
    @AfterEach
    void closeBrowser() {
        driver.quit();
    }

    @Test
    void appear_error_message_when_registered_user_credentials_given() {
        registerPage.FillInput(Users.randomRegistrationUser);
        registerPage.submitRegistrationForm();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='ur-submit-message-node'] ul")));

        assertTrue(registerPage.submitMessageText().contains(registerPage.successRegistrationMessage));
    }
}

