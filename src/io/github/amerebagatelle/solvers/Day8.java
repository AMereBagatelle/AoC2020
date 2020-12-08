package io.github.amerebagatelle.solvers;

import io.github.amerebagatelle.util.RegexPatternBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day8 extends AbstractSolver {
    private static final Pattern pattern = RegexPatternBuilder.create()
            .addGroup("command", ".*")
            .addLiteral(" ")
            .addGroup("value", "\\+?-?\\d+")
            .build();
    private final List<String> lines = new ArrayList<>();

    @Override
    public void retrieveData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("input8.txt")));

            lines.addAll(reader.lines().collect(Collectors.toList()));

            reader.close();

            super.retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void part1() {
        int i = 0;
        int accumulator = 0;
        List<Integer> alreadyExecuted = new ArrayList<>();
        while (lines.size() > i) {
            Matcher matcher = pattern.matcher(lines.get(i));
            if (!matcher.find()) {
                System.out.println("Can't parse");
                continue;
            }
            String command = matcher.group("command");
            System.out.println("Running command " + command + " at " + i);
            if (!alreadyExecuted.contains(i)) {
                alreadyExecuted.add(i);
                switch (command) {
                    case "nop" -> i++;
                    case "acc" -> {
                        accumulator += Integer.parseInt(matcher.group("value"));
                        i++;
                    }
                    case "jmp" -> i += Integer.parseInt(matcher.group("value"));
                }
            } else {
                break;
            }
        }
        System.out.println("Answer: " + accumulator);
    }

    @Override
    public void part2() {
        int accumulator = 0;
        boolean looped = true;
        int i = lines.size() - 1;
        while (looped) {
            accumulator = 0;
            int lineNumber = 0;
            List<Integer> alreadyExecuted = new ArrayList<>();
            List<String> linesCopy = new ArrayList<>(lines);
            for (int i1 = 0; i1 < lines.size(); i1++) {
                if (i1 == i) {
                    Matcher matcher = pattern.matcher(lines.get(i));
                    if (!matcher.find()) {
                        System.out.println("Can't parse");
                        continue;
                    }
                    String command = matcher.group("command");
                    int value = Integer.parseInt(matcher.group("value"));
                    if (command.equals("nop")) {
                        linesCopy.set(i, "jmp " + value);
                    } else if (command.equals("jmp")) {
                        linesCopy.set(i, "nop " + value);
                    }
                }
            }
            boolean wasLooped = false;
            while (linesCopy.size() > lineNumber) {
                Matcher matcher = pattern.matcher(linesCopy.get(lineNumber));
                if (!matcher.find()) {
                    System.out.println("Can't parse");
                    continue;
                }
                String command = matcher.group("command");
                if (alreadyExecuted.contains(lineNumber)) {
                    wasLooped = true;
                    break;
                }
                alreadyExecuted.add(lineNumber);
                switch (command) {
                    case "nop" -> {
                        lineNumber++;
                    }
                    case "acc" -> {
                        accumulator += Integer.parseInt(matcher.group("value"));
                        lineNumber++;
                    }
                    case "jmp" -> lineNumber += Integer.parseInt(matcher.group("value"));
                }
            }
            looped = wasLooped;
            i--;
        }
        System.out.println("Answer: " + accumulator);
    }

    @Override
    public int getDay() {
        return 8;
    }
}
