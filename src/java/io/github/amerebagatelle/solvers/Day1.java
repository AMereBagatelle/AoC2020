package java.io.github.amerebagatelle.solvers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1 extends AbstractSolver {
    public List<Integer> numbers = new ArrayList<>();

    @Override
    public void retrieveData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("input1.txt")));

            String line = reader.readLine();
            while (line != null) {
                numbers.add(Integer.parseInt(line));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void part1() {
        for (int number : numbers) {
            for (int number2 : numbers) {
                if (number + number2 == 2020) {
                    System.out.println("Answer: " + number * number2);
                    return;
                }
            }
        }
    }

    @Override
    public void part2() {
        for (int number : numbers) {
            for (int number2 : numbers) {
                for (int number3 : numbers) {
                    if (number + number2 + number3 == 2020) {
                        System.out.println("Answer: " + number * number2 * number3);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public int getDay() {
        return 1;
    }
}
