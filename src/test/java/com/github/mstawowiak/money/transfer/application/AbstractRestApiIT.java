package com.github.mstawowiak.money.transfer.application;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public abstract class AbstractRestApiIT {

    protected static final String API_V1_PATH = "api/v1";

    @BeforeClass
    public static void setup() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9090;
        RestAssured.basePath = "money-transfer";
    }
}
