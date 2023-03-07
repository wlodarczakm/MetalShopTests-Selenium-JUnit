package selenium.utils;
import com.github.javafaker.Faker;
public class Users {
    static Faker faker = new Faker();
    public static String randomUsername = faker.name().username();
    public static String randomPassword = faker.internet().password();
    public static String randomEmailAddress = faker.internet().emailAddress();

    public static String randomName = faker.name().fullName();
    public static String randomTopic = faker.superhero().name();
    public static String randomMessage = faker.lorem().sentence();

    public String email;
    public String name;
    public String fullName;
    public String subject;
    public String message;

    public Users(String fullName, String email, String subject, String message) {

        this.fullName = randomName;
        this.subject = randomTopic;
        this.message = randomMessage;
        this.email = randomEmailAddress;
    }

}