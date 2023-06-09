package com.example.abilitechapp;
import java.util.ArrayList;
import java.util.List;

public class ColoringData {
    String[] family = new String[] {
            "mom", "dad", "brother", "sister", "love"
    };
    public static ArrayList<Integer> getSyllablesFamily(String word) {
        ArrayList<Integer> output = new ArrayList<>();
        word = word.toLowerCase();
        switch (word) {
            case "mom":
            case "dad":
            case "love":
            case "more":
                output.addAll(List.of(0,1,2));
                break;
            case "brother":
                output.addAll(List.of(0,1,2,3,5));
                break;
            case "sister":
                output.addAll(List.of(0,1,2,3,4));
                break;
            case "eating":
                output.addAll(List.of(0,2,3,4));
                break;
            case "fork":
            case "water":
                output.addAll(List.of(0,1,2,3));
                break;
            case "food":
                output.addAll(List.of(0,1,3));
                break;
            default:
                // Handle unknown words here
                break;
        }
        return output;
    }
}