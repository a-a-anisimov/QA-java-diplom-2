package order;

import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class OrderAssertions {

    public void unsuccessfulGetOrdersListWithoutAuthorization(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .body("success", is(false));
    }
    public void successfulGetOrdersListAuthorizedUser(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_OK)
                .body("orders", notNullValue());
    }
    public void successfulOrderCreation(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_OK)
                .body("success",  is(true));
    }
    public void unsuccessfulOrderCreationAuthorizedUserAndNonExistIngredients(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_INTERNAL_ERROR);
    }
    public void unsuccessfulOrderCreationAuthorizedUserWithoutIngredients(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("success", is(false));
    }
}
