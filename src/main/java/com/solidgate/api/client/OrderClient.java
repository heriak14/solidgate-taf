package com.solidgate.api.client;

import com.solidgate.core.EnvProperties;
import io.restassured.response.Response;
import java.util.Map;

public class OrderClient extends AuthenticatedClient {

    private static final String BASE_PATH = EnvProperties.getCardPaymentsBaseUrl();
    private static final String ORDER_STATUS_URI = "/status";
    private static final String ORDER_ID = "order_id";

    public OrderClient() {
        super(BASE_PATH);
    }

    public Response getOrderStatus(String orderId) {
        Map<String, String> requestBody = Map.of(ORDER_ID, orderId);
        return getAuthenticatedClient(requestBody)
                .post(ORDER_STATUS_URI);
    }
}
