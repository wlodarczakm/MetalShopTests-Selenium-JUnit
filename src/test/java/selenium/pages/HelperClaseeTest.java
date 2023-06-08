package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperClaseeTest {

        private WebDriver driver;

        public HelperClaseeTest(WebDriver driver) {
            this.driver = driver;
        }

        public WebElement LOGOUTBUTTONELEMENT() {
            return driver.findElement(By.cssSelector("a[href*='moje-konto/customer-logout']"));
        }
    }

