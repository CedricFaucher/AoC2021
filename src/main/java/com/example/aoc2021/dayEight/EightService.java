package com.example.aoc2021.dayEight;

import com.example.aoc2021.utils.GetListFromFileUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EightService {
    private final List<String> listOfDigits;

    public EightService() {
        listOfDigits = GetListFromFileUtils.getListOfString("/dayEight/EightList");
    }

    public int firstPart() {
        List<String[]> splitOnPipe = listOfDigits.stream().map(line -> line.split("\s\\|\s")).collect(Collectors.toList());
        int numberOfUniqueDigits = 0;

        for (String[] line : splitOnPipe) {
            String[] digits = line[1].split(" ");
            for (String digit : digits) {
                if (digit.length() == 2 || digit.length() == 3 || digit.length() == 4 || digit.length() == 7) {
                    numberOfUniqueDigits++;
                }
            }
        }

        return numberOfUniqueDigits;
    }

    public int secondPart() {
        List<String[]> splitOnPipe = listOfDigits.stream().map(line -> line.split("\s\\|\s")).collect(Collectors.toList());
        int valueOfOutputs = 0;

        for (String[] line : splitOnPipe) {
            String[] digits = line[0].split(" ");
            char a, b, c, d, e, f, g;
            String one, four, seven, eight;
            one = four = seven = eight = "";
            List<String> fiveLengthDigits = new ArrayList<>();
            List<String> sixLengthDigits = new ArrayList<>();
            List<Integer> fourDigits = new ArrayList<>();


            for (String digit : digits) {
                switch (digit.length()) {
                    case 2 -> one = digit;
                    case 3 -> seven = digit;
                    case 4 -> four = digit;
                    case 5 -> fiveLengthDigits.add(digit);
                    case 6 -> sixLengthDigits.add(digit);
                    case 7 -> eight = digit;
                }
            }

            a = findAFromSevenAndOne(one, seven);
            c = findCFromOneAndListOfSixLengthDigits(one, sixLengthDigits);
            f = findFFromOneAndC(one, c);
            b = findBFromFourAndListOfFiveLengthDigits(four, fiveLengthDigits, c, f);
            d = findDFromFourAndB(four, b, c, f);
            g = findGFromPreviousCharactersAndFiveLengthDigits(fiveLengthDigits, a, b, c, d, f);
            e = findEFromEightAndPreviousCharacters(eight, a, b, c, d, f, g);

            String[] digitsToDecode = line[1].split(" ");
            for (String digitToDecode : digitsToDecode) {
                switch (digitToDecode.length()) {
                    case 2 -> fourDigits.add(1);
                    case 3 -> fourDigits.add(7);
                    case 4 -> fourDigits.add(4);
                    case 5 -> fourDigits.add(decodeFiveDigits(digitToDecode, b, e));
                    case 6 -> fourDigits.add(decodeSixDigits(digitToDecode, c, d, e));
                    case 7 -> fourDigits.add(8);
                }
            }

            valueOfOutputs += ((fourDigits.get(0) * 1000) + (fourDigits.get(1) * 100) + (fourDigits.get(2) * 10) + fourDigits.get(3));
        }

        return valueOfOutputs;
    }

    private char findAFromSevenAndOne(String one, String seven) {
        for (int i = 0; i < one.length(); i++) {
            seven = seven.replace(String.valueOf(one.charAt(i)), "");
        }

        return seven.charAt(0);
    }

    private char findCFromOneAndListOfSixLengthDigits(String one, List<String> sixLengthDigits) {
        for (String sixLengthDigit : sixLengthDigits) {
            String replaced = sixLengthDigit.replace(String.valueOf(one.charAt(0)), "");
            if (replaced.length() == 6) {
                return one.charAt(0);
            }
        }
        return one.charAt(1);
    }

    private char findFFromOneAndC(String one, char c) {
        String replaced = one.replace(String.valueOf(c), "");
        return replaced.charAt(0);
    }

    private char findBFromFourAndListOfFiveLengthDigits(String four, List<String> fiveLengthDigits, char c, char f) {
        String bAndDFromFour = four.replace(String.valueOf(c), "").replace(String.valueOf(f), "");

        for (String fiveLengthDigit : fiveLengthDigits) {
            String replaced = fiveLengthDigit.replace(String.valueOf(bAndDFromFour.charAt(0)), "");
            if (replaced.length() == 5) {
                return bAndDFromFour.charAt(0);
            }
        }
        return bAndDFromFour.charAt(1);
    }

    private char findDFromFourAndB(String four, char b, char c, char f) {
        String replaced = four.replace(String.valueOf(c), "")
                .replace(String.valueOf(f), "")
                .replace(String.valueOf(b), "");
        return replaced.charAt(0);
    }

    private char findGFromPreviousCharactersAndFiveLengthDigits(List<String> fiveLengthDigits, char a, char b, char c, char d, char f) {
        for (String fiveLengthDigit : fiveLengthDigits) {
            String replaced = fiveLengthDigit.replace(String.valueOf(a), "")
                    .replace(String.valueOf(b), "")
                    .replace(String.valueOf(c), "")
                    .replace(String.valueOf(d), "")
                    .replace(String.valueOf(f), "");
            if (replaced.length() == 1) {
                return replaced.charAt(0);
            }
        }
        return 0;
    }

    private char findEFromEightAndPreviousCharacters(String eight, char a, char b, char c, char d, char f, char g) {
        String replaced = eight.replace(String.valueOf(a), "")
                .replace(String.valueOf(b), "")
                .replace(String.valueOf(c), "")
                .replace(String.valueOf(d), "")
                .replace(String.valueOf(f), "")
                .replace(String.valueOf(g), "");
        return replaced.charAt(0);
    }

    private int decodeFiveDigits(String digitToDecode, char b, char e) {
        // Two = c, e
        // Three = c, f
        // Five = b, f

        if (digitToDecode.contains(String.valueOf(b))) {
            return 5;
        } else if (digitToDecode.contains(String.valueOf(e))) {
            return 2;
        }
        return 3;
    }

    private int decodeSixDigits(String digitToDecode, char c, char d, char e) {
        // Zero = c, e
        // Six = d, e
        // Nine =  c, d

        if (digitToDecode.contains(String.valueOf(c)) && digitToDecode.contains(String.valueOf(e))) {
            return 0;
        } else if (digitToDecode.contains(String.valueOf(d)) && digitToDecode.contains(String.valueOf(e))) {
            return 6;
        }
        return 9;
    }
}
