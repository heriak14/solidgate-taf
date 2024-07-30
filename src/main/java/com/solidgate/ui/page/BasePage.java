package com.solidgate.ui.page;

import com.codeborne.selenide.WebDriverRunner;

public class BasePage {

    public String getUrl() {
        return WebDriverRunner.url();
    }
}
