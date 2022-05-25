package com.carola;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Wallet {

    public CurrencyAPIConnector currencyAPIConnector;
    Map<Currencies, Sell> wallet;
    private DBConnector connector;
    private Currencies lastCurrency;

    public Wallet() {
        this.wallet = new ConcurrentHashMap<>();
        this.currencyAPIConnector = new CurrencyAPIConnector();
        this.connector = new DBConnector();
    }

    public void buyCurrency(Currencies currency, double amount) {
        double newValue = 4.30;
        for (Currencies key : wallet.keySet()) {
            if (currency == key){
                this.lastCurrency = key;
            }
        }



        if (wallet.containsKey(currency)) {

            double previousAmount = wallet.get(currency).getAmount();

            String query2 = "UPDATE transaction SET wallet = FALSE WHERE currency = '" +this.lastCurrency +"'" + " and amount = " + previousAmount;
            String query = "insert into transaction(currency, amount, exchangerate, date, wallet) values ('" + currency + "', '" + (wallet.get(currency).getAmount() + amount) + "', '" + newValue + "', '" + new Date() + "', 'TRUE')";


            connector.insertUpdateQuery(query);
            connector.insertUpdateQuery(query2);
            wallet.put(currency, new Sell(newValue, wallet.get(currency).getAmount() + amount));


        } else {
            String query = "insert into transaction(currency, amount, exchangerate, date, wallet) values ('" + currency + "', '" + amount + "', '" + newValue + "', '" + new Date() + "', 'TRUE')";
            connector.insertUpdateQuery(query);
            wallet.put(currency, new Sell(newValue, amount));


        }

    }


    public void printWallet() {
        if (wallet.size() > 0) {
            for (Currencies key : wallet.keySet()) {
                System.out.println("Currency: " + key + ", " + wallet.get(key));
            }
        } else {
            System.out.println("The wallet is empty");
        }
    }

    public void loadTransactions() {
        DBConnector dataBaseConnector = new DBConnector();

        try {

            ResultSet singleTransaction = dataBaseConnector.selectQuery("SELECT  date, currency, amount, exchangerate, id FROM public.transaction where wallet = true order by date desc");
            while (singleTransaction.next()) {
                String currency = singleTransaction.getString("currency");
                double amount = singleTransaction.getDouble("amount");
                double value = singleTransaction.getDouble("exchangerate");
                System.out.println("currency: "+currency+ " amount: "+amount+" value: "+value);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}