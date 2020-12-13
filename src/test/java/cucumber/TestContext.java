package cucumber;

import apiEngine.EndPoints;
import dataProvider.ConfigReader;
import enums.Context;

public class TestContext {

    private EndPoints endPoints =new EndPoints(ConfigReader.getInstance().getBaseUrl());;

    private ScenarioContext scenarioContext;

    public TestContext() {
        scenarioContext = new ScenarioContext();
        scenarioContext.setContext(Context.LOGIN_ID,ConfigReader.getInstance().getLoginId());
        scenarioContext.setContext(Context.API_KEY,ConfigReader.getInstance().getApiKey());
    }

    public EndPoints getEndPoints() {
        return endPoints;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

}
