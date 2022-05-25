package com.carola;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;

public class CurrencyAPIConnector extends APIConnector {
    public static final String API_KEY = "67LWnsxMc5O95oVbWFZRxPmMamtjEUaw8I6UgnFE";
    public static final String URL_ADDRESS = "https://freecurrencyapi.net/api/v3/";
    public static final String LATEST = "latest";
    public static final String HISTORICAL = "historical";

    public JSONObject getLatestData() {
        String data = getData(URL_ADDRESS + LATEST, API_KEY, null);
        JSONTokener tokener = new JSONTokener(data);
        JSONObject jsonCurrency = new JSONObject(tokener);

        return jsonCurrency;
    }

    public JSONObject getHistoricalData(String baseCurrency, String date) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("base_currency", baseCurrency);
        parameters.put("date", date);

        String data = getData(URL_ADDRESS + HISTORICAL, API_KEY, parameters);
        JSONTokener tokener = new JSONTokener(data);
        JSONObject jsonCurrency = new JSONObject(tokener);

        return jsonCurrency;
    }

    public static void getExchangeHistorical(String date, double amount) {
        CurrencyAPIConnector CAC = new CurrencyAPIConnector();
        double oldValue = CAC.getHistoricalData("USD", date).getJSONObject("data").getJSONObject("PLN").getDouble("value");
        double newValue = CAC.getLatestData().getJSONObject("data").getJSONObject("PLN").getDouble("value");
        if (oldValue < newValue) {
            System.out.println("This is a good day to sell");
            System.out.println("You will earn: " + ((newValue * amount) - (oldValue * amount)) + "PLN");
        } else {
            System.out.println("This is not a good day to sell");
            System.out.println("You will lose: " + ((oldValue * amount) - (newValue * amount)) + "PLN");
        }
    }
}
