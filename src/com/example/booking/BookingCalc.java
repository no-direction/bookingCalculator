package com.example.booking;

public class BookingCalc {
    public BookingCalc() {
    }

    public static double calculate(int var0, int var1, int var2, int var3, int var4) {
        if(var2 <= 1000000) {
            int baseCost = (var0 * var1) / 2;
            int timesDays = (baseCost * var4);
            int extras = timesDays + var2 + var3;
            return extras;
        } else {
            throw new RuntimeException("Invalid calculation");
        }
    }
}