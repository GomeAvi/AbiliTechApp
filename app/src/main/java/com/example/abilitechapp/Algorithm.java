package com.example.abilitechapp;

import java.io.BufferedReader;
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
import java.util.Scanner;

public class Algorithm {
    public static final int RED = 0;
    public static final int YELLOW = 1;
    public static final int GREEN = 2;
    public static final int SIZE_OF_PON = 2;
    public static ArrayList<String> readAssetFile(Context context, String fileName, String word) {
        ArrayList<String> phonemes = new ArrayList<String>();

        AssetManager assetManager = context.getAssets();
        try {

            InputStream inputStream = assetManager.open(fileName);
            Scanner scanner = new Scanner(inputStream);
            String line;
            String[] phonemesArr;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String cur_word = line.substring(0, line.indexOf(' '));
                if (cur_word.equals(word)) {
                    phonemesArr = line.substring(line.indexOf(" ")).split(" ");
                    phonemes = new ArrayList<>(Arrays.asList(phonemesArr));
                    break;
                }
            }
            scanner.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phonemes;
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

