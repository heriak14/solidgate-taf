package com.solidgate.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderMetadataDto {

    @JsonProperty("coupon_code")
    private String couponCode;

    @JsonProperty("partner_id")
    private String partnerId;
}