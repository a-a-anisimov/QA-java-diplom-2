package user;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient {
    protected final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    protected final String ROOT = "/api/auth/";

    public ValidatableResponse create(User user) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(ROOT + "register")
                .then().log().all();
    }
    public ValidatableResponse login(Credentials creds) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(creds)
                .when()
                .post(ROOT + "login")
                .then().log().all();
    }
    public ValidatableResponse delete(String accessToken) {
        UserClient userClient =  new UserClient();
        return given().log().all()
                .auth().oauth2(accessToken.replace("Bearer ", ""))
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(userClient)
                .when()
                .delete(ROOT + "user")
                .then().log().all();
    }
}
