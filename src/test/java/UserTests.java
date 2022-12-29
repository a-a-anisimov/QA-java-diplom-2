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
    @Description("успешное создание и логин нового пользователя")
    public void createdAndLoginUser() {
        var user = generator.random();
        ValidatableResponse creationsResponse = client.create(user);
        accessToken = check.createdSuccessfully(creationsResponse);

        Credentials creds = Credentials.from(user);
        ValidatableResponse loginResponse  = client.login(creds);
        check.loggedInSuccessfully(loginResponse);
    }
    @Test
    @Description("попытка создания пользователя с уже существующими данными")
    public void creationFailsWithDataExistingUser() {
        var user = generator.generick();
        ValidatableResponse creationResponse = client.create(user);
        check.creationFailed(creationResponse);
    }
    @Test
    @Description("попытка авторизации без заполнения обязательного поля password")
    public void loginFailsWithoutPassword() {
        var user = generator.random();
        user.setPassword(null);

        Credentials creds = Credentials.from(user);
        ValidatableResponse loginResponse = client.login(creds);
        check.loginFailed(loginResponse);
    }
    @Test
    @Description("попытка авторизации с неверным email и password")
    public void loginFailsWithWrongEmailAndPassword() {
        var user = generator.nonExiting();
        user.setPassword(null);

        Credentials creds = Credentials.from(user);
        ValidatableResponse loginResponse  = client.login(creds);
        check.loginFailed(loginResponse);
    }
    @After //удаление тестового пользователя
    public void deleteUser() {
        if (accessToken != null) {
            ValidatableResponse response = client.delete(accessToken);
            check.deletedSuccessfully(response);
        }
    }
}
