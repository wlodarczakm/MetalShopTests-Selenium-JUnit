package selenium.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.pages.ContactPage;
import selenium.utils.Users;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactPageTests {
    WebDriver driver = new ChromeDriver();
    ContactPage contactPage;

    @BeforeEach
    void prepareBrowser() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        WebDriver driver = new ChromeDriver(options);
        driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/register/");
        contactPage = new ContactPage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    @AfterEach
    void closeBrowser() {
        driver.quit();
    }

    @Test
    void ContactMessage() {
        contactPage.FillInput(new Users(Users.randomName, Users.randomEmailAddress, Users.randomTopic, Users.randomMessage));
        contactPage.submit();
        assertTrue(driver.findElement(By.cssSelector("div[class='wpcf7-response-output']")).isDisplayed());
    }
}
