package apiEngine;

import Requests.AuthenticationRequest;
import Requests.ConversionRequest;
import Responses.AuthenticationResponse;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

public class EndPoints {

    private final RequestSpecification requestSpecification;

    public EndPoints(String baseUrl) {
        RestAssured.baseURI = baseUrl;
        requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.filter(new AllureRestAssured());
    }

    public void authenticateUser(AuthenticationRequest authRequest) {
        Response response = requestSpecification.body(authRequest).post(Route.generateAuthToken());
        if (response.statusCode() != HttpStatus.SC_OK)
            throw new RuntimeException("Authentication Failed. Content of failed Response: " + response.asString() + " , Status Code : " + response.statusCode());
        AuthenticationResponse tokenResponse = response.getBody().as(AuthenticationResponse.class);
        requestSpecification.header("X-Auth-Token", tokenResponse.auth_token);
    }

    public Response getDetailedRates(String buyCurrency,String sellCurrency,String fixedSide,String amount) {

        Response response = requestSpecification.queryParam("buy_currency", buyCurrency)
                .queryParam("sell_currency",sellCurrency)
                .queryParam("fixed_side",fixedSide)
                .queryParam("amount", amount)
                .get(Route.getDetailedRates());
        return response;
    }

    public Response createConversion(ConversionRequest conversionRequest) {
        Response response = requestSpecification.body(conversionRequest).post(Route.createConversion());
        return response;
    }

    public Response endSession() {
        Response response = requestSpecification.post(Route.logoutSession());
        return response;
    }
}
