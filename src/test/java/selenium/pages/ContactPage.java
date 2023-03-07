package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.utils.Users;

import static selenium.utils.Users.*;

public class ContactPage {
    WebDriver driver;
    @FindBy(css = "input[class='wpcf7-form-control wpcf7-text wpcf7-validates-as-required']")
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
        fullNameInput.sendKeys(randomName);
        emailInput.sendKeys(randomEmailAddress);
        subjectInput.sendKeys(randomTopic);
        messageInput.sendKeys(randomMessage);
    }
    public void submit(){
        submit.click();
    }

}
