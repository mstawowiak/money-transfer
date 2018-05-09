package com.github.mstawowiak.money.transfer.application;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TransactionApiIT extends AbstractRestApiIT {

    private static final String API_V1_TRANSACTION_PATH = API_V1_PATH + "/transaction";
    private static final String API_V1_ACCOUNTS_PATH    = API_V1_PATH + "/accounts";

    @Test
    public void shouldMakeTransaction() {
        String sourceAccountId = "IT100EUR";
        String destinationAccountId = "IT200EUR";

        String request = "{" +
                "\"sourceAccountId\" : \"" + sourceAccountId + "\"," +
                "\"destinationAccountId\" : \"" + destinationAccountId + "\"," +
                "\"amount\" : 100," +
                "\"currencyCode\" : \"EUR\"" +
                "}";

        given().body(request).contentType(ContentType.JSON)
                .when().post(API_V1_TRANSACTION_PATH)
                .then().statusCode(204);

        given().when().get(API_V1_ACCOUNTS_PATH + "/" + sourceAccountId)
                .then().statusCode(200)
                .and().body("accountId", equalTo(sourceAccountId))
                .and().body("balance", is(0.00f));

        given().when().get(API_V1_ACCOUNTS_PATH + "/" + destinationAccountId)
                .then().statusCode(200)
                .and().body("accountId", equalTo(destinationAccountId))
                .and().body("balance", is(300.00f));
    }

    @Test
    public void shouldReturn400WhenNotEnoughMoneyOnSourceAccount() {
        String request = "{" +
                "\"sourceAccountId\" : \"IT300EUR\"," +
                "\"destinationAccountId\" : \"IT400EUR\"," +
                "\"amount\" : 400," +
                "\"currencyCode\" : \"EUR\"" +
                "}";

        given().body(request).contentType(ContentType.JSON)
                .when().post(API_V1_TRANSACTION_PATH)
                .then().statusCode(400)
                .and().body(equalTo("Money transfer error: Cannot withdraw more money than actual balance"));
    }

    @Test
    public void shouldReturn400WhenTransferWrongCurrencyFromSourceAccount() {
        String request = "{" +
                "\"sourceAccountId\" : \"IT300EUR\"," +
                "\"destinationAccountId\" : \"PL700PLN\"," +
                "\"amount\" : 100," +
                "\"currencyCode\" : \"PLN\"" +
                "}";

        given().body(request).contentType(ContentType.JSON)
                .when().post(API_V1_TRANSACTION_PATH)
                .then().statusCode(400)
                .and().body(equalTo("Money transfer error: Currency mismatch: EUR != PLN"));
    }

    @Test
    public void shouldReturn400WhenTransferWrongCurrencyToDestinationAccount() {
        String request = "{" +
                "\"sourceAccountId\" : \"IT300EUR\"," +
                "\"destinationAccountId\" : \"PL700PLN\"," +
                "\"amount\" : 100," +
                "\"currencyCode\" : \"EUR\"" +
                "}";

        given().body(request).contentType(ContentType.JSON)
                .when().post(API_V1_TRANSACTION_PATH)
                .then().statusCode(400)
                .and().body(equalTo("Money transfer error: Currency mismatch: PLN != EUR"));
    }
}
