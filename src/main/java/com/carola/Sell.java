package com.carola;

public class Sell {
    private double value;
    private double quantity;

    public Sell(double value, double quantity) {
        this.value = value;
        this.quantity = quantity;
    }

    public double getAmount() {
        return value;
    }

    @Override
    public String toString() {
        return "quantity: " + quantity + ", value: " + value;
    }
}

