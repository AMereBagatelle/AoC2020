package io.github.amerebagatelle.solvers;

import io.github.amerebagatelle.util.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 extends AbstractSolver {
    private final List<String> lines = new ArrayList<>();

    @Override
    public void retrieveData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input10.txt"));

            lines.addAll(reader.lines().collect(Collectors.toList()));

            reader.close();

            super.retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void part1() {
        int currentVoltage = 0;
        int oneVolt = 0;
        int threeVolt = 0;
        List<Integer> linesInt = Util.getIntArray(lines);
        Collections.sort(linesInt);
        for (int adapter : linesInt) {
            int difference = adapter - currentVoltage;
            currentVoltage = adapter;
            if (difference == 1) {
                oneVolt++;
            } else if (difference == 3) {
                threeVolt++;
            }
        }
        threeVolt++;
        System.out.println(oneVolt);
        System.out.println(threeVolt);
        System.out.println("Answer: " + (oneVolt * threeVolt));
    }

    @Override
    public void part2() {
        List<Integer> linesInt = Util.getIntArray(lines);
        Collections.sort(linesInt);
        long[] total = new long[linesInt.size()];
        total[0] = 1;
        if (linesInt.get(0) < 3) total[1] = 1;
        if (linesInt.get(1) < 3 && linesInt.get(0) < 1) total[2] = 1;
        for (int i = 0; i < linesInt.size() - 1; i++) {
            total[i + 1] += total[i];
            if (i + 2 < linesInt.size() && linesInt.get(i + 2) <= linesInt.get(i) + 3) {
                total[i + 2] += total[i]; // tried to use the same bitshifting tricks here but it didn't quite work out
            }
            if (i + 3 < linesInt.size() && linesInt.get(i + 3) <= linesInt.get(i) + 3) {
                total[i + 3] = total[i] << total[i + 3];
            }
        }
        System.out.println("Answer: " + total[total.length - 1]);
    }

    @Override
    public int getDay() {
        return 10;
    }
}
