package order;

import client.Client;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client {
    protected final String ROOT = "/orders";
    public ValidatableResponse getOrderList(String accessToken) {
        OrderClient orderClient =  new OrderClient();
        return given().log().all()
                .auth().oauth2(accessToken.replace("Bearer ", ""))
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .basePath(API_PREFIX)
                .body(orderClient)
                .when()
                .get(ROOT)
                .then().log().all();
    }
    public ValidatableResponse getOrderListWithoutAuthorization() {
        OrderClient orderClient =  new OrderClient();
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .basePath(API_PREFIX)
                .body(orderClient)
                .when()
                .get(ROOT)
                .then().log().all();
    }
    public ValidatableResponse creatingOrder(Order order, String accessToken) {
        return (ValidatableResponse) given().log().all()
                .auth().oauth2(accessToken.replace("Bearer ", ""))
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .basePath(API_PREFIX)
                .body(order)
                .when()
                .post(ROOT)
                .then()
                .log().all();
    }
    public ValidatableResponse creatingOrderWithoutAuthorizedUser(Order order) {
        return (ValidatableResponse) given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .basePath(API_PREFIX)
                .body(order)
                .when()
                .post(ROOT)
                .then()
                .log().all();
    }
}
