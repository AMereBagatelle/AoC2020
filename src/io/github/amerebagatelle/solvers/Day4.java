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
        System.out.println("Answer: " + getValidPassports(false));
    }

    @Override
    public void part2() {
        System.out.println("Answer: " + getValidPassports(true));
    }

    private int getValidPassports(boolean testForValidity) {
        StringBuilder temp = new StringBuilder();
        int valid = 0;
        for (String line : lines) {
            if (!line.isBlank()) {
                temp.append(line.replace("\n", "")).append(" ");
            } else {
                temp.deleteCharAt(temp.length() - 1);
                if (tryPassportParse(temp, testForValidity)) valid++;
                temp = new StringBuilder();
            }
        }
        if (tryPassportParse(temp, testForValidity)) valid++; // get that last line

        return valid;
    }

    private boolean tryPassportParse(StringBuilder toParse, boolean testForValidity) {
        try {
            new Passport(toParse.toString(), testForValidity);
            return true;
        } catch (PassportParseException e) {
            return false;
        }
    }

    @Override
    public int getDay() {
        return 4;
    }
}
