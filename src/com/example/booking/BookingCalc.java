package com.example.booking;

public class BookingCalc {
    public BookingCalc() {
    }

    public static double calculate(int var0, int var1, int var2, int var3, int var4) {
        if(var0 >= 1 && var4 <= 10000000) {
            int var5 = (var0 * var1);
            int var6 = (var5 * var4) + var3 + var4;
            return var6 * var0;
        } else {
            throw new RuntimeException("Invalid calculation");
        }
    }
}