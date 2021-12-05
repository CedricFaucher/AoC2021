package com.example.aoc2021.dayFour;

import com.example.aoc2021.utils.GetListFromFileUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FourService {
    private final List<String> listOfBingos;
    private final List<Integer> pickedNumbers;

    public FourService() {
        listOfBingos = GetListFromFileUtils.getListOfString("/dayFour/FourList");
        pickedNumbers = List.of(31,50,68,16,25,15,28,80,41,8,75,45,96,9,3,98,83,27,62,42,59,99,95,13,55,10,23,84,18,76,87,56,88,66,1,58,92,89,19,54,85,74,39,93,77,26,30,52,69,48,91,73,72,38,64,53,32,51,6,29,17,90,34,61,70,4,7,57,44,97,82,37,43,14,81,65,11,22,5,36,71,35,78,12,0,94,47,49,33,79,63,86,40,21,24,46,20,2,67,60);
    }

    public int firstPart() {
        List<List<String[]>> listOfBingoCards = getListOfBingoCards();
        List<List<List<Boolean>>> listOfMarkedBingoCards = getListOfMarkedBingoCards(listOfBingoCards.size());

        for (Integer pickedNumber : pickedNumbers) {
            String numberAsString = pickedNumber.toString();
            for (int j = 0; j < listOfBingoCards.size(); j++) {
                for (int k = 0; k < listOfBingoCards.get(j).size(); k++) {
                    for (int l = 0; l < listOfBingoCards.get(j).get(k).length; l++) {
                        if (listOfBingoCards.get(j).get(k)[l].equals(numberAsString)) {
                            listOfMarkedBingoCards.get(j).get(k).set(l, true);
                            if (checkBingo(listOfMarkedBingoCards.get(j), k, l)) {
                                return winningCardProduct(listOfBingoCards.get(j), listOfMarkedBingoCards.get(j), pickedNumber);
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }

    public int secondPart() {
        List<List<String[]>> listOfBingoCards = getListOfBingoCards();
        List<List<List<Boolean>>> listOfMarkedBingoCards = getListOfMarkedBingoCards(listOfBingoCards.size());
        HashMap<Integer, Integer> notWonBingoCardsIndexes = new HashMap<>();

        for (int i = 0; i < listOfBingoCards.size(); i++) {
            notWonBingoCardsIndexes.put(i, i);
        }

        for (Integer pickedNumber : pickedNumbers) {
            String numberAsString = pickedNumber.toString();
            for (int j = 0; j < listOfBingoCards.size(); j++) {
                for (int k = 0; k < listOfBingoCards.get(j).size(); k++) {
                    for (int l = 0; l < listOfBingoCards.get(j).get(k).length; l++) {
                        if (listOfBingoCards.get(j).get(k)[l].equals(numberAsString)) {
                            listOfMarkedBingoCards.get(j).get(k).set(l, true);
                            if (checkBingo(listOfMarkedBingoCards.get(j), k, l)) {
                                notWonBingoCardsIndexes.remove(j);
                                if (notWonBingoCardsIndexes.isEmpty()) {
                                    return winningCardProduct(listOfBingoCards.get(j), listOfMarkedBingoCards.get(j), pickedNumber);
                                }
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }

    private List<List<String[]>> getListOfBingoCards() {
        List<List<String[]>> listOfBingoCards = new ArrayList<>();

        for (int i = 0; i < listOfBingos.size(); i += 6) {
            List<String[]> bingoCard = new ArrayList<>();
            List<String> preTrimmedBingoCard = listOfBingos.subList(i, i + 5);
            preTrimmedBingoCard.forEach(l -> bingoCard.add(l.trim().split("\\s+")));
            listOfBingoCards.add(bingoCard);
        }

        return listOfBingoCards;
    }

    private List<List<List<Boolean>>> getListOfMarkedBingoCards(int numberOfBingoCards) {
        List<List<List<Boolean>>> listOfMarkedBingoCards = new ArrayList<>();

        for (int i = 0; i < numberOfBingoCards; i++) {
            List<List<Boolean>> markedBingoCard = new ArrayList<>();
            markedBingoCard.add(new ArrayList<>(Arrays.asList(false, false, false, false, false)));
            markedBingoCard.add(new ArrayList<>(Arrays.asList(false, false, false, false, false)));
            markedBingoCard.add(new ArrayList<>(Arrays.asList(false, false, false, false, false)));
            markedBingoCard.add(new ArrayList<>(Arrays.asList(false, false, false, false, false)));
            markedBingoCard.add(new ArrayList<>(Arrays.asList(false, false, false, false, false)));
            listOfMarkedBingoCards.add(markedBingoCard);
        }

        return listOfMarkedBingoCards;
    }

    private boolean checkBingo(List<List<Boolean>> markedBingoCard, int linePos, int columnPos) {
        if (markedBingoCard.get(linePos).stream().allMatch(Boolean::valueOf)) {
            return true;
        }
        List<Boolean> column = markedBingoCard.stream().map(line -> line.get(columnPos)).collect(Collectors.toList());
        return column.stream().allMatch(Boolean::valueOf);
    }

    private int winningCardProduct(List<String[]> winningCard, List<List<Boolean>> winningMarkedCard, int pickedNumber) {
        int unmarkedCasesSum = 0;

        for (int i = 0; i < winningCard.size(); i++) {
            for (int j = 0; j < winningCard.get(i).length; j++) {
                if (!winningMarkedCard.get(i).get(j)) {
                    unmarkedCasesSum += Integer.parseInt(winningCard.get(i)[j]);
                }
            }
        }

        return unmarkedCasesSum * pickedNumber;
    }
}
