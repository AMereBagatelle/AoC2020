package io.github.amerebagatelle.solvers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 extends AbstractSolver {
    public List<String> lines = new ArrayList<>();

    @Override
    public void retrieveData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("input3.txt")));

            lines.addAll(reader.lines().collect(Collectors.toList()));

            reader.close();

            super.retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void part1() {
        System.out.println(getTreesForSlope(3, 1));
    }

    @Override
    public void part2() {
        System.out.println(getTreesForSlope(1, 1) * getTreesForSlope(3, 1) * getTreesForSlope(5, 1) * getTreesForSlope(7, 1) * getTreesForSlope(1, 2));
    }

    @Override
    public int getDay() {
        return 3;
    }

    private long getTreesForSlope(int right, int down) {
        long trees = 0;
        int x = 0;
        for (int i = 0; i < lines.size(); i += down) {
            if (checkLine(lines.get(i), x)) trees++;
            x += right;
        }
        return trees;
    }

    private boolean checkLine(String line, int x) {
        int xModulo = x % line.length();
        return line.charAt(xModulo) == '#';
    }
}
