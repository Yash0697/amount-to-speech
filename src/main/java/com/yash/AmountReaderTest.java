package com.yash;


import com.sun.speech.freetts.Voice;

import com.sun.speech.freetts.VoiceManager;

public class AmountReaderTest {
    public static void main(String[] args) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        AmountReader amountReader = new AmountReader();
        String amountWords = amountReader.findAmountPronounciation(2112121);
        System.out.println(amountWords);
        textToSpeech(amountWords);
        amountWords = amountReader.findAmountPronounciation(5);
        System.out.println(amountWords);
        textToSpeech(amountWords);
        amountWords = amountReader.findAmountPronounciation(100000);
        System.out.println(amountWords);
        textToSpeech(amountWords);
        amountWords = amountReader.findAmountPronounciation(100005);
        System.out.println(amountWords);
        textToSpeech(amountWords);
        amountWords = amountReader.findAmountPronounciation(9999999);
        System.out.println(amountWords);
        textToSpeech(amountWords);
        amountWords = amountReader.findAmountPronounciation(2005);
        System.out.println(amountWords);
        textToSpeech(amountWords);
        amountWords = amountReader.findAmountPronounciation(105);
        System.out.println(amountWords);
        textToSpeech(amountWords);
    }

    private static void textToSpeech(String text) {
        Voice voice;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice("kevin");
        voice.allocate();
        text += " rupees received";
        voice.speak(text);
    }
}
