package Requests;


public class AuthenticationRequest {

    public String login_id;
    public String api_key;
    /**
     *
     * @param login_id
     * @param api_key
     */
    public AuthenticationRequest(String login_id, String api_key) {
        this.login_id = login_id;
        this.api_key = api_key;
    }

}