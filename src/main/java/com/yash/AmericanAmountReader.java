package com.yash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmericanAmountReader {

	private Map<Integer, String> pronounciations;
	
	public AmericanAmountReader() {
		pronounciations = new HashMap<>();
        List<String> words = Arrays.asList("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                    "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"
        );
        for(int i = 1; i < 21; i++) {
            pronounciations.put(i, words.get(i - 1));
        }
        words = Arrays.asList("Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety",
        "Hundred", "Thousand", "Million", "Billion");

        for(int i = 3; i <= 10; i++) {
            pronounciations.put(i * 10, words.get(i - 3));
        }

        pronounciations.put(1000, words.get(8));
        pronounciations.put(1000000, words.get(9));
        pronounciations.put(1000000000, words.get(10));
	}
	
    public String findPronounciation(Integer num) {
    	if(num == 0) return "Zero";
        StringBuilder sb = new StringBuilder();
        String numString = Integer.toString(num);
        int length = numString.length();
        String tens = null, hundreds = null, thousands = null, millions = null, billions = null;
        if(length == 1)
            return pronounciations.get(num);
        if(length >= 2) 
            tens = numString.substring(length - 2);
        if(length >= 3)
            hundreds = numString.substring(length - 3, length - 2);
        if(length >= 4)
            thousands = numString.substring(Math.max(0, length - 6), length - 3);
        if(length >= 7)
            millions = numString.substring(Math.max(0, length - 9), length - 6);
        if(length >= 10)
            billions = numString.substring(Math.max(0, length - 12), length - 9);

        if(billions != null) {
            sb.append(convertToWords(billions));
            sb.append(" Billion ");
        }

        if(millions != null) {
            String words = convertToWords(millions);
            sb.append(words);
            String toAppend = words.length() > 0 ? " Million ": "";
            sb.append(toAppend);
        }

        if(thousands != null) {
            String words = convertToWords(thousands);
            sb.append(words);
            String toAppend = words.length() > 0 ? " Thousand ": "";
            sb.append(toAppend);
        }

        if(hundreds != null) {
            String words = convertToWords(hundreds);
            sb.append(words);
            String toAppend = words.length() > 0 ? " Hundred ": "";
            sb.append(toAppend);
        }

        if(tens != null) {
            sb.append(convertToWords(tens));
        }
        
        return sb.toString().trim();
    }

    private String convertToWords(String num) {
        // System.out.println(num);
        if(num.equals("0") || num.equals("00")) return "";
        Integer numInteger = Integer.parseInt(num);
        int hundredsPlace = numInteger / 100;
    
        int tensPlace = 0;
        if(num.length() >= 2)
            tensPlace = Integer.parseInt(num.substring(num.length() - 2)) / 10;
        int unitsPlace = numInteger % 10;
        String words = "";
        words = words + (hundredsPlace > 0 ? (pronounciations.get(hundredsPlace) + " Hundred "):"");       
        // System.out.println(tensPlace);
        if(tensPlace >= 2) {
            words += pronounciations.get(tensPlace * 10);
            words += pronounciations.get(unitsPlace) == null ? "": " " + pronounciations.get(unitsPlace);
        }
        else if(tensPlace > 0) words += pronounciations.get(tensPlace * 10 + unitsPlace);
        else if(tensPlace == 0) words += pronounciations.get(unitsPlace) == null ? "":pronounciations.get(unitsPlace);
        return words.trim();
    }
}
