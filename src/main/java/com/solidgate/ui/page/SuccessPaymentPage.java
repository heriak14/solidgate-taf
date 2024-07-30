package com.solidgate.ui.page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class SuccessPaymentPage extends BasePage {

    private final SelenideElement successMessage = $("img[src*='PAYMENT-SUCCESS']");

    public void waitForSuccessMessage() {
        successMessage
                .as("Success Payment message")
                .shouldBe(visible);
    }
}
