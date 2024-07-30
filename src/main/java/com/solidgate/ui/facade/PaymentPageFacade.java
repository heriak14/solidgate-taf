package com.solidgate.ui.facade;

import com.solidgate.core.model.CreditCardDto;
import com.solidgate.ui.page.PaymentPage;

public class PaymentPageFacade {

    private final PaymentPage paymentPage;

    public PaymentPageFacade(PaymentPage paymentPage) {
        this.paymentPage = paymentPage;
    }

    public void payForOrder(CreditCardDto creditCardDto) {
        paymentPage.enterCardNumber(creditCardDto.getCardNumber())
                .enterCardExpiration(creditCardDto.getExpirationDate())
                .enterCardCvc(creditCardDto.getCvv())
                .enterCardHolder(creditCardDto.getCardHolder())
                .clickPayButton();
    }
}
