package io.github.amerebagatelle.solvers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 extends AbstractSolver {
    private final List<String> lines = new ArrayList<>();

    @Override
    public void retrieveData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input11.txt"));

            lines.addAll(reader.lines().collect(Collectors.toList()));

            reader.close();

            super.retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void part1() {
        char[][] seats = new char[lines.size()][lines.get(0).length()];
        for (int y = 0; y < lines.size(); y++) {
            char[] lineChars = lines.get(y).toCharArray();
            for (int x = 0; x < lineChars.length; x++) {
                seats[y][x] = lineChars[x];
            }
        }
        char[][] seatsLast;
        boolean modified;
        do {
            seatsLast = Arrays.stream(seats).map(char[]::clone).toArray(char[][]::new);
            modified = false;
            for (int y = 0; y < seats.length; y++) {
                for (int x = 0; x < seats[0].length; x++) {
                    char seat = seats[y][x];

                    if (seat == '.') continue;
                    if (seat == 'L') {
                        if (getSeatsAround(seatsLast, y, x, '#') == 0) {
                            seats[y][x] = '#';
                            modified = true;
                        }
                    }
                    if (seat == '#') {
                        if (getSeatsAround(seatsLast, y, x, '#') >= 4) {
                            seats[y][x] = 'L';
                            modified = true;
                        }
                    }
                }
            }
        } while (modified);
        int value = 0;
        for (char[] seat : seats) {
            for (int x = 0; x < seats[0].length; x++) {
                if (seat[x] == '#') value++;
            }
        }
        System.out.println("Answer: " + value);
    }

    private int getSeatsAround(char[][] seats, int y, int x, char type) {
        int value = 0;
        if (doesIndexExist(seats, y - 1, x - 1) && seats[y - 1][x - 1] == type) value++;
        if (doesIndexExist(seats, y - 1, x) && seats[y - 1][x] == type) value++;
        if (doesIndexExist(seats, y - 1, x + 1) && seats[y - 1][x + 1] == type) value++;
        if (doesIndexExist(seats, y, x - 1) && seats[y][x - 1] == type) value++;
        if (doesIndexExist(seats, y, x + 1) && seats[y][x + 1] == type) value++;
        if (doesIndexExist(seats, y + 1, x - 1) && seats[y + 1][x - 1] == type) value++;
        if (doesIndexExist(seats, y + 1, x) && seats[y + 1][x] == type) value++;
        if (doesIndexExist(seats, y + 1, x + 1) && seats[y + 1][x + 1] == type) value++;
        return value;
    }

    private boolean doesIndexExist(char[][] indexed, int y, int x) {
        return indexed.length > y && y >= 0 && indexed[y].length > x && x >= 0;
    }

    @Override
    public void part2() {
        char[][] seats = new char[lines.size()][lines.get(0).length()];
        for (int y = 0; y < lines.size(); y++) {
            char[] lineChars = lines.get(y).toCharArray();
            System.arraycopy(lineChars, 0, seats[y], 0, lineChars.length);
        }
        char[][] seatsLast;
        boolean modified;
        do {
            seatsLast = Arrays.stream(seats).map(char[]::clone).toArray(char[][]::new);
            modified = false;
            for (int y = 0; y < seats.length; y++) {
                for (int x = 0; x < seats[0].length; x++) {
                    char seat = seats[y][x];

                    if (seat == '.') continue;
                    if (seat == 'L') {
                        if (getVisibleSeatsAround(seatsLast, y, x, '#') == 0) {
                            seats[y][x] = '#';
                            modified = true;
                        }
                    }
                    if (seat == '#') {
                        if (getVisibleSeatsAround(seatsLast, y, x, '#') >= 5) {
                            seats[y][x] = 'L';
                            modified = true;
                        }
                    }
                }
            }
        } while (modified);
        int value = 0;
        for (char[] seat : seats) {
            for (int x = 0; x < seats[0].length; x++) {
                if (seat[x] == '#') value++;
            }
        }
        System.out.println("Answer: " + value);
    }

    private int getVisibleSeatsAround(char[][] seats, int y, int x, char type) {
        int value = 0;
        value += getForSlope(seats, y, x, 0, 1, type);
        value += getForSlope(seats, y, x, 1, 1, type);
        value += getForSlope(seats, y, x, 1, 0, type);
        value += getForSlope(seats, y, x, 1, -1, type);
        value += getForSlope(seats, y, x, 0, -1, type);
        value += getForSlope(seats, y, x, -1, -1, type);
        value += getForSlope(seats, y, x, -1, 0, type);
        value += getForSlope(seats, y, x, -1, 1, type);
        return value;
    }

    private int getForSlope(char[][] seats, int y, int x, int down, int right, char type) {
        int trees = 0;
        int posX = x;
        for (int i = y; i < seats.length && i > -1 && posX < seats[0].length && posX > -1; i += 0) {
            if (!doesIndexExist(seats, i, posX)) break;
            if ((i != y || posX != x) && (seats[i][posX] == 'L' || seats[i][posX] == '#')) {
                if (seats[i][posX] == type) trees++;
                break;
            }
            posX += right;
            i += down;
        }
        return trees;
    }

    @Override
    public int getDay() {
        return 11;
    }
}
