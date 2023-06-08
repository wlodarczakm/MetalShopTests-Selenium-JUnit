package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.utils.Users;
import java.util.ArrayList;
import java.util.List;
public class MyAccountPage {
    WebDriver driver;
    @FindBy(css = "input[id='username']")
    WebElement usernameInput;

    @FindBy(css = "input[id='password']")
    WebElement passwordInput;

    @FindBy(css = "button[class*='submit']")
    WebElement submitButton;

    @FindBy(css = "ul[class='woocommerce-error'] li")
    WebElement loginErrorMessage;
    @FindBy(css = "li[class*='is-active'] a")
    WebElement dashboardButton;
    @FindBy(css = "li[class*='woocommerce-MyAccount-navigation-link--orders'] a")
    WebElement ordersButton;
    @FindBy(css = "li[class*='woocommerce-MyAccount-navigation-link--edit-address'] a")
    WebElement addressesButton;
    @FindBy(css = "li[class*='woocommerce-MyAccount-navigation-link--edit-account'] a")
    WebElement editAccountButton;
    @FindBy(css = "li[class*='woocommerce-MyAccount-navigation-link--customer-logout'] a")
    WebElement logoutButton;

    @FindBy(className = "woocommerce-notices-wrapper")
    WebElement helloMessage;

    public static final int NEW_USER_DATA = 1;
    public static final int REGISTERED_USER_CREDENTIALS = 2;
    public static final int USER_EMAIL_NOT_GIVEN = 3;
    public static final int USERNAME_NOT_GIVEN = 4;
    public static final int PASSWORD_NOT_GIVEN = 5;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void FillInputs(int methodIndex) {
        switch (methodIndex){
            case NEW_USER_DATA:
                UserData_NewUser();
                break;
            case REGISTERED_USER_CREDENTIALS:
                UserData_RegisteredUser();
                break;
            case USERNAME_NOT_GIVEN:
                UserData_UsernameNotGive();
                break;
            case PASSWORD_NOT_GIVEN:
                UserData_NewUser_password_not_given();
                break;
            default:
                throw new IllegalArgumentException("Wrong method index");
        }

    }
    @Deprecated
    public void UserData_RegisteredUser() {
        usernameInput.sendKeys(Users.registeredUsername);
        passwordInput.sendKeys(Users.registeredUserPassword);
    }
    @Deprecated
    public void UserData_NewUser() {
        usernameInput.sendKeys(Users.randomUsername);
        passwordInput.sendKeys(Users.randomPassword);
    }
    @Deprecated
    public void UserData_NewUser_password_not_given() {
        usernameInput.sendKeys(Users.registeredUsername);
        passwordInput.sendKeys(passwordNotGiven);
    }
    @Deprecated
    public void UserData_UsernameNotGive() {
        usernameInput.sendKeys(usernameNotGiven);
        passwordInput.sendKeys(Users.randomPassword);
    }
    public void pressLoginButton() {
        submitButton.click();
    }

    public String ErrorMessageText() {
        return loginErrorMessage.getText();
    }
    public WebElement getLogoutButton() {return logoutButton;
    }
    public String passwordInputIsEmptyMessage() {
        return "pole hasła jest puste.";
    }
    public String usernameInputIsRequiredMessage() {
        return "Nazwa użytkownika jest wymagana.";
    }
    public String NoSuchUserMessage() {
        return "wśród zarejestrowanych w witrynie użytkowników." +
                " Jeśli nie masz pewności co do nazwy użytkownika, użyj adresu e-mail.";
    }
    public String passwordNotGiven = "";
    public String usernameNotGiven = "";

    public List<WebElement> DashboardElements() {
        List<WebElement> allDashboardElements = new ArrayList<>();
        allDashboardElements.add(dashboardButton);
        allDashboardElements.add(ordersButton);
        allDashboardElements.add(addressesButton);
        allDashboardElements.add(editAccountButton);
        allDashboardElements.add(logoutButton);
        return allDashboardElements;
    }
}