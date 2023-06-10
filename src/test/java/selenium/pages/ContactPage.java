package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utils.Users;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ContactPage {
    WebDriver driver;
    Users user;

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

    @FindBy(css = "div[class='wpcf7-response-output']")
    WebElement sendContactMessageError;



    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> ContactFormElements() {
        List<WebElement> allDashboardElements = new ArrayList<>();
        allDashboardElements.add(fullNameInput);
        allDashboardElements.add(emailInput);
        allDashboardElements.add(subjectInput);
        allDashboardElements.add(messageInput);
        return allDashboardElements;

    }

    public void WaitClass() {
        WebDriverWait WAIT = new WebDriverWait(driver, Duration.ofSeconds(1));
        for (WebElement element : ContactFormElements()) {
            WAIT.until(ExpectedConditions.visibilityOfAllElements(element));
        }
    }
    public void FillInput() {
        user = new Users();

        fullNameInput.sendKeys(user.fullName);
        emailInput.sendKeys(user.email);
        subjectInput.sendKeys(user.subject);
        messageInput.sendKeys(user.message);
    }

    public void WaitForErroMessage() {
        WebDriverWait WAIT = new WebDriverWait(driver, Duration.ofSeconds(1));
        WAIT.until(ExpectedConditions.visibilityOf(sendContactMessageError));
    }
    public void submitContactForm(){
        submit.click();
    }

    public boolean AppearErrorWhenSendingMessage() {
        return sendContactMessageError.getText().contains
            ("Wystąpił problem z wysłaniem twojej wiadomości. Spróbuj ponownie później.");
    }
}
