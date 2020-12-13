package steps;

import io.restassured.response.Response;
import org.apache.log4j.Logger;

public class BaseStep {

    public final Logger logger = Logger.getLogger(BaseStep.class);

    public void printOutputLog(Response response) {
        response.then().log().all();
    }
}

