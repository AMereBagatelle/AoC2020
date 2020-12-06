package io.github.amerebagatelle.util.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerGroup {
    private final List<String> people = new ArrayList<>();

    public AnswerGroup(String... people) {
        this.people.addAll(Arrays.stream(people).collect(Collectors.toList()));
    }

    public int getQuestionsAnswered() {
        List<Character> result = new ArrayList<>();
        for (String person : people) {
            for (char c : person.toCharArray()) {
                result.add(c);
            }
        }
        result = result.stream().distinct().collect(Collectors.toList());
        return result.size();
    }

    public int getQuestionsAnsweredEveryone() {
        List<Character> characters = new ArrayList<>();
        for (String person : people) {
            for (char c : person.toCharArray()) {
                characters.add(c);
            }
        }
        characters = characters.stream().distinct().collect(Collectors.toList());
        for (String person : people) {
            findPersonContainsChar(characters, person);
        }
        return characters.size();
    }

    private void findPersonContainsChar(List<Character> characters, String person) {
        for (int i = 0; i < characters.size(); i++) {
            if (!person.contains(String.valueOf(characters.get(i)))) {
                characters.remove(characters.get(i));
                findPersonContainsChar(characters, person);
            }
        }
    }

    public void addPerson(String person) {
        people.add(person);
    }
}
