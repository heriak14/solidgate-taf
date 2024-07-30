package com.solidgate.paymentpage;

import static com.solidgate.core.utils.Constants.SUCCESS;
import static com.solidgate.core.utils.Constants.URL;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.codeborne.selenide.Selenide;
import com.jayway.jsonpath.JsonPath;
import com.solidgate.api.client.OrderClient;
import com.solidgate.api.client.PaymentPageClient;
import com.solidgate.core.EnvProperties;
import com.solidgate.core.model.CreditCardDto;
import com.solidgate.core.model.PaymentPageDto;
import com.solidgate.core.testdata.provider.CreditCardProvider;
import com.solidgate.core.testdata.provider.PaymentPageProvider;
import com.solidgate.ui.facade.PaymentPageFacade;
import com.solidgate.ui.page.PaymentPage;
import com.solidgate.ui.page.SuccessPaymentPage;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreatePaymentPageTest {

    private final PaymentPageClient paymentPageClient = new PaymentPageClient();
    private final OrderClient orderClient = new OrderClient();
    private PaymentPageDto paymentPageDto;
    private PaymentPageFacade paymentPageFacade;

    @BeforeMethod
    public void createPaymentPage() {
        paymentPageDto = new PaymentPageProvider().provide();
        String pageUrl = paymentPageClient.createPaymentPage(paymentPageDto)
                .then().statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath().getString(URL);
        PaymentPage paymentPage = Selenide.open(pageUrl, PaymentPage.class);
        paymentPageFacade = new PaymentPageFacade(paymentPage);
    }

    @Test(description = "Verify user can pay for order and success message appears")
    public void testSuccessMessageAppearsAfterPayment() {
        CreditCardDto creditCardDto = new CreditCardProvider().provide();
        paymentPageFacade.payForOrder(creditCardDto);
        SuccessPaymentPage successPaymentPage = new SuccessPaymentPage();
        successPaymentPage.waitForSuccessMessage();
        String expectedSuccessUrl = EnvProperties.getPaymentSuccessUrl();
        String actualSuccessUrl = successPaymentPage.getUrl();
        assertEquals(actualSuccessUrl, expectedSuccessUrl,
                "Success page URL should be '%s' but instead found '%s'".formatted(expectedSuccessUrl, actualSuccessUrl));
        assertTrue(successPaymentPage.isSuccessMessageVisible(), "Success message should be visible");
    }

    @Test(description = "Verify user can pay for order and check order status")
    public void testOrderStatusIsCorrectAfterPayment() {
        CreditCardDto creditCardDto = new CreditCardProvider().provide();
        paymentPageFacade.payForOrder(creditCardDto);
        SuccessPaymentPage successPaymentPage = new SuccessPaymentPage();
        successPaymentPage.waitForSuccessMessage();
        Response response = orderClient.getOrderStatus(paymentPageDto.getOrder().getOrderId())
                .then().statusCode(HttpStatus.SC_OK)
                .extract().response();

        SoftAssert softAssert = new SoftAssert();
        int actualAmount = response.jsonPath().get("order.amount");
        int expectedAmount = paymentPageDto.getOrder().getAmount();
        softAssert.assertEquals(actualAmount, expectedAmount,
                "Order amount should be '%s' but instead found '%s'".formatted(expectedAmount, actualAmount));

        String actualCurrency = response.jsonPath().getString("order.currency");
        String expectedCurrency = paymentPageDto.getOrder().getCurrency();
        softAssert.assertEquals(actualCurrency, expectedCurrency,
                "Order currency should be '%s' but instead found '%s'".formatted(expectedCurrency, actualCurrency));

        String actualTransactionStatus = JsonPath.parse(response.asString()).read("$.transactions.*.status", String[].class)[0];
        softAssert.assertEquals(actualTransactionStatus, SUCCESS,
                "Transaction status should be '%s' but instead found '%s'".formatted(SUCCESS, actualTransactionStatus));

        softAssert.assertAll();
    }
}
