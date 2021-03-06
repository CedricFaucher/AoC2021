package com.example.aoc2021.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class GetListFromFileUtils {
    public static final String BASEPATH = "/Users/cfaucher/Dev/AoC2021/src/main/java/com/example/aoc2021";

    public static List<Integer> getListOfIntegers(String path) {
        try {
            Scanner scanner = new Scanner(new File(BASEPATH + path));
            List<Integer> integerList = new ArrayList<>();
            while (scanner.hasNextInt()) {
                integerList.add(scanner.nextInt());
            }
            return integerList;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return Collections.emptyList();
    }

    public static List<String> getListOfString(String path) {
        try {
            Scanner scanner = new Scanner(new File(BASEPATH + path));
            List<String> stringList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                stringList.add(scanner.nextLine());
            }
            return stringList;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return Collections.emptyList();
    }

    public static List<Integer> getListOfIntegerCommaSeparated(String path) {
        try {
            Scanner scanner = new Scanner(new File(BASEPATH + path));
            String string = "";
            while (scanner.hasNextLine()) {
                string = scanner.nextLine();
            }
            String[] split = string.split(",");
            return Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return Collections.emptyList();
    }

    public static List<List<Integer>> getListOfIntegerCommaSeparatedAndWithLines(String path) {
        try {
            Scanner scanner = new Scanner(new File(BASEPATH + path));
            List<List<Integer>> result = new ArrayList<>();
            List<String> strings = new ArrayList<>();
            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }
            List<String[]> split = strings.stream().map(s -> s.split(",")).collect(Collectors.toList());
            for (String[] s : split) {
                result.add(Arrays.stream(s).map(Integer::parseInt).collect(Collectors.toList()));
            }
            return result;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return Collections.emptyList();
    }

    public static List<List<Integer>> getListOfIntegerInLine(String path) {
        List<List<Integer>> returnedList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(BASEPATH + path));
            while (scanner.hasNextLine()) {
                List<Integer> list = new ArrayList<>();
                String line = scanner.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    list.add(Character.getNumericValue(line.charAt(i)));
                }
                returnedList.add(list);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return returnedList;
    }

    public static List<List<Integer>> getListOfCommands(String path) {
        try {
            Scanner scanner = new Scanner(new File(BASEPATH + path));
            List<List<Integer>> result = new ArrayList<>();
            List<String> strings = new ArrayList<>();
            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }
            for (String s : strings) {
                String[] split = s.split("=");
                if (s.charAt(11) == 'y') {
                    result.add(List.of(0, Integer.parseInt(split[1])));
                } else {
                    result.add(List.of(Integer.parseInt(split[1]), 0));
                }
            }
            return result;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return Collections.emptyList();
    }
}
