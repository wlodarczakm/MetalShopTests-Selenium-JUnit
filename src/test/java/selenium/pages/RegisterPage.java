package selenium.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.utils.Users;
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

    @FindBy(css= "label[id='user_email-error']")
    WebElement emailRequiredWarning;

    public RegisterPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static final int NEW_USER_REGISTER = 1;
    public static final int REGISTERED_USER_CREDENTIALS = 2;
    public static final int USER_EMAIL_NOT_GIVEN = 3;



    public void FillInAForm(int methodIndex) {
        switch (methodIndex) {
            case NEW_USER_REGISTER:
                NewUserRegister();
                break;
            case REGISTERED_USER_CREDENTIALS:
                RegisteredUserCredentials();
                break;
            case USER_EMAIL_NOT_GIVEN:
                UserCredentialsWithoutEmail();
                break;
            default:
                throw new IllegalArgumentException("Wrong method index");
        }
    }
    @Deprecated
    public void NewUserRegister() {
        usernameInput.sendKeys(Users.username);
        passwordInput.sendKeys(Users.userPassword);
        confirmPasswordInput.sendKeys(Users.userPassword);
        emailInput.sendKeys(Users.email);
    }
    @Deprecated
    public void RegisteredUserCredentials() {
        usernameInput.sendKeys(Users.registeredUsername);
        passwordInput.sendKeys(Users.registeredUserPassword);
        confirmPasswordInput.sendKeys(Users.registeredUserPassword);
        emailInput.sendKeys(Users.registeredUserEmail);
    }
    @Deprecated
    public void UserCredentialsWithoutEmail() {
        usernameInput.sendKeys(Users.registeredUsername);
        passwordInput.sendKeys(Users.registeredUserPassword);
        confirmPasswordInput.sendKeys(Users.registeredUserPassword);
        emailInput.sendKeys("");
    }
    public void FormSubmission() {
        submitButton.click();
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