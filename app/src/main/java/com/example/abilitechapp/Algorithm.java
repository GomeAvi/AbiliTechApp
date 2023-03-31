package com.example.abilitechapp;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class Algorithm {
    public static final int RED = 0;
    public static final int YELLOW = 1;
    public static final int GREEN = 2;
    public static final int SIZE_OF_PON = 2;
    public static ArrayList<String> BinSearch(Context context, String assetFileName,String word) throws IOException {
        word = word.toLowerCase();
        InputStream inputStream = context.getAssets().open(assetFileName);
        long fileSize = inputStream.available();

        // Perform binary search for a line
        long left = 0;
        long right = fileSize - 1;
        long middle = 0;
        while (left <= right) {
            middle = (left + right) / 2;
            inputStream.reset();
            inputStream.skip(middle); // Move the input stream pointer to the middle position
            readPartialLine(inputStream); // Read partial line to move input stream pointer to start of line
            String currentLine = readLine(inputStream); // Read the full line
            String cur_word = currentLine.substring(0, currentLine.indexOf(' '));
            if (cur_word.equals(word)) {
                // Line found
                System.out.println("Found line " + word + " at position " + middle);
                inputStream.close();
                return new ArrayList<String>(Arrays.asList(currentLine.substring(currentLine.indexOf(' ')).trim().split(" ")));
            } else if (cur_word.compareTo(word) < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        // Line not found
        System.out.println("Line " + word + " not found in file");
        inputStream.close();
        return new ArrayList<>();
    }

    // Read a partial line from the input stream to move the pointer to the start of the current line
    private static void readPartialLine(InputStream inputStream) throws IOException {
        while (inputStream.read() != '\n') ;
    }

    // Read a full line from the input stream
    private static String readLine(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int c;
        while ((c = inputStream.read()) != -1 && c != '\n') {
            byteArrayOutputStream.write(c);
        }
        return byteArrayOutputStream.toString();
    }

    public static ArrayList<Integer> getAccuracy(ArrayList<String> word, ArrayList<String> userTry){
        ArrayList<Integer> accuracy = new ArrayList<>(Collections.nCopies(word.size(), 0));
        ArrayList<Integer> dummyAccuracy = new ArrayList<>(Collections.nCopies(word.size(), 0));

        for (int i=0; i<word.size(); i++) {
            int startInd = i;
            dummyAccuracy = new ArrayList<>(Collections.nCopies(word.size(), 0));
            for(int j=i; j<word.size(); j++) {
                for (int k = startInd; k < userTry.size(); k++) {
                    if (userTry.get(k).equals(word.get(j))){
                        startInd = k;
                        dummyAccuracy.set(j, GREEN);
                        break;
                    }
                    else if (userTry.get(k).length() >= SIZE_OF_PON && word.get(j).length() >= SIZE_OF_PON &&
                            userTry.get(k).substring(0,SIZE_OF_PON).equals(word.get(j).substring(0,SIZE_OF_PON))) {
                        startInd = k;
                        dummyAccuracy.set(j, YELLOW);
                        break;
                    }
                }
            }
            if (getSum(dummyAccuracy) > getSum(accuracy))
                accuracy = dummyAccuracy;
        }
        return accuracy;
    }

    private static int getSum(ArrayList<Integer> arr){
        int sum = 0;
        for(int i=0; i<arr.size(); i++) {
            sum += arr.get(i);
        }
        return sum;
    }
}

