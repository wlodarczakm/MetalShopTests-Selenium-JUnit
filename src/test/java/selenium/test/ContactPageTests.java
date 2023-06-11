package selenium.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    void error_message_occurs_when_contact_message_sent() {
        contactPage.FillInput();
        contactPage.submitContactForm();
        contactPage.WaitForErrorMessage();

        assertTrue(contactPage.AppearErrorWhenSendingMessage());
    }
    @Test
    void error_message_occurs_for_empty_required_fields() {
        contactPage.FillInput("", "");
        contactPage.submitContactForm();
        contactPage.WaitForRequiredFieldErrorMessage();

        WebElement[] elements = contactFormElements.RequiredFields();
        for (WebElement element : elements ) {
            Assertions.assertEquals("Proszę wypełnić to pole.", element.getText());
        }
    }
}
