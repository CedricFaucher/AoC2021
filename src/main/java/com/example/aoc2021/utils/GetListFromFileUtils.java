package com.example.aoc2021.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GetListFromFileUtils {
    public static List<Integer> getListOfIntegers(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            List<Integer> listOfDepths = new ArrayList<>();
            while (scanner.hasNextInt()) {
                listOfDepths.add(scanner.nextInt());
            }
            return listOfDepths;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return Collections.emptyList();
    }
}
