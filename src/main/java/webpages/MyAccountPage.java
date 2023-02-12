package webpages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Users;
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

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void FillInputs(Users user) {
        usernameInput.sendKeys(user.username);
        passwordInput.sendKeys(user.password);
    }
    public void login() {
        submitButton.click();
    }

    public String errorMessageText() {
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
    public String wrongUserMessage() {
        return "wśród zarejestrowanych w witrynie użytkowników." +
                " Jeśli nie masz pewności co do nazwy użytkownika, użyj adresu e-mail.";
    }
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