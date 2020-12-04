package io.github.amerebagatelle.solvers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day2 extends AbstractSolver {
    public static final Pattern pattern = Pattern.compile("^(?<begin>\\d+)-(?<end>\\d+) (?<toContain>.): (?<restOfLine>.*)$");

    public final List<String> lines = new ArrayList<>();

    @Override
    public void retrieveData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("input2.txt")));

            lines.addAll(reader.lines().collect(Collectors.toList()));

            reader.close();

            super.retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void part1() {
        int validLines = 0;
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (!matcher.find()) {
                System.out.println("Failed to parse.");
            }
            int begin = Integer.parseInt(matcher.group("begin"));
            int end = Integer.parseInt(matcher.group("end"));
            String toContain = matcher.group("toContain");
            String restOfLine = matcher.group("restOfLine");
            if (restOfLine.contains(toContain)) {
                int count = countSubstring(restOfLine, toContain);
                if (begin <= count && end >= count) {
                    validLines++;
                }
            }
        }
        System.out.println(validLines);
    }

    @Override
    public void part2() {
        int validLines = 0;
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (!matcher.find()) {
                System.out.println("Failed to parse.");
            }
            int begin = Integer.parseInt(matcher.group("begin"));
            int end = Integer.parseInt(matcher.group("end"));
            char toContain = matcher.group("toContain").charAt(0);
            String restOfLine = matcher.group("restOfLine");
            if (restOfLine.charAt(begin - 1) == toContain ^ restOfLine.charAt(end - 1) == toContain) {
                validLines++;
            }
        }
        System.out.println(validLines);
    }

    @Override
    public int getDay() {
        return 2;
    }

    private int countSubstring(String string, String substring) {
        int index = string.indexOf(substring);
        int count = 0;
        while (index != -1) {
            count++;
            index = string.indexOf(substring, index + 1);
        }
        return count;
    }
}
