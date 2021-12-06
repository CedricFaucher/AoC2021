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
}
