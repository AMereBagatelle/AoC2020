package io.github.amerebagatelle;

import java.util.Scanner;

public class Main {
    public static final SolverManager solverManager = new SolverManager();

    public static int day;
    public static int part;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("What day would you like to solve?");
        day = in.nextInt();
        System.out.println("What part of the day would you like to solve?");
        part = in.nextInt();

        solverManager.solve(day, part);
    }
}
