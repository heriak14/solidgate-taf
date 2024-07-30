package com.solidgate.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class OrderDto {

    @JsonProperty("force3ds")
    private boolean force3ds;

    @JsonProperty("google_pay_allowed_auth_methods")
    private List<String> googlePayAllowedAuthMethods;

    @JsonProperty("order_description")
    private String orderDescription;

    @JsonProperty("order_number")
    private int orderNumber;

    @JsonProperty("language")
    private String language;

    @JsonProperty("type")
    private String type;

    @JsonProperty("fail_url")
    private String failUrl;

    @JsonProperty("customer_last_name")
    private String customerLastName;

    @JsonProperty("geo_city")
    private String geoCity;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("retry_attempt")
    private int retryAttempt;

    @JsonProperty("customer_date_of_birth")
    private String customerDateOfBirth;

    @JsonProperty("order_items")
    private String orderItems;

    @JsonProperty("success_url")
    private String successUrl;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("website")
    private String website;

    @JsonProperty("customer_first_name")
    private String customerFirstName;

    @JsonProperty("purchase_country")
    private String purchaseCountry;

    @JsonProperty("customer_phone")
    private String customerPhone;

    @JsonProperty("transaction_source")
    private String transactionSource;

    @JsonProperty("order_metadata")
    private OrderMetadataDto orderMetadata;

    @JsonProperty("traffic_source")
    private String trafficSource;

    @JsonProperty("settle_interval")
    private int settleInterval;

    @JsonProperty("order_date")
    private String orderDate;

    @JsonProperty("customer_email")
    private String customerEmail;

    @JsonProperty("geo_country")
    private String geoCountry;

    @JsonProperty("order_id")
    private String orderId;
}