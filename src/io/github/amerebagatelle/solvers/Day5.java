package io.github.amerebagatelle.solvers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 extends AbstractSolver {
    private final List<String> lines = new ArrayList<>();

    @Override
    public void retrieveData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("input5.txt")));

            lines.addAll(reader.lines().collect(Collectors.toList()));

            reader.close();

            super.retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void part1() {
        int highest = 0;
        for (String line : lines) {
            int seatId = getSeatId(line);
            if (seatId > highest) highest = seatId;
        }
        System.out.println("Answer: " + highest);
    }

    private int getSeatId(String seat) {
        int id = 0;
        for (int i = 0; i < seat.toCharArray().length; i++) {
            char c = seat.toCharArray()[i];
            if (!(c == 'F' || c == 'L')) id += 1 << (9 - i);
        }
        return id;
    }

    @Override
    public void part2() {
        int highest = 0;
        int lowest = 2056;
        List<Integer> seats = new ArrayList<>();
        for (String line : lines) {
            int seatId = getSeatId(line);
            if (seatId > highest) highest = seatId;
            if (seatId < lowest) lowest = seatId;
            seats.add(seatId);
        }
        for (int i = lowest; i < highest; i++) {
            if (!seats.contains(i)) System.out.println("Seat found: " + i);
        }
    }

    @Override
    public int getDay() {
        return 5;
    }
}
