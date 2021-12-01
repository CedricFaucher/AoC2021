package com.example.aoc2021.dayOne;

import com.example.aoc2021.utils.GetListFromFileUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OneService {
    private final List<Integer> listOfDepths;

    public OneService() {
        listOfDepths = GetListFromFileUtils.getListOfIntegers("/Users/cfaucher/Dev/AoC2021/src/main/java/com/example/aoc2021/dayOne/OneList");
    }

    public int firstPart() {
        int numberOfPositiveVariation = 0;

        // Regular for loop, should be O(n)
        for (int i = 0; i < listOfDepths.size() - 1; i++) {
            if (listOfDepths.get(i + 1) > listOfDepths.get(i)) {
                numberOfPositiveVariation++;
            }
        }

        return numberOfPositiveVariation;
    }

    public int secondPart() {
        int numberOfPositiveVariation = 0;

        // Regular for loop, should be O(n)
        for (int i = 0; i < listOfDepths.size() - 3; i++) {
            int firstThreeMeasurementSlidingWindow = listOfDepths.get(i) + listOfDepths.get(i + 1) + listOfDepths.get(i + 2);
            int secondThreeMeasurementSlidingWindow = listOfDepths.get(i + 1) + listOfDepths.get(i + 2) + listOfDepths.get(i + 3);
            if (secondThreeMeasurementSlidingWindow > firstThreeMeasurementSlidingWindow) {
                numberOfPositiveVariation++;
            }
        }

        return numberOfPositiveVariation;
    }
}
