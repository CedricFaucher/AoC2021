package com.example.aoc2021.dayThirteen;

import com.example.aoc2021.utils.GetListFromFileUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ThirteenService {
    private final List<List<Integer>> coordinates;
    private final List<List<Integer>> commands;

    public ThirteenService() {
        coordinates = GetListFromFileUtils.getListOfIntegerCommaSeparatedAndWithLines("/dayThirteen/ThirteenList");
        commands = GetListFromFileUtils.getListOfCommands("/dayThirteen/ThirteenCommandList");
    }

    public long firstPart() {
        List<List<Integer>> copyCoordinates = new ArrayList<>(coordinates);
        for (List<Integer> command : commands) {
            List<List<Integer>> newCoordinates = new ArrayList<>();
            if (command.get(0) > 0) {
                for (List<Integer> copyCoordinate : copyCoordinates) {
                    int diff = (copyCoordinate.get(0) - command.get(0)) * 2;
                    newCoordinates.add(List.of(copyCoordinate.get(0) - (Math.max(diff, 0)), copyCoordinate.get(1)));
                }
            } else {
                for (List<Integer> copyCoordinate : copyCoordinates) {
                    int diff = (copyCoordinate.get(1) - command.get(1)) * 2;
                    newCoordinates.add(List.of(copyCoordinate.get(0), copyCoordinate.get(1) - (Math.max(diff, 0))));
                }
            }
            return newCoordinates.stream().distinct().count();
        }

        return 0;
    }

    public int secondPart() {
        List<List<Integer>> copyCoordinates = new ArrayList<>(coordinates);
        for (List<Integer> command : commands) {
            List<List<Integer>> newCoordinates = new ArrayList<>();
            if (command.get(0) > 0) {
                for (List<Integer> copyCoordinate : copyCoordinates) {
                    int diff = (copyCoordinate.get(0) - command.get(0)) * 2;
                    newCoordinates.add(List.of(copyCoordinate.get(0) - (Math.max(diff, 0)), copyCoordinate.get(1)));
                }
            } else {
                for (List<Integer> copyCoordinate : copyCoordinates) {
                    int diff = (copyCoordinate.get(1) - command.get(1)) * 2;
                    newCoordinates.add(List.of(copyCoordinate.get(0), copyCoordinate.get(1) - (Math.max(diff, 0))));
                }
            }
            copyCoordinates = new ArrayList<>(newCoordinates);
        }

        int maxX = 0;
        int maxY = 0;
        for (List<Integer> line : copyCoordinates) {
            if (line.get(0) > maxX) maxX = line.get(0);
            if (line.get(1) > maxY) maxY = line.get(1);
        }

        //List<List<Character>> beautifiedCoordinates = new ArrayList<>(Collections.nCopies(maxY + 1, new ArrayList<>(Collections.nCopies(maxX + 1, '.'))));
        List<List<Character>> beautifiedCoordinates = new ArrayList<>();
        for (int i = 0; i <= maxY; i++) {
            beautifiedCoordinates.add(new ArrayList<>(Collections.nCopies(maxX + 1, '.')));
        }

        for (List<Integer> line : copyCoordinates) {
            beautifiedCoordinates.get(line.get(1)).set(line.get(0), '#');
        }

        for (List<Character> beautifiedLine : beautifiedCoordinates) {
            for (Character c : beautifiedLine) {
                System.out.print(c);
            }
            System.out.print('\n');
        }

        return 0;
    }
}
