package com.solidgate.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentPageDto {

    @JsonProperty("page_customization")
    private PageCustomizationDto pageCustomization;

    @JsonProperty("order")
    private OrderDto order;
}