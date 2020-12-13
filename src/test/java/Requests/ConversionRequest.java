package Requests;


public class ConversionRequest {

    public String buy_currency;
    public String sell_currency;
    public String fixed_side;
    public Integer amount;
    public Boolean term_agreement;

    /**
     *
     * @param amount
     * @param term_agreement
     * @param fixed_side
     * @param sell_currency
     * @param buy_currency
     */
    public ConversionRequest(String buy_currency, String sell_currency, String fixed_side, Integer amount, Boolean term_agreement) {
        this.buy_currency = buy_currency;
        this.sell_currency = sell_currency;
        this.fixed_side = fixed_side;
        this.amount = amount;
        this.term_agreement = term_agreement;
    }

}