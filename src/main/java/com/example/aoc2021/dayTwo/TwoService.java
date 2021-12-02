package com.example.aoc2021.dayTwo;

import com.example.aoc2021.utils.GetListFromFileUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwoService {
    private final List<String> listOfMovements;
    public final String FORWARD = "forward";
    public final String UP = "up";
    public final String DOWN = "down";

    public TwoService() {
        listOfMovements = GetListFromFileUtils.getListOfString("/dayTwo/TwoList");
    }

    public int firstPart() {
        int horizontal = 0;
        int depth = 0;

        // Regular for loop, should be O(n)
        for (String move : listOfMovements) {
            String[] directionAndValue = move.split(" ");
            int value = Integer.parseInt(directionAndValue[1]);
            switch (directionAndValue[0]) {
                case FORWARD -> horizontal += value;
                case UP -> depth -= value;
                case DOWN -> depth += value;
            }
        }

        return horizontal * depth;
    }

    public int secondPart() {
        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        // Regular for loop, should be O(n)
        for (String move : listOfMovements) {
            String[] directionAndValue = move.split(" ");
            int value = Integer.parseInt(directionAndValue[1]);
            switch (directionAndValue[0]) {
                case FORWARD -> {
                    horizontal += value;
                    depth += aim * value;
                }
                case UP -> aim -= value;
                case DOWN -> aim += value;
            }
        }

        return horizontal * depth;
    }
}