package user;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends Client.Client {
    protected final String ROOT = "/auth";
    public ValidatableResponse create(User user) {
        return spec()
                .body(user)
                .when()
                .post(ROOT + "/register")
                .then().log().all();
    }
    public ValidatableResponse login(Credentials creds) {
        return spec()
                .body(creds)
                .when()
                .post(ROOT + "/login")
                .then().log().all();
    }
    public ValidatableResponse change(User user, String accessToken) {
        return spec()
                .headers("authorization", accessToken)
                .body(user)
                .when()
                .patch(ROOT + "/user")
                .then().log().all();
    }
    public ValidatableResponse delete(String accessToken) {
        UserClient userClient =  new UserClient();
        return given().log().all()
                .auth().oauth2(accessToken.replace("Bearer ", ""))
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .basePath(API_PREFIX)
                .body(userClient)
                .when()
                .delete(ROOT + "/user")
                .then().log().all();
    }
}
