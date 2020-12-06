package io.github.amerebagatelle.solvers;

import io.github.amerebagatelle.util.objects.AnswerGroup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 extends AbstractSolver {
    private final List<String> lines = new ArrayList<>();

    @Override
    public void retrieveData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("input6.txt")));

            lines.addAll(reader.lines().collect(Collectors.toList()));

            reader.close();

            super.retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void part1() {
        List<AnswerGroup> groupList = new ArrayList<>();
        AnswerGroup group = new AnswerGroup();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            boolean blank = line.isBlank();
            if (!blank) group.addPerson(line);
            if (blank || i == lines.size() - 1) { // extra check here is just for that last line
                groupList.add(group);
                group = new AnswerGroup();
            }
        }
        int answer = 0;
        for (AnswerGroup answerGroup : groupList) {
            answer += answerGroup.getQuestionsAnswered();
        }
        System.out.println("Answer: " + answer);
    }

    @Override
    public void part2() {
        List<AnswerGroup> groupList = new ArrayList<>();
        AnswerGroup group = new AnswerGroup();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            boolean blank = line.isBlank();
            if (!blank) group.addPerson(line);
            if (blank || i == lines.size() - 1) { // extra check here is just for that last line
                groupList.add(group);
                group = new AnswerGroup();
            }
        }
        int answer = 0;
        for (AnswerGroup answerGroup : groupList) {
            answer += answerGroup.getQuestionsAnsweredEveryone();
        }
        System.out.println("Answer: " + answer);
    }

    @Override
    public int getDay() {
        return 6;
    }
}
