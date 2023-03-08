package selenium.utils;
import com.github.javafaker.Faker;
public class Users {
    static Faker faker = new Faker();

    public static String randomEmailAddress = faker.internet().emailAddress();

    public static String randomName = faker.name().fullName();
    public static String randomTopic = faker.superhero().name();
    public static String randomMessage = faker.lorem().sentence();

    public String email;
    public String fullName;
    public String subject;
    public String message;
    public Users() {
        this.fullName = randomName;
        this.email = randomEmailAddress;
        this.subject = randomTopic;
        this.message = randomMessage;

    }
}