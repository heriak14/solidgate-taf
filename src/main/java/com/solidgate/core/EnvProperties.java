package com.solidgate.core;

import java.util.Properties;

public class EnvProperties {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try {
            PROPERTIES.load(EnvProperties.class.getClassLoader().getResourceAsStream("env.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    public static String getPaymentPageBaseUrl() {
        return getProperty("payment.page.base.url");
    }

    public static String getCardPaymentsBaseUrl() {
        return getProperty("card.payments.base.url");
    }

    public static String getPaymentSuccessUrl() {
        return getProperty("payment.success.url");
    }

    public static String getPublicKey() {
        return getProperty("public.key");
    }

    public static String getSecretKey() {
        return getProperty("secret.key");
    }

    private static String getProperty(String key) {
        if (PROPERTIES.containsKey(key)) {
            return PROPERTIES.getProperty(key);
        } else if (System.getProperties().containsKey(key)) {
            return System.getProperty(key);
        } else {
            throw new RuntimeException("Property not defined: " + key);
        }
    }
}
