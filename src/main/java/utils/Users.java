package utils;
import com.github.javafaker.Faker;
public class Users {
    static Faker faker = new Faker();
    public static String randomUsername = faker.name().username();
    public static String randomPassword = faker.internet().password();
    public static String randomEmailAddress = faker.internet().emailAddress();

    public String username;
    public String password;
    public String email;

    public Users(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public static Users registeredUser = new Users("my_username", "MyPassword123", "" );
    public static Users randomUser = new Users(randomUsername, randomPassword, "");
    public static Users onlyPasswordGivenUser = new Users("", randomPassword, "");
    public static Users onlyUsernameGivenUser = new Users(randomUsername, "", "");

    public static Users forRegistrationUser = new Users("my_login", "MY_PASSWORD1", "my.fake.email@mail.com");
    public static Users randomRegistrationUser = new Users(randomUsername, randomPassword, randomEmailAddress);
}

