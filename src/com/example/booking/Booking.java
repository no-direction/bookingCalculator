package com.example.booking;

import java.io.File;
import java.util.Scanner;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.regex.*;

public class Booking {
    public static void main(String[] args) throws Exception {

        if (args.length == 1) {

            String fileName = args[0];
            String fileContent = new Scanner(new File(fileName))
                    .useDelimiter("\\Z").next();

            ArrayList<Integer> parsedContent = parseContentFromFileContent(fileContent);

            LinkedHashMap<String, Integer> outputMethod = renderOutput(parsedContent);

            double discountValue = 0.05;

            double subTotal = BookingCalc.calculate(outputMethod.get("Type"), outputMethod.get("Price"), outputMethod.get("Guests"),
                    outputMethod.get("Amenities"), outputMethod.get("Days"));

            System.out.println("Sub-total: £" + new DecimalFormat("#.00").format(subTotal));

            double total = checkForDiscount(outputMethod.get("Days"), subTotal, discountValue);

            System.out.println("Total: £" + new DecimalFormat("#.00").format(total));
            return;
        }

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter your type of booking:");
        int type = scan.nextInt();

        System.out.println("Enter the standard rate");
        int price = scan.nextInt();

        System.out.println("Enter the number of guests");
        int guests = scan.nextInt();

        System.out.println("Enter costs for any other amenities");
        int amenities = scan.nextInt();

        System.out.println("Enter the number of days");
        int days = scan.nextInt();

        double discountValue = 0.05;
        double subTotal = BookingCalc.calculate(type, price, guests, amenities, days);

        System.out.println("Sub-total: £" + new DecimalFormat("#.00").format(subTotal));

        double total = checkForDiscount(days, subTotal, discountValue);

        System.out.println("Total: £" + new DecimalFormat("#.00").format(total));
    }

    public static double checkForDiscount(int days, double subTotal, double discountValue) {
        double newPrice = subTotal;

        if (days >= 14) {
            double calculation = subTotal * discountValue;
            System.out.println("Discount: £" + calculation);
            newPrice = applyDiscount(discountValue, subTotal);
        } else {
            System.out.println("No discount");
        }
        return newPrice;
    }

    public static double applyDiscount(double discountValue, double subTotal) {
        return subTotal - (subTotal * discountValue);
    }

    public static ArrayList<Integer> parseContentFromFileContent(String fileContent) {

        ArrayList<Integer> parsedInts = new ArrayList<>();

        String pattern = "([a-z]{2})-(\\d+)\\n[g]-(\\d+)\\n([a-z]{4})-(\\d+)";
        Pattern p = Pattern.compile(pattern, Pattern.DOTALL);
        Matcher m = p.matcher(fileContent);

        if (m.matches()) {
            switch (m.group(1)) {
                case "sr":
                    parsedInts.add(1);
                    break;
                case "pr":
                    parsedInts.add(2);
                    break;
                case "eh":
                    parsedInts.add(3);
                    break;
            }

            parsedInts.add(Integer.parseInt(m.group(2)));
            parsedInts.add(Integer.parseInt(m.group(3)));

            switch (m.group(4)) {
                case "kitc":
                    parsedInts.add(1);
                    break;
                case "park":
                    parsedInts.add(2);
                    break;
                case "wifi":
                    parsedInts.add(3);
                    break;
            }
            parsedInts.add(Integer.parseInt(m.group(5)));
        }
        return parsedInts;
    }

    public static LinkedHashMap<String, Integer> renderOutput(ArrayList<Integer> parsedContent) {
        LinkedHashMap<String, Integer> renderedOutputMap = new LinkedHashMap<String, Integer>();

        String type = "Type";
        String price = "Price";
        String guests = "Guests";
        String amenities = "Amenities";
        String days = "Days";

        renderedOutputMap.put(type, parsedContent.get(0));
        renderedOutputMap.put(price, parsedContent.get(1));
        renderedOutputMap.put(guests, parsedContent.get(2));
        renderedOutputMap.put(amenities, parsedContent.get(3));
        renderedOutputMap.put(days, parsedContent.get(4));

        Set set = renderedOutputMap.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            System.out.println(mentry.getKey() + ": " + mentry.getValue());
        }

        return renderedOutputMap;
    }

}