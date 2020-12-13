package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;

public class conversionRateVerification extends BaseStep {

    private static final String login_id = "mailangel08@gmail.com";
    private static final String api_key = "440f058d969d61cad8225e0e50502af35777888cf4f438c12a668839743f6e56";
    private static final String BASE_URL = "https://devapi.currencycloud.com/v2";
    private static final String buy_currency = "USD";
    private static final String sell_currency = "GBP";
    private static final String fixed_side = "sell";
    private static final String amount = "1500";
    private static final String term_agreement = "true";

    private static String token;
    // private static String beneficiary_id;
    private static Double buy_amount;
    private static Double sell_amount;
    private static Double rate;
    private static Double expectedAmount;
    private static String conversion_id;
    private static Response response;


    @Given("I am an authorized user")
    public void iAmAnAuthorizedUser() {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        JSONObject requestParams = new JSONObject();
        requestParams.put("login_id", login_id);
        requestParams.put("api_key", api_key);

        request.body(requestParams.toJSONString());

        Response response = request.post("/authenticate/api");

        printOutputLog(response);
        String jsonString = response.asString();
        logger.info("Authentication Response : " + jsonString);
        token = JsonPath.from(jsonString).get("auth_token");
        logger.info("Authentication Token : " + token);
        Assert.assertEquals(200, response.getStatusCode());


    }

    @Then("I query detailed rates for GBP to USD")
    public void iQueryDetailedRatesForGBPToUSD() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("X-Auth-Token", token)
                .header("Content-Type", "application/json");

        Response response = request.queryParam("buy_currency", "USD")
                .queryParam("sell_currency", "GBP")
                .queryParam("fixed_side", "sell")
                .queryParam("amount", "1500")
                .get("/rates/detailed");


        printOutputLog(response);
        String jsonString = response.asString();
        logger.info("Conversion Rate Details :" + jsonString);
        Assert.assertEquals(jsonString.contains("1500"), true);
        Assert.assertEquals(200, response.getStatusCode());

    }

    @Then("I Create a Quote for Conversion to sell GBP and buy USD")
    public void iCreateAQuoteForConversionToSellGBPAndBuyUSD() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("X-Auth-Token", token)
                .header("Content-Type", "application/json");

        JSONObject requestParams = new JSONObject();
        requestParams.put("buy_currency", buy_currency);
        requestParams.put("sell_currency", sell_currency);
        requestParams.put("fixed_side", fixed_side);
        requestParams.put("amount", amount);
        requestParams.put("term_agreement", term_agreement);

        request.body(requestParams.toJSONString());

        Response response = request.post("/conversions/create");

        printOutputLog(response);
        String jsonString = response.asString();
        logger.info("Conversion Details : " + jsonString);
        conversion_id = JsonPath.from(jsonString).get("id");
        buy_amount = JsonPath.from(jsonString).getDouble("client_buy_amount");
        sell_amount = JsonPath.from(jsonString).getDouble("client_sell_amount");
        rate = JsonPath.from(jsonString).getDouble("core_rate");

        Assert.assertEquals(200, response.getStatusCode());


    }

    @Then("verify buy amount equals to conversion rate")
    public void verify_buy_amount_equals_to_conversion_rate() {
        expectedAmount = sell_amount * rate;
        Assert.assertEquals(expectedAmount, buy_amount);

    }

    @Then("verify buy amount not equals to conversion rate")
    public void verifyBuyAmountNotEqualsToConversionRate() {
        expectedAmount = sell_amount * rate;
        Assert.assertNotEquals(expectedAmount, buy_amount);

    }


    @And("End the Session")
    public void endTheSession() {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("X-Auth-Token", token)
                .header("Content-Type", "application/json");

        Response response = request.post("authenticate/close_session");

        printOutputLog(response);
        String jsonString = response.asString();
        Assert.assertEquals(200, response.getStatusCode());
    }


}
