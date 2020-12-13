package apiEngine;

public class Route {
    private static final String AUTHENTICATION = "/authenticate";
    private static final String RATES = "/rates/detailed";
    private static final String CONVERSION = "/conversions";

    public static String generateAuthToken() {
        return AUTHENTICATION + "/api";
    }

    public static String getDetailedRates() {
        return RATES;
    }

    public static String logoutSession() {
        return AUTHENTICATION + "/close_session";
    }
    public static String createConversion() {
        return CONVERSION + "/create";
    }




}
