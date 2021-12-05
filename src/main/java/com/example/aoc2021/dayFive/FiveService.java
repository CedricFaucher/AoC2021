package com.example.aoc2021.dayFive;

import com.example.aoc2021.utils.GetListFromFileUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FiveService {
    private final List<String> listOfLines;

    public FiveService() {
        listOfLines = GetListFromFileUtils.getListOfString("/dayFive/FiveList");
    }

    public int firstPart() {
        List<String[]> listOfFormattedLines = new ArrayList<>();
        int maxX = 0;
        int maxY = 0;
        for (String line : listOfLines) {
            String coordinates = line.replace(" -> ", ",");
            String[] split = coordinates.split(",");

            int x1 = Integer.parseInt(split[0]) + 1;
            int y1 = Integer.parseInt(split[1]) + 1;
            int x2 = Integer.parseInt(split[2]) + 1;
            int y2 = Integer.parseInt(split[3]) + 1;

            if (maxX < x1) {
                maxX = x1;
            }
            if (maxX < x2) {
                maxX = x2;
            }
            if (maxY < y1) {
                maxY = y1;
            }
            if (maxY < y2) {
                maxY = y2;
            }
        }

        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < maxY; i++) {
            List<Integer> list = new ArrayList<>(Collections.nCopies(maxX, 0));
            resultList.add(list);
        }

        for (String line : listOfLines) {
            String coordinates = line.replace(" -> ", ",");
            String[] split = coordinates.split(",");

            int x1 = Integer.parseInt(split[0]);
            int y1 = Integer.parseInt(split[1]);
            int x2 = Integer.parseInt(split[2]);
            int y2 = Integer.parseInt(split[3]);

            if (x1 == x2) {
                if (y1 > y2) {
                    for (int i = y2; i <= y1; i++) {
                        int updatedValue = resultList.get(x1).get(i) + 1;
                        resultList.get(x1).set(i, updatedValue);
                    }
                } else {
                    for (int i = y1; i <= y2; i++) {
                        int updatedValue = resultList.get(x1).get(i) + 1;
                        resultList.get(x1).set(i, updatedValue);
                    }
                }
            } else if (y1 == y2) {
                if (x1 > x2) {
                    for (int i = x2; i <= x1; i++) {
                        int updatedValue = resultList.get(i).get(y1) + 1;
                        resultList.get(i).set(y1, updatedValue);
                    }
                } else {
                    for (int i = x1; i <= x2; i++) {
                        int updatedValue = resultList.get(i).get(y1) + 1;
                        resultList.get(i).set(y1, updatedValue);
                    }
                }
            }
            listOfFormattedLines.add(split);
        }

        int result = 0;
        for (List<Integer> line : resultList) {
            for (Integer res : line) {
                if (res > 1) {
                    result++;
                }
            }
        }

        return result;
    }

    public int secondPart() {
        List<String[]> listOfFormattedLines = new ArrayList<>();
        int maxX = 0;
        int maxY = 0;
        for (String line : listOfLines) {
            String coordinates = line.replace(" -> ", ",");
            String[] split = coordinates.split(",");

            int x1 = Integer.parseInt(split[0]) + 1;
            int y1 = Integer.parseInt(split[1]) + 1;
            int x2 = Integer.parseInt(split[2]) + 1;
            int y2 = Integer.parseInt(split[3]) + 1;

            if (maxX < x1) {
                maxX = x1;
            }
            if (maxX < x2) {
                maxX = x2;
            }
            if (maxY < y1) {
                maxY = y1;
            }
            if (maxY < y2) {
                maxY = y2;
            }
        }

        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < maxY; i++) {
            List<Integer> list = new ArrayList<>(Collections.nCopies(maxX, 0));
            resultList.add(list);
        }

        for (String line : listOfLines) {
            String coordinates = line.replace(" -> ", ",");
            String[] split = coordinates.split(",");

            int x1 = Integer.parseInt(split[0]);
            int y1 = Integer.parseInt(split[1]);
            int x2 = Integer.parseInt(split[2]);
            int y2 = Integer.parseInt(split[3]);

            if (x1 == x2) {
                if (y1 > y2) {
                    for (int i = y2; i <= y1; i++) {
                        int updatedValue = resultList.get(x1).get(i) + 1;
                        resultList.get(x1).set(i, updatedValue);
                    }
                } else {
                    for (int i = y1; i <= y2; i++) {
                        int updatedValue = resultList.get(x1).get(i) + 1;
                        resultList.get(x1).set(i, updatedValue);
                    }
                }
            } else if (y1 == y2) {
                if (x1 > x2) {
                    for (int i = x2; i <= x1; i++) {
                        int updatedValue = resultList.get(i).get(y1) + 1;
                        resultList.get(i).set(y1, updatedValue);
                    }
                } else {
                    for (int i = x1; i <= x2; i++) {
                        int updatedValue = resultList.get(i).get(y1) + 1;
                        resultList.get(i).set(y1, updatedValue);
                    }
                }
            } else if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
                if (x1 > x2) {
                    if (y1 > y2) {
                        int diffBetweenXAndY = y1 - x1;
                        for (int i = x2; i <= x1; i++) {
                            int updatedValue = resultList.get(i).get(i + diffBetweenXAndY) + 1;
                            resultList.get(i).set(i + diffBetweenXAndY, updatedValue);
                        }
                    } else {
                        int diffBetweenXAndY = y2 - x2;
                        for (int i = x2; i <= x1; i++) {
                            int updatedValue = resultList.get(i).get(x2 - (i - x2) + diffBetweenXAndY) + 1;
                            resultList.get(i).set(x2 - (i - x2) + diffBetweenXAndY, updatedValue);
                        }
                    }
                } else {
                    if (y1 > y2) {
                        int diffBetweenXAndY = y1 - x1;
                        for (int i = x1; i <= x2; i++) {
                            int updatedValue = resultList.get(i).get(x1 - (i - x1) + diffBetweenXAndY) + 1;
                            resultList.get(i).set(x1 - (i - x1) + diffBetweenXAndY, updatedValue);
                        }
                    } else {
                        int diffBetweenXAndY = y1 - x1;
                        for (int i = x1; i <= x2; i++) {
                            int updatedValue = resultList.get(i).get(i + diffBetweenXAndY) + 1;
                            resultList.get(i).set(i + diffBetweenXAndY, updatedValue);
                        }
                    }
                }
            }
            listOfFormattedLines.add(split);
        }

        int result = 0;
        for (List<Integer> line : resultList) {
            for (Integer res : line) {
                if (res > 1) {
                    result++;
                }
            }
        }

        return result;
    }
}
