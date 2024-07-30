package com.solidgate.api.client;

import com.solidgate.core.EnvProperties;
import com.solidgate.core.model.PaymentPageDto;
import io.restassured.response.Response;

public class PaymentPageClient extends AuthenticatedClient {

    private static final String BASE_URL = EnvProperties.getPaymentPageBaseUrl();
    private static final String CREATE_PAYMENT_PAGE_URI = "/init";

    public PaymentPageClient() {
        super(BASE_URL);
    }

    public Response createPaymentPage(PaymentPageDto paymentPage) {
        return getAuthenticatedClient(paymentPage)
                .post(CREATE_PAYMENT_PAGE_URI);
    }
}
