package io.github.amerebagatelle.solvers;

import io.github.amerebagatelle.util.RegexPatternBuilder;
import io.github.amerebagatelle.util.objects.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day12 extends AbstractSolver {
    private static final Pattern pattern = RegexPatternBuilder.create()
            .addLiteral("^")
            .addGroup("command", "N|S|E|W|L|R|F")
            .addGroup("distance", "\\d+")
            .addLiteral("$")
            .build();

    private final List<String> lines = new ArrayList<>();

    @Override
    public void retrieveData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input12.txt"));

            lines.addAll(reader.lines().collect(Collectors.toList()));

            reader.close();

            super.retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void part1() {
        Position position = new Position(0, 0, 90);
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (!matcher.find()) {
                System.out.println("Could not parse line: " + line);
                continue;
            }
            char command = matcher.group("command").charAt(0);
            int distance = Integer.parseInt(matcher.group("distance"));
            switch (command) {
                case 'N' -> position.north(distance);
                case 'S' -> position.south(distance);
                case 'E' -> position.east(distance);
                case 'W' -> position.west(distance);
                case 'F' -> position.forward(distance);
                case 'L' -> position.rotateLeft(distance);
                case 'R' -> position.rotateRight(distance);
            }
        }
        System.out.println("Answer: " + position.getManhattanFromStart());
    }

    @Override
    public void part2() {
        Position position = new Position();
        Position waypoint = new Position(10, 1, 0);
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (!matcher.find()) {
                System.out.println("Could not parse line: " + line);
                continue;
            }
            char command = matcher.group("command").charAt(0);
            int amount = Integer.parseInt(matcher.group("distance"));
            switch (command) {
                case 'N' -> waypoint.north(amount);
                case 'S' -> waypoint.south(amount);
                case 'E' -> waypoint.east(amount);
                case 'W' -> waypoint.west(amount);
                case 'F' -> {
                    position.x += waypoint.x * amount;
                    position.y += waypoint.y * amount;
                }
                case 'R' -> {
                    double radians = Math.atan2(waypoint.y, waypoint.x) - Math.toRadians(amount);
                    double distance = Math.sqrt((waypoint.x * waypoint.x) + (waypoint.y * waypoint.y));
                    waypoint.x = (int) Math.round(Math.cos(radians) * distance);
                    waypoint.y = (int) Math.round(Math.sin(radians) * distance);
                }
                case 'L' -> {
                    double radians = Math.toRadians(amount) + Math.atan2(waypoint.y, waypoint.x);
                    double distance = Math.sqrt(waypoint.x * waypoint.x + waypoint.y * waypoint.y);
                    waypoint.x = (int) Math.round(Math.cos(radians) * distance);
                    waypoint.y = (int) Math.round(Math.sin(radians) * distance);
                }
            }
        }
        System.out.println("Answer: " + position.getManhattanFromStart());
    }

    @Override
    public int getDay() {
        return 12;
    }
}
