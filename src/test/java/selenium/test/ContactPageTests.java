import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import webpages.ContactPage;

public class ContactPageTests {
    static WebDriver driver = new ChromeDriver();
    ContactPage contactPage;
    @BeforeEach
    void prepareBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/register/");
        contactPage = new ContactPage(driver);
    }
    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}
