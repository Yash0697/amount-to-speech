package com.yash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmountReader {
    private Map<Integer, String> pronounciations;

    public AmountReader() {
        pronounciations = new HashMap<>();
        List<String> words = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                    "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twentee"
        );
        for(int i = 1; i < 21; i++) {
            pronounciations.put(i, words.get(i - 1));
        }
        words = Arrays.asList("thirty", "fourtee", "fiftee", "sixtee", "seventee", "eightee", "ninetee",
        "hundred", "thousand", "lac");

        for(int i = 3; i <= 10; i++) {
            pronounciations.put(i * 10, words.get(i - 3));
        }

        pronounciations.put(1000, words.get(8));
        pronounciations.put(10000, words.get(9));

    }    

    public String findAmountPronounciation(Integer amount) {
        StringBuilder sb = new StringBuilder();
        String amtString = Integer.toString(amount);
        int length = amtString.length();
        String tens = null, hundreds = null, thousands = null, lacs = null;
        if(length == 1)
            return pronounciations.get(amount);
        if(length >= 2) 
            tens = amtString.substring(length - 2);
        if(length >= 3)
            hundreds = amtString.substring(length - 3, length - 2);
        if(length >= 5)
            thousands = amtString.substring(length - 5, length - 3);
        if(length >= 6)
            lacs = amtString.substring(0, length - 5);

        if(lacs != null) {
            sb.append(convertToWords(lacs));
            sb.append(" lac ");
        }

        if(thousands != null) {
            String words = convertToWords(thousands);
            sb.append(words);
            String toAppend = words.length() > 0 ? " thousand ": "";
            sb.append(toAppend);
        }

        if(hundreds != null) {
            String words = convertToWords(hundreds);
            sb.append(words);
            String toAppend = words.length() > 0 ? " hundred ": "";
            sb.append(toAppend);
        }

        if(tens != null) {
            sb.append(convertToWords(tens));
        }
        
        return sb.toString();
    }

    private String convertToWords(String amount) {
        if(amount.equals("0") || amount.equals("00")) return "";
        Integer amtInteger = Integer.parseInt(amount);
        int tensPlace = amtInteger / 10;
        int unitsPlace = amtInteger % 10;
        String words = "";
        if(tensPlace >= 2) {
            words += pronounciations.get(tensPlace * 10);
            words += " ";
            words += pronounciations.get(unitsPlace);
        }
        else if(tensPlace > 0) words += pronounciations.get(amtInteger);
        else if(tensPlace == 0) words += pronounciations.get(unitsPlace);
        return words;
    }

}
