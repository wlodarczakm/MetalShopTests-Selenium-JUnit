package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.seleniumhq.jetty9.server.Authentication;
import selenium.utils.Users;

import java.util.concurrent.TimeUnit;

import static selenium.utils.Users.*;

public class ContactPage {
    WebDriver driver;
    @FindBy(css = "input[name='your-name']")
    WebElement fullNameInput;
    @FindBy(css = "input[type='email']")
    WebElement emailInput;
    @FindBy(css = "input[name*='subject']")
    WebElement subjectInput;
    @FindBy(css = "textarea[class='wpcf7-form-control wpcf7-textarea']")
    WebElement messageInput;
    @FindBy(css = "input[type='submit']")
    WebElement submit;
    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void FillInput(Users user) {
        fullNameInput.sendKeys(user.fullName);
        emailInput.sendKeys(user.email);
        subjectInput.sendKeys(user.subject);
        messageInput.sendKeys(user.message);
    }
    public void submitContactForm(){
        submit.click();
    }

}
