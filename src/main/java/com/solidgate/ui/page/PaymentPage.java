package com.solidgate.ui.page;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class PaymentPage extends BasePage {

    private final SelenideElement cardNumberInput = $("input#ccnumber");
    private final SelenideElement cardExpirationInput = $("[data-testid='cardExpiryDate']");
    private final SelenideElement cardCvcInput = $("input#cvv2");
    private final SelenideElement cardHolderInput = $("[data-testid='cardHolder']");
    private final SelenideElement payButton = $("[data-testid='submit']");

    public PaymentPage enterCardNumber(String cardNumber) {
        cardNumberInput
                .as("Card Number input")
                .shouldBe(visible)
                .sendKeys(cardNumber);
        return this;
    }

    public PaymentPage enterCardExpiration(String cardExpiration) {
        cardExpirationInput
                .as("Card Expiration input")
                .shouldBe(visible)
                .sendKeys(cardExpiration);
        return this;
    }

    public PaymentPage enterCardCvc(String cardCvc) {
        cardCvcInput
                .as("Card CVC input")
                .shouldBe(visible)
                .sendKeys(cardCvc);
        return this;
    }

    public PaymentPage enterCardHolder(String cardHolder) {
        cardHolderInput
                .as("Card Holder input")
                .shouldBe(visible)
                .sendKeys(cardHolder);
        return this;
    }

    public void clickPayButton() {
        payButton
                .as("Pay button")
                .shouldBe(enabled)
                .click();
    }
}
