package com.solidgate.api.client;

import static com.solidgate.api.utils.JsonUtils.convertObjectToJsonString;
import static com.solidgate.core.utils.Constants.MERCHANT;
import static com.solidgate.core.utils.Constants.SIGNATURE;

import com.solidgate.api.utils.SignatureGenerator;
import com.solidgate.core.EnvProperties;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public abstract class AuthenticatedClient {

    private final String merchant;
    private final String secretKey;
    private final String baseUri;

    public AuthenticatedClient(String baseUri) {
        this.merchant = EnvProperties.getPublicKey();
        this.secretKey = EnvProperties.getSecretKey();
        this.baseUri = baseUri;
    }

    protected RequestSpecification getAuthenticatedClient(Object requestBody) {
        Map<String, String> authHeaders = getAuthHeaders(requestBody);
        return RestAssured.given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .headers(authHeaders)
                .baseUri(baseUri)
                .body(requestBody);
    }

    private Map<String, String> getAuthHeaders(Object requestBody) {
        String jsonString = convertObjectToJsonString(requestBody);
        return Map.of(
                MERCHANT, EnvProperties.getPublicKey(),
                SIGNATURE, SignatureGenerator.generateSignature(merchant, jsonString, secretKey)
        );
    }
}
