package selenium.utils;
import com.github.javafaker.Faker;
public class Users {
    static Faker faker = new Faker();

    public static String randomUsername = faker.name().username();
    public static String randomPassword = faker.internet().password();
    public static String randomEmail = faker.internet().emailAddress();

    public static String registeredUsername = "my_username";
    public static String registeredUserPassword = "MyPassword123";
    public static String registeredUserEmail = "myemail123@wp.pl";

    public Users(String username, String password, String email) {
        this.randomUsername = username;
        this.randomPassword = password;
        this.randomEmail = email;
    }
}
