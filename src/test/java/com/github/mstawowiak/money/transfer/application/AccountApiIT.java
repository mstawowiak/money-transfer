package com.github.mstawowiak.money.transfer.application;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;

public class AccountApiIT extends AbstractRestApiIT {

    private static final String API_V1_ACCOUNTS_PATH = API_V1_PATH + "/accounts";

    @Test
    public void shouldGetAccount() {
        String accountId = "IT500EUR";

        given().when().get(API_V1_ACCOUNTS_PATH + "/" + accountId)
                .then().statusCode(200)
                .and().body("accountId", equalTo(accountId))
                .and().body("balance", is(500.00f))
                .and().body("currencyCode", equalTo("EUR"));
    }

    @Test
    public void shouldGetAllAccounts() {
        given().when().get(API_V1_ACCOUNTS_PATH)
                .then().statusCode(200)
                .and().body("", hasSize(10));
    }
}
