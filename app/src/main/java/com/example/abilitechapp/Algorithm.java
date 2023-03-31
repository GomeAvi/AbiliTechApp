package com.example.abilitechapp;

import java.util.ArrayList;
import java.util.Collections;

public class Algorithm {
    public final int RED = 0;
    public final int YELLOW = 1;
    public final int GREEN = 2;
    public final int SIZE_OF = 2;

    public ArrayList<Integer> getAccuracy(ArrayList<String> word, ArrayList<String> userTry){
        ArrayList<Integer> accuracy = new ArrayList<>(Collections.nCopies(word.size(), 0));
        ArrayList<Integer> dummyAccuracy = new ArrayList<>(Collections.nCopies(word.size(), 0));
        int startInd;

        for (int i=0; i<word.size(); i++) {
            startInd = i;
            for(int j=i; j<word.size(); j++) {
                for (int k = startInd; k < userTry.size(); k++) {
                    if (userTry.get(k).equals(word.get(j))){
                        startInd = k;
                        dummyAccuracy.set(j, GREEN);
                        break;
                    }
                    else if (userTry.get(k).substring(0,SIZE_OF).equals(word.get(j).substring(0,SIZE_OF))) {
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

    private int getSum(ArrayList<Integer> arr){
        int sum = 0;
        for(int num : arr)
            sum += num;
        return sum;
    }
}
