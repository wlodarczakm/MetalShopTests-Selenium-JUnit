package selenium.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pages.ContactPage;
import selenium.utils.Users;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactPageTests {
    WebDriver driver = new ChromeDriver();
    ContactPage contactPage;
    Wait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @BeforeEach
    void prepareBrowser() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        WebDriver driver = new ChromeDriver(options);
        driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/kontakt/");
        contactPage = new ContactPage(driver);
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    @AfterEach
    void closeBrowser() {
        driver.quit();
    }

    @Test
    void ContactMessage() {
        contactPage.FillInput(new Users());
        contactPage.submitContactForm();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@class, 'wpcf7-response-output')]")));
        assertTrue(driver.findElement(By.xpath(".//div[contains(@class, 'wpcf7-response-output')]")).getText().contains("Wystąpił problem z wysłaniem twojej wiadomości. Spróbuj ponownie później."));
    }
}
