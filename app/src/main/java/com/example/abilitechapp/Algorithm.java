package com.example.abilitechapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Algorithm {
    static final String DICT_PATH = "C:\\Users\\ofir\\StudioProjects\\AbiliTechApp\\app\\src\\main\\assets\\cmudict.txt";

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
                //while ((line = bufferedReader.readLine()) != null) {
                String cur_word = line.substring(0, line.indexOf(' '));
                System.out.println(line);
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

    // get phonemes Array of a given word
    private static ArrayList<String> getVowels(String word) {
        try (BufferedReader br = new BufferedReader(new FileReader(DICT_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        ArrayList<String> answer = new ArrayList<>();
        return answer;
    }
}
