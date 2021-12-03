package com.example.aoc2021.dayThree;

import com.example.aoc2021.utils.GetListFromFileUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThreeService {
    private final List<String> listOfBinaries;

    public ThreeService() {
        listOfBinaries = GetListFromFileUtils.getListOfString("/dayThree/ThreeList");
    }

    public int firstPart() {
        List<Integer> numberOfTrueBytesPerPos = new ArrayList<>();
        for (int i = 0; i < listOfBinaries.get(0).length(); i++) {
            numberOfTrueBytesPerPos.add(0);
        }

        for (String listOfBinary : listOfBinaries) {
            for (int j = 0; j < listOfBinary.length(); j++) {
                int value = numberOfTrueBytesPerPos.get(j) + Integer.parseInt(String.valueOf(listOfBinary.charAt(j)));
                numberOfTrueBytesPerPos.set(j, value);
            }
        }

        int gammaRate = getRate(numberOfTrueBytesPerPos, listOfBinaries.size(), 1);
        int epsilonRate = getRate(numberOfTrueBytesPerPos, listOfBinaries.size(), 0);

        return gammaRate * epsilonRate;
    }

    public int secondPart() {
        int oxygenGeneratorRating = getRateByBitCriteria(new ArrayList<>(listOfBinaries), 0,'1');
        int co2ScrubberRating = getRateByBitCriteria(new ArrayList<>(listOfBinaries), 0, '0');

        return oxygenGeneratorRating * co2ScrubberRating;
    }

    private int getRate(List<Integer> numberOfTrueBytesPerPos, int size, int isGamma) {
        int result = 0;

        for (int i = 0; i < numberOfTrueBytesPerPos.size(); i++) {
            int power = numberOfTrueBytesPerPos.size() - (i + 1);
            result += Math.round((float) numberOfTrueBytesPerPos.get(i) / size) == isGamma ? Math.pow(2, power) : 0;
        }

        return result;
    }

    private int getRateByBitCriteria(List<String> updatedList, int pos, char isOxygen) {
        if (updatedList.size() == 1) {
            return getRateFromString(updatedList.get(0));
        }

        int valueOfColumn = 0;

        for (String listOfBinary : updatedList) {
            valueOfColumn += Integer.parseInt(String.valueOf(listOfBinary.charAt(pos)));
        }

        char valueToRemove;

        if (isOxygen == '1') {
            valueToRemove = Math.round((float) valueOfColumn / updatedList.size()) == 1 ? '0' : '1';
        } else {
            valueToRemove = Math.round((float) valueOfColumn / updatedList.size()) == 1 ? '1' : '0';
        }

        List<String> toRemoveList = new ArrayList<>();

        for (String updatedListOfBinary : updatedList) {
            if (updatedListOfBinary.charAt(pos) == valueToRemove) {
                toRemoveList.add(updatedListOfBinary);
            }
        }
        updatedList.removeAll(toRemoveList);

        return getRateByBitCriteria(updatedList, ++pos, isOxygen);
    }

    private int getRateFromString(String binary) {
        int result = 0;

        for (int i = 0; i < binary.length(); i++) {
            int power = binary.length() - (i + 1);
            result += binary.charAt(i) == '1' ? Math.pow(2, power) : 0;
        }

        return result;
    }
}
