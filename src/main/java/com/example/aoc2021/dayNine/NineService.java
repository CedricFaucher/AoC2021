package com.example.aoc2021.dayNine;

import com.example.aoc2021.utils.GetListFromFileUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NineService {
    private final List<List<Integer>> listOfHeight;

    public NineService() {
        listOfHeight = GetListFromFileUtils.getListOfIntegerInLine("/dayNine/NineList");
    }

    public int firstPart() {
        int totalLowPoints = 0;

        for (int i = 0; i < listOfHeight.size(); i++) {
            for (int j = 0; j < listOfHeight.get(i).size(); j++) {
                int evaluatedPoint = listOfHeight.get(i).get(j);
                // If it is the top row
                if (i == 0) {
                    // If it is the most left row
                    if (j == 0) {
                        if (listOfHeight.get(i).get(j + 1) > evaluatedPoint && listOfHeight.get(i + 1).get(j) > evaluatedPoint) totalLowPoints += evaluatedPoint + 1;
                    }
                    // If it is the most right row
                    else if (j == listOfHeight.get(i).size() - 1) {
                        if (listOfHeight.get(i + 1).get(j) > evaluatedPoint && listOfHeight.get(i).get(j - 1) > evaluatedPoint) totalLowPoints += evaluatedPoint + 1;
                    } else {
                        if (listOfHeight.get(i + 1).get(j) > evaluatedPoint && listOfHeight.get(i).get(j - 1) > evaluatedPoint && listOfHeight.get(i).get(j + 1) > evaluatedPoint) totalLowPoints += evaluatedPoint + 1;
                    }
                }
                // If it is the bottom row
                else if (i == listOfHeight.size() - 1) {
                    // If it is the most left row
                    if (j == 0) {
                        if (listOfHeight.get(i).get(j + 1) > evaluatedPoint && listOfHeight.get(i - 1).get(j) > evaluatedPoint) totalLowPoints += evaluatedPoint + 1;
                    }
                    // If it is the most right row
                    else if (j == listOfHeight.get(i).size() - 1) {
                        if (listOfHeight.get(i).get(j - 1) > evaluatedPoint && listOfHeight.get(i - 1).get(j) > evaluatedPoint) totalLowPoints += evaluatedPoint + 1;
                    } else {
                        if (listOfHeight.get(i - 1).get(j) > evaluatedPoint && listOfHeight.get(i).get(j - 1) > evaluatedPoint && listOfHeight.get(i).get(j + 1) > evaluatedPoint) totalLowPoints += evaluatedPoint + 1;
                    }
                } else if (j == 0) {

                        if (listOfHeight.get(i).get(j + 1) > evaluatedPoint && listOfHeight.get(i - 1).get(j) > evaluatedPoint && listOfHeight.get(i + 1).get(j) > evaluatedPoint) totalLowPoints += evaluatedPoint + 1;

                }
                // If it is the bottom row
                else if (j == listOfHeight.get(i).size() - 1) {

                        if (listOfHeight.get(i).get(j - 1) > evaluatedPoint && listOfHeight.get(i - 1).get(j) > evaluatedPoint && listOfHeight.get(i + 1).get(j) > evaluatedPoint) totalLowPoints += evaluatedPoint + 1;

                }
                // Else
                else {
                    if (listOfHeight.get(i).get(j + 1) > evaluatedPoint && listOfHeight.get(i + 1).get(j) > evaluatedPoint && listOfHeight.get(i - 1).get(j) > evaluatedPoint && listOfHeight.get(i).get(j - 1) > evaluatedPoint) totalLowPoints += evaluatedPoint + 1;
                }
            }
        }

        return totalLowPoints;
    }

    public int secondPart() {
        List<Integer> threeValues = new ArrayList<>(List.of(0, 0, 0));

        for (int i = 0; i < listOfHeight.size(); i++) {
            for (int j = 0; j < listOfHeight.get(i).size(); j++) {
                Collections.sort(threeValues);
                int evaluatedPoint = listOfHeight.get(i).get(j);
                HashMap<String, Integer> acc = new HashMap<>();
                // If it is the top row
                if (i == 0) {
                    // If it is the most left row
                    if (j == 0) {
                        if (listOfHeight.get(i).get(j + 1) > evaluatedPoint && listOfHeight.get(i + 1).get(j) > evaluatedPoint) {
                            int basin = getNextNumberInBasin(i, j, acc);
                            if (threeValues.get(0) < basin) {
                                threeValues.set(0, basin);
                            }
                        }
                    }
                    // If it is the most right row
                    else if (j == listOfHeight.get(i).size() - 1) {
                        if (listOfHeight.get(i + 1).get(j) > evaluatedPoint && listOfHeight.get(i).get(j - 1) > evaluatedPoint) {
                            int basin = getNextNumberInBasin(i, j, acc);
                            if (threeValues.get(0) < basin) {
                                threeValues.set(0, basin);
                            }
                        }
                    } else {
                        if (listOfHeight.get(i + 1).get(j) > evaluatedPoint && listOfHeight.get(i).get(j - 1) > evaluatedPoint && listOfHeight.get(i).get(j + 1) > evaluatedPoint) {
                            int basin = getNextNumberInBasin(i, j, acc);
                            if (threeValues.get(0) < basin) {
                                threeValues.set(0, basin);
                            }
                        }
                    }
                }
                // If it is the bottom row
                else if (i == listOfHeight.size() - 1) {
                    // If it is the most left row
                    if (j == 0) {
                        if (listOfHeight.get(i).get(j + 1) > evaluatedPoint && listOfHeight.get(i - 1).get(j) > evaluatedPoint) {
                            int basin = getNextNumberInBasin(i, j, acc);
                            if (threeValues.get(0) < basin) {
                                threeValues.set(0, basin);
                            }
                        }
                    }
                    // If it is the most right row
                    else if (j == listOfHeight.get(i).size() - 1) {
                        if (listOfHeight.get(i).get(j - 1) > evaluatedPoint && listOfHeight.get(i - 1).get(j) > evaluatedPoint) {
                            int basin = getNextNumberInBasin(i, j, acc);
                            if (threeValues.get(0) < basin) {
                                threeValues.set(0, basin);
                            }
                        }
                    } else {
                        if (listOfHeight.get(i - 1).get(j) > evaluatedPoint && listOfHeight.get(i).get(j - 1) > evaluatedPoint && listOfHeight.get(i).get(j + 1) > evaluatedPoint) {
                            int basin = getNextNumberInBasin(i, j, acc);
                            if (threeValues.get(0) < basin) {
                                threeValues.set(0, basin);
                            }
                        }
                    }
                } else if (j == 0) {

                    if (listOfHeight.get(i).get(j + 1) > evaluatedPoint && listOfHeight.get(i - 1).get(j) > evaluatedPoint && listOfHeight.get(i + 1).get(j) > evaluatedPoint) {
                        int basin = getNextNumberInBasin(i, j, acc);
                        if (threeValues.get(0) < basin) {
                            threeValues.set(0, basin);
                        }
                    }

                }
                // If it is the bottom row
                else if (j == listOfHeight.get(i).size() - 1) {

                    if (listOfHeight.get(i).get(j - 1) > evaluatedPoint && listOfHeight.get(i - 1).get(j) > evaluatedPoint && listOfHeight.get(i + 1).get(j) > evaluatedPoint) {
                        int basin = getNextNumberInBasin(i, j, acc);
                        if (threeValues.get(0) < basin) {
                            threeValues.set(0, basin);
                        }
                    }

                }
                // Else
                else {
                    if (listOfHeight.get(i).get(j + 1) > evaluatedPoint && listOfHeight.get(i + 1).get(j) > evaluatedPoint && listOfHeight.get(i - 1).get(j) > evaluatedPoint && listOfHeight.get(i).get(j - 1) > evaluatedPoint) {
                        int basin = getNextNumberInBasin(i, j, acc);
                        if (threeValues.get(0) < basin) {
                            threeValues.set(0, basin);
                        }
                    }
                }
            }
        }

        return threeValues.get(0) * threeValues.get(1) * threeValues.get(2);
    }

    private int getNextNumberInBasin(int posY, int posX, HashMap<String, Integer> acc) {
        acc.put(String.format("%d,%d", posY, posX), posY + posX);
        if (posY != 0 && listOfHeight.get(posY - 1).get(posX) != 9 && listOfHeight.get(posY - 1).get(posX) > listOfHeight.get(posY).get(posX)) {
            getNextNumberInBasin(posY - 1, posX, acc);
        }
        if (posY != listOfHeight.size() - 1 && listOfHeight.get(posY + 1).get(posX) != 9 && listOfHeight.get(posY + 1).get(posX) > listOfHeight.get(posY).get(posX)) {
            getNextNumberInBasin(posY + 1, posX, acc);
        }
        if (posX != 0 && listOfHeight.get(posY).get(posX - 1) != 9 && listOfHeight.get(posY).get(posX - 1) > listOfHeight.get(posY).get(posX)) {
            getNextNumberInBasin(posY, posX - 1, acc);
        }
        if (posX != listOfHeight.get(posY).size() - 1 && listOfHeight.get(posY).get(posX + 1) != 9 && listOfHeight.get(posY).get(posX + 1) > listOfHeight.get(posY).get(posX)) {
            getNextNumberInBasin(posY, posX + 1, acc);
        }
        return acc.size();
    }
}
