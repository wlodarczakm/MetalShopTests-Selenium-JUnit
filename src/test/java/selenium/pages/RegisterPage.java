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

    @FindBy(xpath = "//*[@id='user_login-error']")
    WebElement requiredUsernameInput;

    public RegisterPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static final int NEW_USER_DATA = 1;
    public static final int REGISTERED_USER_CREDENTIALS = 2;
    public static final int USER_EMAIL_NOT_GIVEN = 3;
    public static final int USERNAME_NOT_GIVEN = 4;



    public void FillOutAForm(int methodIndex) {
        switch (methodIndex) {
            case NEW_USER_DATA:
                UserData_NewUser();
                break;
            case REGISTERED_USER_CREDENTIALS:
                UserData_RegisteredUser();
                break;
            case USER_EMAIL_NOT_GIVEN:
                UserData_EmailNotGiven();
                break;
            case USERNAME_NOT_GIVEN:
                UserData_UsernameNotGive();
                break;
            default:
                throw new IllegalArgumentException("Wrong method index");
        }
    }
    @Deprecated
    public void UserData_NewUser() {
        usernameInput.sendKeys(Users.randomUsername);
        passwordInput.sendKeys(Users.randomPassword);
        confirmPasswordInput.sendKeys(Users.randomPassword);
        emailInput.sendKeys(Users.randomEmail);
    }
    @Deprecated
    public void UserData_RegisteredUser() {
        usernameInput.sendKeys(Users.registeredUsername);
        passwordInput.sendKeys(Users.registeredUserPassword);
        confirmPasswordInput.sendKeys(Users.registeredUserPassword);
        emailInput.sendKeys(Users.registeredUserEmail);
    }
    @Deprecated
    public void UserData_EmailNotGiven() {
        usernameInput.sendKeys(Users.registeredUsername);
        passwordInput.sendKeys(Users.registeredUserPassword);
        confirmPasswordInput.sendKeys(Users.registeredUserPassword);
        emailInput.sendKeys("");
    }
    @Deprecated
    public void UserData_UsernameNotGive() {
        usernameInput.sendKeys(usernameNotGiven);
        passwordInput.sendKeys(Users.randomPassword);
        confirmPasswordInput.sendKeys(Users.randomPassword);
        emailInput.sendKeys(Users.randomEmail);
    }

    public void FormSubmission() {
        submitButton.click();
    }

    public String UserRegisteredMessage() {
        return submitMessage.getText();
    }
    public String ShouldDisplayText_RegistrationSuccess = "User successfully registered.";


    public String RequiredInput_Username_Message() {
        return requiredUsernameInput.getText();
    }

    public String ShouldDisplay_UserExistMessageText = "Username already exists.";
    public String ShouldDisplayText_UsernameRequired = "This field is required.";

    public String UserExistMessage() {
        return userExistText.getText();
    }
    public String EmailRequiredMessage() {
        return emailRequiredWarning.getText();
    }
    public String emailRequiredMessageText = "This field is required.";
    public String usernameNotGiven = "";
}