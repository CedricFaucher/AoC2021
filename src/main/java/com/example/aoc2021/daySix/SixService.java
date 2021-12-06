package com.example.aoc2021.daySix;

import com.example.aoc2021.utils.GetListFromFileUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SixService {
    private final List<Integer> listOfLanternFishes;

    public SixService() {
        listOfLanternFishes = GetListFromFileUtils.getListOfIntegerCommaSeparated("/daySix/SixList");
    }

    public int firstPart(int numberOfDays) {
        List<Integer> result = new ArrayList<>(listOfLanternFishes);
        for (int i = 0; i < numberOfDays; i++) {
            List<Integer> currentListOfLanternFishes = new ArrayList<>(result);
            for (int j = 0; j < currentListOfLanternFishes.size(); j++) {
                if (result.get(j) > 0) {
                    result.set(j, result.get(j) - 1);
                } else {
                    result.set(j, 6);
                    result.add(8);
                }
            }
        }

        return result.size();
    }

    public long secondPart(int numberOfDays) {
        HashMap<Integer, Long> numberOfFishesPerDayOfGestation = new HashMap<>();

        for (int i = 0; i <= 8; i++) {
            int finalI = i;
            numberOfFishesPerDayOfGestation.put(finalI, listOfLanternFishes.stream().filter(fish -> fish == finalI).count());
        }

        for (int i = 0; i < numberOfDays; i++) {
            HashMap<Integer, Long> currentNumberOfFishesPerDayOfGestation = new HashMap<>(numberOfFishesPerDayOfGestation);
            for (int j = 1; j <= 8; j++) {
                numberOfFishesPerDayOfGestation.put(j - 1, currentNumberOfFishesPerDayOfGestation.get(j));
            }
            numberOfFishesPerDayOfGestation.put(8, currentNumberOfFishesPerDayOfGestation.get(0));
            numberOfFishesPerDayOfGestation.put(6, currentNumberOfFishesPerDayOfGestation.get(0) + currentNumberOfFishesPerDayOfGestation.get(7));
        }

        AtomicLong numberOfFishes = new AtomicLong();
        numberOfFishesPerDayOfGestation.forEach((day, fish) -> numberOfFishes.addAndGet(fish));

        return numberOfFishes.get();
    }
}
