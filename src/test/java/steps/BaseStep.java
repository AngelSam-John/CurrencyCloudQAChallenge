package steps;

import apiEngine.EndPoints;
import cucumber.ScenarioContext;
import cucumber.TestContext;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class BaseStep {

    public final Logger logger = Logger.getLogger(BaseStep.class);

    private ScenarioContext scenarioContext;
    private EndPoints endPoints;

    public void printOutputLog(Response response) {
        response.then().log().all();
    }

    public BaseStep(TestContext testContext) {
        endPoints = testContext.getEndPoints();
        scenarioContext = testContext.getScenarioContext();
    }

    public EndPoints getEndPoints() {
        return endPoints;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }


}

