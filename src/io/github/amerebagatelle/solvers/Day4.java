package io.github.amerebagatelle.solvers;

import io.github.amerebagatelle.util.exception.PassportParseException;
import io.github.amerebagatelle.util.objects.Passport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 extends AbstractSolver {
    private final List<String> lines = new ArrayList<>();

    @Override
    public void retrieveData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("input4.txt")));

            lines.addAll(reader.lines().collect(Collectors.toList()));

            reader.close();

            super.retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void part1() {
        StringBuilder temp = new StringBuilder();
        int valid = 0;
        for (String line : lines) {
            if (!line.isBlank()) {
                temp.append(line.replace("\n", "")).append(" ");
            } else {
                try {
                    new Passport(temp.substring(0, temp.length() - 1), false);
                    valid++;
                } catch (PassportParseException e) {
                    System.out.println("Could not parse passport");
                }
                temp = new StringBuilder();
            }
        }
        try {
            new Passport(temp.substring(0, temp.length() - 1), false);
            valid++;
        } catch (PassportParseException e) {
            System.out.println("Could not parse passport");
        }
        System.out.println("Answer: " + valid);
    }

    @Override
    public void part2() {
        StringBuilder temp = new StringBuilder();
        int valid = 0;
        for (String line : lines) {
            if (!line.isBlank()) {
                temp.append(line.replace("\n", "")).append(" ");
            } else {
                try {
                    new Passport(temp.substring(0, temp.length() - 1), true);
                    valid++;
                } catch (PassportParseException e) {
                    System.out.println("Could not parse passport");
                }
                temp = new StringBuilder();
            }
        }
        try {
            new Passport(temp.substring(0, temp.length() - 1), true);
            valid++;
        } catch (PassportParseException e) {
            System.out.println("Could not parse passport");
        }
        System.out.println("Answer: " + valid);
    }

    @Override
    public int getDay() {
        return 4;
    }
}
