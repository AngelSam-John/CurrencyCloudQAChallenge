package steps;

import Requests.AuthenticationRequest;
import Requests.ConversionRequest;
import Responses.ConversionResponse;
import cucumber.TestContext;
import dataProvider.ConfigReader;
import enums.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

public class conversionRateVerification extends BaseStep {

    private static ConversionResponse conversionResponse;
    public static String accountId;

    public conversionRateVerification(TestContext testContext) {
        super(testContext);
    }

    @When("User generates an authentication token")
    public void User_generates_authentication_token() {
        logger.info(getScenarioContext().getContext(Context.LOGIN_ID).toString());
        logger.info(getScenarioContext().getContext(Context.API_KEY).toString());
        AuthenticationRequest authRequest = new AuthenticationRequest(getScenarioContext().getContext(Context.LOGIN_ID).toString(),
                (String) getScenarioContext().getContext(Context.API_KEY));
        getEndPoints().authenticateUser(authRequest);
    }

    @Then("User queries detailed rates for conversion with {string} {string} {string} and {string}")
    public void iQueryDetailedRatesForGBPToUSD(String buyCurrency, String sellCurrency, String fixedSide, String amount) {

        Response response = getEndPoints().getDetailedRates(buyCurrency, sellCurrency, fixedSide, amount);
        printOutputLog(response);
        getScenarioContext().setContext(Context.DETAILED_RATE_RESPONSE, response);
        String jsonString = response.asString();
        logger.info("Conversion Rate Details :" + jsonString);

    }

    @Then("User Creates a Conversion Quote to sell {string} and buy {string} with {string} and {string} and {string}")
    public void iCreateAQuoteForConversionToSellGBPAndBuyUSD(String buyCurrency, String sellCurrency, String fixedSide, String amount, String termAgreement) {
        int amountInInt = Integer.parseInt(amount);
        boolean agreementBoolean = Boolean.parseBoolean(termAgreement);
        ConversionRequest conversionRequest = new ConversionRequest(buyCurrency, sellCurrency, fixedSide, amountInInt, agreementBoolean);
        Response response = getEndPoints().createConversion(conversionRequest);
        printOutputLog(response);
        conversionResponse = response.getBody().as(ConversionResponse.class);
        getScenarioContext().setContext(Context.CONVERSION_RESPONSE, conversionResponse);
        logger.info("Conversion Details : " + conversionResponse);
        accountId = conversionResponse.id;
        getScenarioContext().setContext(Context.BUY_AMOUNT, Double.parseDouble(conversionResponse.client_buy_amount));
        getScenarioContext().setContext(Context.SELL_AMOUNT, Double.parseDouble(conversionResponse.client_sell_amount));
        getScenarioContext().setContext(Context.RATE, Double.parseDouble(conversionResponse.core_rate));
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("User verifies buy amount is correct with respect to conversion rate")
    public void verify_buy_amount_equals_to_conversion_rate() {
        Double price = (Double) getScenarioContext().getContext(Context.SELL_AMOUNT);
        Double rate = (Double) getScenarioContext().getContext(Context.RATE);
        Double actualAmount = (Double) getScenarioContext().getContext(Context.BUY_AMOUNT);
        Double expectedAmount = price * rate;
        Assert.assertEquals(expectedAmount, actualAmount);

    }

    @And("User disconnects the Session")
    public void endTheSession() {
        Response response = getEndPoints().endSession();
        printOutputLog(response);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @And("User should receive response code as {int}")
    public void validateResponseCode(int responseCode) {
        Response response = (Response) getScenarioContext().getContext(Context.DETAILED_RATE_RESPONSE);
        Assert.assertEquals(responseCode, response.getStatusCode());
        logger.info("Actual Response Code " + response.getStatusCode());
    }

}
