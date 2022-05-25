package com.carola;

import org.json.JSONObject;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Wallet wallet = new Wallet();
        System.out.println("Transactions saved");
        wallet.loadTransactions();
    }
}
