package com.example.aoc2021.dayTwelve;

import com.example.aoc2021.utils.GetListFromFileUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TwelveService {
    private final List<String> paths;
    public int sum = 0;

    public TwelveService() {
        paths = GetListFromFileUtils.getListOfString("/dayTwelve/TwelveList");
    }

    public int firstPart() {
        List<String[]> splittedPaths = paths.stream().map(path -> path.split("-")).collect(Collectors.toList());
        HashMap<String, Integer> hash;

        for (String[] split : splittedPaths) {
            hash = new HashMap<>();
            if (Arrays.asList(split).contains("start")) {
                hash.put("start", -1);
                String value = Arrays.stream(split).filter(s -> !s.equals("start")).collect(Collectors.toList()).get(0);
                if (!Character.isUpperCase(value.charAt(0))) {
                    hash.put(value, 1);
                }
                findNextElementInPath(value, splittedPaths, hash, "start->" + value + "->");
            }
        }

        return sum;
    }

    public int secondPart() {
        List<String[]> splittedPaths = paths.stream().map(path -> path.split("-")).collect(Collectors.toList());
        HashMap<String, Integer> hash;

        for (String[] split : splittedPaths) {
            hash = new HashMap<>();
            if (Arrays.asList(split).contains("start")) {
                hash.put("start", -1);
                String value = Arrays.stream(split).filter(s -> !s.equals("start")).collect(Collectors.toList()).get(0);
                if (!Character.isUpperCase(value.charAt(0))) {
                    hash.put(value, 1);
                }
                findNextElementInPathWithOneSmall(value, splittedPaths, hash, "start->" + value + "->");
            }
        }

        return sum;
    }

    private void findNextElementInPath(String start, List<String[]> splittedPaths, HashMap<String, Integer> hash, String completeString) {
        for (String[] split : splittedPaths) {
            HashMap<String, Integer> hashCopy = new HashMap<>(hash);
            if (Arrays.asList(split).contains(start)) {
                String value = Arrays.stream(split).filter(s -> !s.equals(start)).collect(Collectors.toList()).get(0);
                if (!hash.containsKey(value)) {
                    if (value.equals("end")) {
                        System.out.println(completeString + "end");
                        sum++;
                    }
                    if (!value.equals("end") && !value.equals("start")) {
                        if (!Character.isUpperCase(value.charAt(0))) {
                            hashCopy.put(value, 1);
                        }
                        findNextElementInPath(value, splittedPaths, hashCopy, completeString + value + "->");
                    }
                }
            }
        }
    }

    private void findNextElementInPathWithOneSmall(String start, List<String[]> splittedPaths, HashMap<String, Integer> hash, String completeString) {
        for (String[] split : splittedPaths) {
            HashMap<String, Integer> hashCopy = new HashMap<>(hash);
            if (Arrays.asList(split).contains(start)) {
                String value = Arrays.stream(split).filter(s -> !s.equals(start)).collect(Collectors.toList()).get(0);
                if (!hash.containsValue(2)) {
                    if (value.equals("end")) {
                        System.out.println(completeString + "end");
                        sum++;
                    }
                    if (!value.equals("end") && !value.equals("start")) {
                        if (!Character.isUpperCase(value.charAt(0))) {
                            if (hash.containsKey(value)) {
                                hashCopy.put(value, 2);
                            } else {
                                hashCopy.put(value, 1);
                            }
                        }
                        findNextElementInPathWithOneSmall(value, splittedPaths, hashCopy, completeString + value + "->");
                    }
                } else if (!hash.containsKey(value)) {
                    if (value.equals("end")) {
                        System.out.println(completeString + "end");
                        sum++;
                    }
                    if (!value.equals("end") && !value.equals("start")) {
                        if (!Character.isUpperCase(value.charAt(0))) {
                            hashCopy.put(value, 1);
                        }
                        findNextElementInPathWithOneSmall(value, splittedPaths, hashCopy, completeString + value + "->");
                    }
                }
            }
        }
    }
}
