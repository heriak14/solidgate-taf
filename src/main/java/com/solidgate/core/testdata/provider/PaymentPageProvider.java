package com.solidgate.core.testdata.provider;

import com.github.javafaker.Faker;
import com.solidgate.core.EnvProperties;
import com.solidgate.core.model.PaymentPageDto;
import com.solidgate.core.testdata.reader.JsonReader;
import java.util.UUID;

public class PaymentPageProvider implements Provider<PaymentPageDto> {

    private static final String FILE_PATH = "testdata/payment-page.json";
    private final JsonReader<PaymentPageDto> jsonReader = new JsonReader<>(FILE_PATH, PaymentPageDto.class);
    private final Faker faker = new Faker();

    @Override
    public PaymentPageDto provide() {
        PaymentPageDto paymentPage = jsonReader.readOne();
        paymentPage.getOrder().setOrderId(UUID.randomUUID().toString());
        paymentPage.getOrder().setAmount(faker.number().numberBetween(0, 100000));
        paymentPage.getOrder().setOrderDescription(faker.lorem().sentence());
        paymentPage.getOrder().setCustomerEmail(faker.internet().emailAddress());
        paymentPage.getOrder().setCustomerFirstName(faker.name().firstName());
        paymentPage.getOrder().setCustomerLastName(faker.name().lastName());
        paymentPage.getOrder().setCustomerPhone(faker.phoneNumber().cellPhone());
        paymentPage.getOrder().setSuccessUrl(EnvProperties.getPaymentSuccessUrl());
        return paymentPage;
    }
}
