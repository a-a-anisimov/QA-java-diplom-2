package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import user.Credentials;
import user.UserAssertions;
import user.UserGenerator;
import user.UserClient;

public class GetOrdersListUserTests {
    private final UserGenerator generator = new UserGenerator();
    private String accessToken;
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    private final OrderClient orderClient = new OrderClient();
    private final OrderAssertions checks = new OrderAssertions();

    @Test
    @Description("Successful get last 50 orders list")
    @DisplayName("Successful get orders list authorized user")
    public void successfulGetOrderAuthorizedUser() {
        var user = generator.generick();
        Credentials creds = Credentials.from(user);

        ValidatableResponse loginResponse = client.login(creds);
        accessToken = check.loggedInSuccessfully(loginResponse);

        ValidatableResponse getOrderListResponse = orderClient.getOrderList(accessToken);
        checks.successfulGetOrdersListAuthorizedUser(getOrderListResponse);
    }
    @Test
    @Description("Unsuccessful get orders list")
    @DisplayName("Unsuccessful get orders list without authorization")
    public void unsuccessfulGetOrdersListWithoutAuthorization() {
        ValidatableResponse getOrderListResponse = orderClient.getOrderListWithoutAuthorization();
        checks.unsuccessfulGetOrdersListWithoutAuthorization(getOrderListResponse);
    }
}
