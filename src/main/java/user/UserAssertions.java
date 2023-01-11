package user;
import io.restassured.response.ValidatableResponse;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.*;

public class UserAssertions {
    public String createdSuccessfully(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(HTTP_OK)
                .body("success", is(true))
                .extract().path("accessToken");
    }
    public String loggedInSuccessfully(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(HTTP_OK)
                .body("accessToken", notNullValue())
                .extract().path("accessToken");
    }
    public void creationFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_FORBIDDEN)
                .body("success", is(false));
    }
    public void loginFailed(ValidatableResponse response) {
        response.assertThat().statusCode(HTTP_UNAUTHORIZED);
    }
    public void deletedSuccessfully(ValidatableResponse response) {
        response.assertThat().statusCode(HTTP_ACCEPTED);
    }
    public String changingEmailSuccessfully(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(HTTP_OK)
                .body("success", is(true))
                .extract().path("user.email");
    }
    public String changingNameSuccessfully(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(HTTP_OK)
                .body("success", is(true))
                .extract().path("user.name");
    }
    public void changingPasswordSuccessfully(ValidatableResponse response) {
                response.assertThat()
                .statusCode(HTTP_OK)
                .body("success", is(true));
    }
    public void changingFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .body("success", is(false));
    }
}
