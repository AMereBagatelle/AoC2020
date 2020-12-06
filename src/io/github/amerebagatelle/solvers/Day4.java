package io.github.amerebagatelle.solvers;

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
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            boolean blank = line.isBlank();
            if (!blank) temp.append(line.replace("\n", "")).append(" ");
            if (blank || i == lines.size() - 1) { // extra check here is just for that last line
                temp.deleteCharAt(temp.length() - 1);
                if (tryPassportParse(temp, testForValidity)) valid++;
                temp = new StringBuilder();
            }
        }

        return valid;
    }

    private boolean tryPassportParse(StringBuilder toParse, boolean testForValidity) {
        return Passport.parsePassportString(toParse.toString(), testForValidity);
    }

    @Override
    public int getDay() {
        return 4;
    }
}
