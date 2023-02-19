package webpages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Users;
public class RegisterPage {
    WebDriver driver;

    @FindBy(css = "input[id='user_login']")
    WebElement usernameInput;

    @FindBy(css = "input[id='user_email']")
    WebElement emailInput;

    @FindBy(css = "input[id='user_pass']")
    WebElement passwordInput;

    @FindBy(css = "input[id*='confirm']")
    WebElement confirmPasswordInput;

    @FindBy(css = "button[class*='ur-submit-button']")
    WebElement submitButton;

    @FindBy(css = "div[id='ur-submit-message-node'] ul")
    WebElement submitMessage;

    @FindBy(css= "div[id='ur-submit-message-node'] ul li")
    WebElement userExistText;

    @FindBy(css= "label[id$='email-error']")
    WebElement emailRequiredWarning;

    public RegisterPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void FillInput(Users user) {
        usernameInput.sendKeys(user.username);
        passwordInput.sendKeys(user.password);
        confirmPasswordInput.sendKeys(user.password);
        emailInput.sendKeys(user.email);
    }
    public void SubmitRegistrationForm(){submitButton.click();
    }
    public String SubmitMessageText() {
        return submitMessage.getText();
    }
    public String successRegistrationMessage = "User successfully registered.";
    public String userExistMessage = "Username already exists.";

    public String UserExistMessage() {
        return userExistText.getText();
    }
    public String EmailRequiredMessage() {
        return emailRequiredWarning.getText();
    }
    public String emailRequiredMessageText = "This field is required.";
}