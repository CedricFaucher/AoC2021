package com.example.aoc2021.dayEleven;

import com.example.aoc2021.utils.GetListFromFileUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class ElevenService {
    private final List<List<Integer>> octopuses;

    public ElevenService() {
        octopuses = GetListFromFileUtils.getListOfIntegerInLine("/dayEleven/ElevenList");
    }

    public int firstPart() {
        int totalFlashes = 0;
        HashMap<String, Integer> listOfFlashedOctopuses;

        for (int i = 0; i < 100; i++) {
            listOfFlashedOctopuses = new HashMap<>();
            for (int j = 0; j < octopuses.size(); j++) {
                for (int k = 0; k < octopuses.get(j).size(); k++) {
                    octopuses.get(j).set(k, octopuses.get(j).get(k) + 1);
                    if (octopuses.get(j).get(k) == 10) {
                        listOfFlashedOctopuses.put(String.format("%d,%d", j, k), octopuses.get(j).get(k));
                        increaseOctopusesAround(j, k, listOfFlashedOctopuses);
                    }
                }
            }
            totalFlashes += listOfFlashedOctopuses.size();
            for (String key : listOfFlashedOctopuses.keySet()) {
                octopuses.get(Character.getNumericValue(key.charAt(0))).set(Character.getNumericValue(key.charAt(2)), 0);
            }
        }

        return totalFlashes;
    }

    public int secondPart() {
        HashMap<String, Integer> listOfFlashedOctopuses = new HashMap<>();
        int stepOut = 0;

        while (listOfFlashedOctopuses.size() != octopuses.size() * octopuses.get(0).size()) {
            stepOut++;
            listOfFlashedOctopuses = new HashMap<>();
            for (int j = 0; j < octopuses.size(); j++) {
                for (int k = 0; k < octopuses.get(j).size(); k++) {
                    octopuses.get(j).set(k, octopuses.get(j).get(k) + 1);
                    if (octopuses.get(j).get(k) == 10) {
                        listOfFlashedOctopuses.put(String.format("%d,%d", j, k), octopuses.get(j).get(k));
                        increaseOctopusesAround(j, k, listOfFlashedOctopuses);
                    }
                }
            }
            for (String key : listOfFlashedOctopuses.keySet()) {
                octopuses.get(Character.getNumericValue(key.charAt(0))).set(Character.getNumericValue(key.charAt(2)), 0);
            }
        }

        return stepOut;
    }

    private void increaseOctopusesAround(int j, int k, HashMap<String, Integer> listOfFlashedOctopuses) {
        if (j != 0) {
            if (k != 0) {
                octopuses.get(j - 1).set(k - 1, octopuses.get(j - 1).get(k - 1) + 1);
                if (octopuses.get(j - 1).get(k - 1) == 10) {
                    listOfFlashedOctopuses.put(String.format("%d,%d", j - 1, k - 1), octopuses.get(j - 1).get(k - 1));
                    increaseOctopusesAround(j - 1, k - 1, listOfFlashedOctopuses);
                }
            }
            if (k != octopuses.get(j).size() - 1) {
                octopuses.get(j - 1).set(k + 1, octopuses.get(j - 1).get(k + 1) + 1);
                if (octopuses.get(j - 1).get(k + 1) == 10) {
                    listOfFlashedOctopuses.put(String.format("%d,%d", j - 1, k + 1), octopuses.get(j - 1).get(k + 1));
                    increaseOctopusesAround(j - 1, k + 1, listOfFlashedOctopuses);
                }
            }
            octopuses.get(j - 1).set(k, octopuses.get(j - 1).get(k) + 1);
            if (octopuses.get(j - 1).get(k) == 10) {
                listOfFlashedOctopuses.put(String.format("%d,%d", j - 1, k), octopuses.get(j - 1).get(k));
                increaseOctopusesAround(j - 1, k, listOfFlashedOctopuses);
            }
        }
        if (j != octopuses.size() - 1) {
            if (k != 0) {
                octopuses.get(j + 1).set(k - 1, octopuses.get(j + 1).get(k - 1) + 1);
                if (octopuses.get(j + 1).get(k - 1) == 10) {
                    listOfFlashedOctopuses.put(String.format("%d,%d", j + 1, k - 1), octopuses.get(j + 1).get(k - 1));
                    increaseOctopusesAround(j + 1, k - 1, listOfFlashedOctopuses);
                }
            }
            if (k != octopuses.get(j).size() - 1) {
                octopuses.get(j + 1).set(k + 1, octopuses.get(j + 1).get(k + 1) + 1);
                if (octopuses.get(j + 1).get(k + 1) == 10) {
                    listOfFlashedOctopuses.put(String.format("%d,%d", j + 1, k + 1), octopuses.get(j + 1).get(k + 1));
                    increaseOctopusesAround(j + 1, k + 1, listOfFlashedOctopuses);
                }
            }
            octopuses.get(j + 1).set(k, octopuses.get(j + 1).get(k) + 1);
            if (octopuses.get(j + 1).get(k) == 10) {
                listOfFlashedOctopuses.put(String.format("%d,%d", j + 1, k), octopuses.get(j + 1).get(k));
                increaseOctopusesAround(j + 1, k, listOfFlashedOctopuses);
            }
        }
        if (k != 0) {
            octopuses.get(j).set(k - 1, octopuses.get(j).get(k - 1) + 1);
            if (octopuses.get(j).get(k - 1) == 10) {
                listOfFlashedOctopuses.put(String.format("%d,%d", j, k - 1), octopuses.get(j).get(k - 1));
                increaseOctopusesAround(j, k - 1, listOfFlashedOctopuses);
            }
        }
        if (k != octopuses.get(j).size() - 1) {
            octopuses.get(j).set(k + 1, octopuses.get(j).get(k + 1) + 1);
            if (octopuses.get(j).get(k + 1) == 10) {
                listOfFlashedOctopuses.put(String.format("%d,%d", j, k + 1), octopuses.get(j).get(k + 1));
                increaseOctopusesAround(j, k + 1, listOfFlashedOctopuses);
            }
        }
    }
}
