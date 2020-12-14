package io.github.amerebagatelle.solvers;

import io.github.amerebagatelle.util.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Day13 extends AbstractSolver {
    private final List<String> lines = new ArrayList<>();

    @Override
    public void retrieveData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input13.txt"));

            lines.addAll(reader.lines().collect(Collectors.toList()));

            reader.close();

            super.retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void part1() {
        int earliest = Integer.parseInt(lines.get(0));
        List<Integer> ids = Util.getIntArray(Arrays.stream(lines.get(1).replace("x", "").split(",")).collect(Collectors.toList()));
        TreeMap<Integer, Integer> possibilities = new TreeMap<>();
        for (Integer id : ids) {
            int i = 0;
            while (id * i < earliest) {
                i++;
            }
            possibilities.put(id * i, id);
        }
        System.out.println("Answer: " + (possibilities.firstEntry().getKey() - earliest) * possibilities.firstEntry().getValue());
    }

    @Override
    public void part2() {
        List<Long> modulos = new ArrayList<>();
        List<Long> other = new ArrayList<>();
        String[] linesSplit = lines.get(1).split(",");
        for (int i = 0; i < linesSplit.length; i++) {
            if (!linesSplit[i].equals("x")) {
                long num = Long.parseLong(linesSplit[i]);
                other.add(num);
                modulos.add((num - (i - num)) % num);
            }
        }
        System.out.println("Answer: " + Util.chineseRemainder(other.toArray(new Long[0]), modulos.toArray(new Long[0])));
    }

    @Override
    public int getDay() {
        return 13;
    }
}
