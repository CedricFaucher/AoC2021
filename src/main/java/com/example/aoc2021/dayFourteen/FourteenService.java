package com.example.aoc2021.dayFourteen;

import com.example.aoc2021.utils.GetListFromFileUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FourteenService {
    private final List<String> insertions;
    public final String polymerTemplate = "CNBPHFBOPCSPKOFNHVKV";

    public FourteenService() {
        insertions = GetListFromFileUtils.getListOfString("/dayFourteen/FourteenList");
    }

    public int firstPart(int steps) {
        List<String[]> insertionsSplitted = insertions.stream().map(s -> s.split(" -> ")).collect(Collectors.toList());
        String currentPolymerTemplate = "CNBPHFBOPCSPKOFNHVKV";

        for (int i = 0; i < steps; i++) {
            String newPolymerTemplate = "";
            for (int j = 0; j < currentPolymerTemplate.length() - 1; j++) {
                String evaluatedPair = currentPolymerTemplate.substring(j, j + 2);
                for (String[] insertion : insertionsSplitted) {
                    if (insertion[0].equals(evaluatedPair)) {
                        evaluatedPair = evaluatedPair.charAt(0) + insertion[1] + evaluatedPair.charAt(1);
                    }
                }
                newPolymerTemplate = newPolymerTemplate.concat(evaluatedPair.substring(0, evaluatedPair.length() - 1));
            }
            currentPolymerTemplate = newPolymerTemplate.concat(String.valueOf(currentPolymerTemplate.charAt(currentPolymerTemplate.length() - 1)));
        }

        HashMap<Character, Integer> numberOfCharacter = new HashMap<>();
        for (int i = 0; i < currentPolymerTemplate.length(); i++) {
            if (numberOfCharacter.containsKey(currentPolymerTemplate.charAt(i))) {
                numberOfCharacter.put(currentPolymerTemplate.charAt(i), numberOfCharacter.get(currentPolymerTemplate.charAt(i)) + 1);
            } else {
                numberOfCharacter.put(currentPolymerTemplate.charAt(i), 1);
            }
        }

        int minValue = 0;
        int maxValue = 0;
        for (int value : numberOfCharacter.values()) {
            if (value < minValue || minValue == 0) {
                minValue = value;
            }
            if (value > maxValue || maxValue == 0) {
                maxValue = value;
            }
        }

        return maxValue - minValue;
    }

    public long secondPart(int steps) {
        List<String[]> insertionsSplitted = insertions.stream().map(s -> s.split(" -> ")).collect(Collectors.toList());
        HashMap<String, Long> numberOfPairs = new HashMap<>();
        HashMap<Character, Long> numberOfCharacter = new HashMap<>();

        for (int i = 0; i < polymerTemplate.length() - 1; i++) {
            if (numberOfCharacter.containsKey(polymerTemplate.charAt(i))) {
                numberOfCharacter.put(polymerTemplate.charAt(i), numberOfCharacter.get(polymerTemplate.charAt(i)) + 1);
            } else {
                numberOfCharacter.put(polymerTemplate.charAt(i), 1L);
            }
            String pair = polymerTemplate.substring(i, i + 2);
            if (numberOfPairs.containsKey(pair)) {
                numberOfPairs.put(pair, numberOfPairs.get(pair) + 1);
            } else {
                numberOfPairs.put(pair, 1L);
            }
        }
        if (numberOfCharacter.containsKey(polymerTemplate.charAt(polymerTemplate.length() - 1))) {
            numberOfCharacter.put(polymerTemplate.charAt(polymerTemplate.length() - 1), numberOfCharacter.get(polymerTemplate.charAt(polymerTemplate.length() - 1)) + 1);
        } else {
            numberOfCharacter.put(polymerTemplate.charAt(polymerTemplate.length() - 1), 1L);
        }

        for (int i = 0; i < steps; i++) {
            HashMap<String, Long> updatedNumberOfPairs = new HashMap<>();
            for (String pair : numberOfPairs.keySet()) {
                putTwoPairsFromOnePairInMap(pair, numberOfPairs.get(pair), insertionsSplitted, updatedNumberOfPairs);
                for (String[] split : insertionsSplitted) {
                    if (split[0].equals(pair)) {
                        if (numberOfCharacter.containsKey(split[1].charAt(0))) {
                            numberOfCharacter.put(split[1].charAt(0), numberOfCharacter.get(split[1].charAt(0)) + numberOfPairs.get(pair));
                        } else {
                            numberOfCharacter.put(split[1].charAt(0), numberOfPairs.get(pair));
                        }
                    }
                }
            }
            numberOfPairs = updatedNumberOfPairs;
        }

//        for (String pair : numberOfPairs.keySet()) {
//            if (numberOfCharacter.containsKey(pair.charAt(0))) {
//                numberOfCharacter.put(pair.charAt(0), numberOfCharacter.get(pair.charAt(0)) + numberOfPairs.get(pair));
//            } else {
//                numberOfCharacter.put(pair.charAt(0), numberOfPairs.get(pair));
//            }
//            if (numberOfCharacter.containsKey(pair.charAt(1))) {
//                numberOfCharacter.put(pair.charAt(1), numberOfCharacter.get(pair.charAt(1)) + numberOfPairs.get(pair));
//            } else {
//                numberOfCharacter.put(pair.charAt(1), numberOfPairs.get(pair));
//            }
//        }

        long minValue = 0;
        long maxValue = 0;
        for (long value : numberOfCharacter.values()) {
            if (value < minValue || minValue == 0) {
                minValue = value;
            }
            if (value > maxValue || maxValue == 0) {
                maxValue = value;
            }
        }

        return maxValue - minValue;
    }

    private void putTwoPairsFromOnePairInMap(String pair, long number, List<String[]> insertionsSplitted, HashMap<String, Long> numberOfPairs) {
        for (String[] insertion : insertionsSplitted) {
            if (insertion[0].equals(pair)) {
                String firstPair = pair.charAt(0) + insertion[1];
                String secondPair = insertion[1] + pair.charAt(1);

                if (numberOfPairs.containsKey(firstPair)) {
                    numberOfPairs.put(firstPair, numberOfPairs.get(firstPair) + number);
                } else {
                    numberOfPairs.put(firstPair, number);
                }

                if (numberOfPairs.containsKey(secondPair)) {
                    numberOfPairs.put(secondPair, numberOfPairs.get(secondPair) + number);
                } else {
                    numberOfPairs.put(secondPair, number);
                }

                break;
            }
        }
    }
}
