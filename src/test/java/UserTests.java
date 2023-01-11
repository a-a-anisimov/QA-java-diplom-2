import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Test;
import user.Credentials;
import user.UserAssertions;
import user.UserClient;
import user.UserGenerator;

public class UserTests {
    private final UserGenerator generator = new UserGenerator();
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    private String accessToken;

    @Test
    @Description("Successful creation a new user")
    @DisplayName("Creation user")
    public void creationUser() {
        var user = generator.random();
        ValidatableResponse creationsResponse = client.create(user);
        accessToken = check.createdSuccessfully(creationsResponse);
    }
    @Test
    @Description("Successful login")
    @DisplayName("Login user")
    public void loginUser() {
        var user = generator.generick();
        Credentials creds = Credentials.from(user);
        ValidatableResponse loginResponse  = client.login(creds);
        check.loggedInSuccessfully(loginResponse);
    }
    @Test
    @Description("Attempt to create a user with already existing data")
    @DisplayName("Creation fails with data existing user")
    public void creationFailsWithDataExistingUser() {
        var user = generator.nonExiting();
        ValidatableResponse creationsResponse = client.create(user);
        check.creationFailed(creationsResponse);
    }
    @Test
    @Description("Authorization attempt without filling in the required password field")
    @DisplayName("Login fails without password")
    public void loginFailsWithoutPassword() {
        var user = generator.generick();
        user.setPassword(null);

        Credentials creds = Credentials.from(user);
        ValidatableResponse loginResponse = client.login(creds);
        check.loginFailed(loginResponse);
    }
    @Test
    @Description("Authorization attempt with invalid email and password")
    @DisplayName("Login fails with wrong email and password")
    public void loginFailsWithWrongEmailAndPassword() {
        var user = generator.generick();
        user.setEmail(null);
        user.setPassword(null);

        Credentials creds = Credentials.from(user);
        ValidatableResponse loginResponse  = client.login(creds);
        check.loginFailed(loginResponse);
    }
    @After
    @DisplayName("Remove test users")
    public void deleteUser() {
        if (accessToken != null) {
            ValidatableResponse response = client.delete(accessToken);
            check.deletedSuccessfully(response);
        }
    }
}
