package io.github.amerebagatelle;

import io.github.amerebagatelle.solvers.AbstractSolver;
import io.github.amerebagatelle.solvers.Day1;

import java.util.ArrayList;
import java.util.List;

public class SolverManager {
    public static final List<AbstractSolver> solvers = new ArrayList<>();

    public SolverManager() {
        solvers.add(new Day1());
    }

    public void solve(int day, int part) {
        for (AbstractSolver solver : solvers) {
            if (day == solver.getDay()) {
                solver.retrieveData();
                switch (part) {
                    case 1 -> solver.part1();
                    case 2 -> solver.part2();
                }
            }
        }
    }
}
