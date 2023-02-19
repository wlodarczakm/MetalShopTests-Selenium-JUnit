package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
    WebDriver driver;
    @FindBy(css ="input[name='your-name']")
    WebElement fullNameInput;
    @FindBy(css = "input[type='email']")
    WebElement emailInput;
    @FindBy(css = "input[name*='subject']")
    WebElement subjectInput;
    @FindBy(css = "textarea[class='wpcf7-form-control wpcf7-textarea']")
    WebElement messageInput;
    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
