package io.github.amerebagatelle.solvers;

public abstract class AbstractSolver {
    public boolean hasData = false;

    public void retrieveData() {
        hasData = true;
    }

    public abstract void part1();

    public abstract void part2();

    public abstract int getDay();
}
