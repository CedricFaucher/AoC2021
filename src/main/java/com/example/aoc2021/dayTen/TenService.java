package com.example.aoc2021.dayTen;

import com.example.aoc2021.utils.GetListFromFileUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class TenService {
    private final List<String> subsystemChunks;
    public final int VALUE_PARENTHESE = 3;
    public final int VALUE_SQUARE_BRACKET = 57;
    public final int VALUE_CURLY_BRACKET = 1197;
    public final int VALUE_ANGLE_BRACKET = 25137;
    public final int VALUE_PARENTHESE_SECOND = 1;
    public final int VALUE_SQUARE_BRACKET_SECOND = 2;
    public final int VALUE_CURLY_BRACKET_SECOND = 3;
    public final int VALUE_ANGLE_BRACKET_SECOND = 4;

    public TenService() {
        subsystemChunks = GetListFromFileUtils.getListOfString("/dayTen/TenList");
    }

    public int firstPart() {
        int totalScore = 0;
        List<Character> lifoStack = new ArrayList<>();

        for (String lineChunk : subsystemChunks) {
            for (int i = 0; i < lineChunk.length(); i++) {
                if (lifoStack.size() == 0) {
                    lifoStack.add(lineChunk.charAt(i));
                } else {
                    switch (lineChunk.charAt(i)) {
                        case ')':
                            if (lifoStack.get(lifoStack.size() - 1).equals('(')) {
                                lifoStack.remove(lifoStack.size() - 1);
                            } else {
                                totalScore += VALUE_PARENTHESE;
                                i = lineChunk.length();
                            }
                            break;
                        case ']':
                            if (lifoStack.get(lifoStack.size() - 1).equals('[')) {
                                lifoStack.remove(lifoStack.size() - 1);
                            } else {
                                totalScore += VALUE_SQUARE_BRACKET;
                                i = lineChunk.length();
                            }
                            break;
                        case '}':
                            if (lifoStack.get(lifoStack.size() - 1).equals('{')) {
                                lifoStack.remove(lifoStack.size() - 1);
                            } else {
                                totalScore += VALUE_CURLY_BRACKET;
                                i = lineChunk.length();
                            }
                            break;
                        case '>':
                            if (lifoStack.get(lifoStack.size() - 1).equals('<')) {
                                lifoStack.remove(lifoStack.size() - 1);
                            } else {
                                totalScore += VALUE_ANGLE_BRACKET;
                                i = lineChunk.length();
                            }
                            break;
                        default:
                            lifoStack.add(lineChunk.charAt(i));
                            break;
                    }
                }
            }
        }

        return totalScore;
    }

    public double secondPart() {
        List<Double> totalScore = new ArrayList<>();
        List<Character> lifoStack = new ArrayList<>();
        List<String> incompleteLineChunks = new ArrayList<>(subsystemChunks);

        for (String lineChunk : subsystemChunks) {
            for (int i = 0; i < lineChunk.length(); i++) {
                if (lifoStack.size() == 0) {
                    lifoStack.add(lineChunk.charAt(i));
                } else {
                    switch (lineChunk.charAt(i)) {
                        case ')':
                            if (lifoStack.get(lifoStack.size() - 1).equals('(')) {
                                lifoStack.remove(lifoStack.size() - 1);
                            } else {
                                incompleteLineChunks.remove(lineChunk);
                                i = lineChunk.length();
                            }
                            break;
                        case ']':
                            if (lifoStack.get(lifoStack.size() - 1).equals('[')) {
                                lifoStack.remove(lifoStack.size() - 1);
                            } else {
                                incompleteLineChunks.remove(lineChunk);
                                i = lineChunk.length();
                            }
                            break;
                        case '}':
                            if (lifoStack.get(lifoStack.size() - 1).equals('{')) {
                                lifoStack.remove(lifoStack.size() - 1);
                            } else {
                                incompleteLineChunks.remove(lineChunk);
                                i = lineChunk.length();
                            }
                            break;
                        case '>':
                            if (lifoStack.get(lifoStack.size() - 1).equals('<')) {
                                lifoStack.remove(lifoStack.size() - 1);
                            } else {
                                incompleteLineChunks.remove(lineChunk);
                                i = lineChunk.length();
                            }
                            break;
                        default:
                            lifoStack.add(lineChunk.charAt(i));
                            break;
                    }
                }
            }
        }

        lifoStack.clear();

        for (String lineChunk : incompleteLineChunks) {
            double score = 0;
            for (int i = 0; i < lineChunk.length(); i++) {
                if (lifoStack.size() == 0) {
                    lifoStack.add(lineChunk.charAt(i));
                } else {
                    if (lineChunk.charAt(i) == ')' || lineChunk.charAt(i) == ']' || lineChunk.charAt(i) == '}' || lineChunk.charAt(i) == '>') {
                        lifoStack.remove(lifoStack.size() - 1);
                    } else {
                        lifoStack.add(lineChunk.charAt(i));
                    }
                }
            }
            for (int i = lifoStack.size() - 1; i >= 0; i--) {
                score *= 5;
                switch (lifoStack.get(i)) {
                    case '(' -> score += VALUE_PARENTHESE_SECOND;
                    case '[' -> score += VALUE_SQUARE_BRACKET_SECOND;
                    case '{' -> score += VALUE_CURLY_BRACKET_SECOND;
                    case '<' -> score += VALUE_ANGLE_BRACKET_SECOND;
                }
            }
            totalScore.add(score);
            lifoStack.clear();
        }

        Collections.sort(totalScore);

        return totalScore.get((int) Math.floor((float) totalScore.size() / 2.0));
    }
}
