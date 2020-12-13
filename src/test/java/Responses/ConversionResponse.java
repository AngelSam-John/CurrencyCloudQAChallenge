package Responses;

import java.util.List;

public class ConversionResponse {

    public String account_id;
    public String id;
    public String creator_contact_id;
    public String short_reference;
    public String settlement_date;
    public String conversion_date;
    public String status;
    public String partner_status;
    public String currency_pair;
    public String buy_currency;
    public String sell_currency;
    public String fixed_side;
    public String partner_buy_amount;
    public String partner_sell_amount;
    public String client_buy_amount;
    public String client_sell_amount;
    public String mid_market_rate;
    public String core_rate;
    public String partner_rate;
    public String client_rate;
    public Boolean deposit_required;
    public String deposit_amount;
    public String deposit_currency;
    public String deposit_status;
    public String deposit_required_at;
    public List<String> payment_ids = null;
    public String unique_request_id;
    public String created_at;
    public String updated_at;
}
