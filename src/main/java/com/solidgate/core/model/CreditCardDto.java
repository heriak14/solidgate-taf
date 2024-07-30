package com.solidgate.core.model;

import lombok.Data;

@Data
public class CreditCardDto {

    private String testId;
    private String cardNumber;
    private String expirationDate;
    private String cvv;
    private String cardHolder;
}