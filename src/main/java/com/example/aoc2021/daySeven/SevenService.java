package com.example.aoc2021.daySeven;

import com.example.aoc2021.utils.GetListFromFileUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SevenService {
    private final List<Integer> listOfCrabSubmarinesPosition;

    public SevenService() {
        listOfCrabSubmarinesPosition = GetListFromFileUtils.getListOfIntegerCommaSeparated("/daySeven/SevenList");
    }

    public int firstPart() {
        List<Integer> orderedCrabList = listOfCrabSubmarinesPosition.stream().sorted().collect(Collectors.toList());
        int positionToMoveTo;
        int fuelToUse = 0;

        if (orderedCrabList.size() % 2 != 0) {
            positionToMoveTo = orderedCrabList.get(orderedCrabList.size() / 2);
        } else {
            int left = orderedCrabList.get((orderedCrabList.size() / 2) - 1);
            int right = orderedCrabList.get(orderedCrabList.size() / 2);
            positionToMoveTo = (left + right) / 2;
        }

        for (int crab : orderedCrabList) {
            fuelToUse += Math.abs(crab - positionToMoveTo);
        }

        return fuelToUse;
    }

    public long secondPart() {
        List<Integer> orderedCrabList = listOfCrabSubmarinesPosition.stream().sorted().collect(Collectors.toList());
        int positionToMoveToHigh;
        int positionToMoveToLow;
        int fuelToUseHigh = 0;
        int fuelToUseLow = 0;

        positionToMoveToHigh = (int) Math.ceil((float) (orderedCrabList.stream().mapToInt(Integer::valueOf).sum()) / (float) orderedCrabList.size());
        positionToMoveToLow = (int) Math.floor((float) (orderedCrabList.stream().mapToInt(Integer::valueOf).sum()) / (float) orderedCrabList.size());

        for (int crab : orderedCrabList) {
            int n = Math.abs(crab - positionToMoveToHigh);
            fuelToUseHigh += (Math.pow(n, 2) + n) / 2;

            int m = Math.abs(crab - positionToMoveToLow);
            fuelToUseLow += (Math.pow(m, 2) + m) / 2;
        }

        return Math.min(fuelToUseHigh, fuelToUseLow);
    }
}
