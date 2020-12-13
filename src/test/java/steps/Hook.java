package steps;

import cucumber.TestContext;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook extends BaseStep {


    public Hook(TestContext testContext) {
        super(testContext);
    }

    @Before
    public void hookUp(Scenario scenario) {
        logger.info("***Execution of \"" + scenario.getName() + "\" has started.***");
    }

    @After
    public void teardown(Scenario scenario) {
        logger.info("***Execution of \"" + scenario.getName() + "\" is completed. Result " + scenario.getStatus() + "***");
    }

}
