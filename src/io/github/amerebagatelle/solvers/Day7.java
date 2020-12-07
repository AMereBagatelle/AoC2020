package io.github.amerebagatelle.solvers;

import io.github.amerebagatelle.util.RegexPatternBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day7 extends AbstractSolver {
    private static final Pattern pattern = RegexPatternBuilder.create()
            .addLiteral("^")
            .addGroup("first", ".*")
            .addLiteral(" contain ")
            .addGroup("second", ".*")
            .addLiteral(".")
            .build();
    private static final Pattern miniPattern = RegexPatternBuilder.create()
            .addGroup("number", "\\d+")
            .addLiteral(" ")
            .addGroup("name", ".*")
            .build();

    private final List<String> lines = new ArrayList<>();

    @Override
    public void retrieveData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("input7.txt")));

            lines.addAll(reader.lines().collect(Collectors.toList()));

            reader.close();

            super.retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void part1() {
        HashMap<String, String[]> rules = new HashMap<>();
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (!matcher.find()) {
                System.out.println("Could not parse line");
                continue;
            }
            rules.put(matcher.group("first"), matcher.group("second").split(", "));
        }
        searchResult = 0;
        alreadyFound = new ArrayList<>();
        System.out.println("Answer: " + searchForCombinations(rules, "shiny gold"));
    }

    private List<String> alreadyFound = new ArrayList<>();
    private int searchResult;

    private int searchForCombinations(HashMap<String, String[]> toSearch, String toFind) {
        for (String key : toSearch.keySet()) {
            String[] value = toSearch.get(key);
            for (String s : value) {
                if (s.contains(toFind)) {
                    if (!alreadyFound.contains(key)) searchResult++;
                    alreadyFound.add(key);
                    String key2 = key.charAt(key.length() - 1) == 's' ? key.substring(0, key.length() - 1) : key;
                    searchForCombinations(toSearch, key2);
                }
            }
        }
        return searchResult;
    }

    @Override
    public void part2() {
        HashMap<String, String[]> rules = new HashMap<>();
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (!matcher.find()) {
                System.out.println("Could not parse line");
                continue;
            }
            rules.put(matcher.group("first"), matcher.group("second").split(", "));
        }
        searchResult = 0;
        System.out.println("Answer: " + howManyContains(rules, "shiny gold"));
    }

    private int howManyContains(HashMap<String, String[]> toSearch, String toFind) {
        int result = 0;
        for (String s : toSearch.keySet()) {
            if (s.contains(toFind)) {
                String[] searched = toSearch.get(s);
                for (String s1 : searched) {
                    if (!s1.contains("no other")) {
                        int i = Integer.parseInt(s1.substring(0, s1.indexOf(" ")));
                        result += i + i * howManyContains(toSearch, s1.substring(s1.indexOf(" ") + 1));
                    } else {
                        return 0;
                    }
                }
            }
        }

        return result;
    }

    @Override
    public int getDay() {
        return 7;
    }
}
