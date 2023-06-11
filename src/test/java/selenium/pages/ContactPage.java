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

    @FindBy(css = "span[data-name='your-email'] span[class='wpcf7-not-valid-tip']")
    WebElement requiredField_email;

    @FindBy(css = "span[data-name='your-name'] span[class='wpcf7-not-valid-tip']")
    WebElement requiredField_fullName;


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
    public void FillInput(String... fieldValues) {
        user = new Users();

        String fullNameValue = fieldValues.length > 0 ? fieldValues[0] : user.fullName;
        String emailValue = fieldValues.length > 1 ? fieldValues[1] : user.email;
        String subjectValue = fieldValues.length > 2 ? fieldValues[2] : user.subject;
        String messageValue = fieldValues.length > 3 ? fieldValues[3] : user.message;

        fullNameInput.sendKeys(fullNameValue);
        emailInput.sendKeys(emailValue);
        subjectInput.sendKeys(subjectValue);
        messageInput.sendKeys(messageValue);
    }

    public void WaitForErrorMessage() {
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

    public void WaitForRequiredFieldErrorMessage() {
        WebDriverWait WAIT = new WebDriverWait(driver, Duration.ofSeconds(1));
        WAIT.until(ExpectedConditions.visibilityOf(requiredField_fullName));
    }

    public WebElement[] RequiredFields() {
        WebElement[] getRequiredFields = new WebElement[2];
        getRequiredFields[0] = requiredField_fullName;
        getRequiredFields[1] = requiredField_email;
        return getRequiredFields;
    }
}
