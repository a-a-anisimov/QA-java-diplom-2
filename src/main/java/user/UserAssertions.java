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
    public void loggedInSuccessfully(ValidatableResponse response) {
                response.assertThat()
                .statusCode(HTTP_OK)
                .body("accessToken", notNullValue());
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
}
