package io.github.amerebagatelle.solvers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 extends AbstractSolver {
    private final List<String> lines = new ArrayList<>();

    @Override
    public void retrieveData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input9.txt"));

            lines.addAll(reader.lines().collect(Collectors.toList()));

            reader.close();

            super.retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void part1() {
        int preambleLength = 25;
        for (int i = preambleLength; i < lines.size(); i++) {
            List<Integer> sums = getSums(lines.subList(i - preambleLength, i));
            if (!sums.contains(Integer.parseInt(lines.get(i)))) {
                System.out.println("Answer: " + lines.get(i));
                break;
            }
        }
    }

    private List<Integer> getSums(List<String> input) {
        List<Integer> sums = new ArrayList<>();
        for (String i : input) {
            for (String i1 : input) {
                int parsed = Integer.parseInt(i) + Integer.parseInt(i1);
                if (!sums.contains(parsed)) sums.add(parsed);
            }
        }
        return sums;
    }

    @Override
    public void part2() {
        int preambleLength = 25;
        int result1 = 0;
        for (int i = preambleLength; i < lines.size(); i++) {
            List<Integer> sums = getSums(lines.subList(i - preambleLength, i));
            if (!sums.contains(Integer.parseInt(lines.get(i)))) {
                result1 = Integer.parseInt(lines.get(i));
                break;
            }
        }
        first:
        for (int i = 2; i < lines.size(); i++) {
            for (int i1 = 0; i1 < lines.size() - i; i1++) {
                List<String> numbers = lines.subList(i1, i1 + i);

                if (sum(numbers) == result1) {
                    List<Long> numbersClone = getLongArray(numbers);
                    Collections.sort(numbersClone);
                    System.out.println("Answer: " + (numbersClone.get(0) + numbersClone.get(numbersClone.size() - 1)));
                    break first;
                }
            }
        }
    }

    private long sum(List<String> numbers) {
        long result = 0;
        for (String number : numbers) {
            result += Long.parseLong(number);
        }
        return result;
    }

    private List<Long> getLongArray(List<String> stringArray) {
        ArrayList<Long> result = new ArrayList<>();
        for (String stringValue : stringArray) {
            try {
                //Convert String to Integer, and store it into integer array list.
                result.add(Long.parseLong(stringValue));
            } catch (NumberFormatException ignored) {
            }
        }
        return result;
    }

    @Override
    public int getDay() {
        return 9;
    }
}
