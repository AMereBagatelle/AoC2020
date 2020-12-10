package io.github.amerebagatelle;

import io.github.amerebagatelle.solvers.AbstractSolver;
import io.github.amerebagatelle.solvers.Day1;
import io.github.amerebagatelle.solvers.Day2;
import io.github.amerebagatelle.solvers.Day3;
import io.github.amerebagatelle.solvers.Day4;
import io.github.amerebagatelle.solvers.Day5;
import io.github.amerebagatelle.solvers.Day6;
import io.github.amerebagatelle.solvers.Day7;
import io.github.amerebagatelle.solvers.Day8;
import io.github.amerebagatelle.solvers.Day9;

import java.util.ArrayList;
import java.util.List;

public class SolverManager {
    public static final List<AbstractSolver> solvers = new ArrayList<>();

    public SolverManager() {
        solvers.add(new Day1());
        solvers.add(new Day2());
        solvers.add(new Day3());
        solvers.add(new Day4());
        solvers.add(new Day5());
        solvers.add(new Day6());
        solvers.add(new Day7());
        solvers.add(new Day8());
        solvers.add(new Day9());
    }

    public void solve(int day, int part) {
        for (AbstractSolver solver : solvers) {
            if (day == solver.getDay()) {
                if (!solver.hasData) solver.retrieveData();

                long startTime = System.nanoTime();
                switch (part) {
                    case 1 -> solver.part1();
                    case 2 -> solver.part2();
                }
                long elapsedTime = System.nanoTime() - startTime;
                System.out.println("Solved in: " + elapsedTime / 1000000 + "ms");
            }
        }
    }
}
