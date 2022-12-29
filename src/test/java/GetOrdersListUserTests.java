import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersListUserTests {

    @BeforeClass
    public static void log() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        RestAssured.basePath = "/api/orders";
    }

    @Test
    @Description("Max 50 last orders")
    @DisplayName("Get orders list specific authorized user")
    public void getOrderListAuthorizedUser() {
        String json = "{\"courierId\": \"\", \"nearestStation\": \"\", \"limit\": \"\", \"page\": \"\"}";
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .get()
                .then().statusCode(200)
                .and()
                .assertThat().body("orders", notNullValue());
    }
}
