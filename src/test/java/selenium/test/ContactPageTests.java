package selenium.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.pages.ContactPage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactPageTests {
    WebDriver driver;
    ContactPage contactPage;
    ContactPage contactFormElements;

    @BeforeEach
    void prepareBrowser() {
//        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
//        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/kontakt/");
        contactPage = new ContactPage(driver);
        contactFormElements = new ContactPage(driver);
    }
    @AfterEach
    void closeBrowser() {
        driver.quit();
    }

    @Test
    void ContactMessage() {
        contactPage.FillInput();
        contactPage.submitContactForm();
        contactPage.WaitForErroMessage();

        assertTrue(contactPage.AppearErrorWhenSendingMessage());
    }
}
