package io.github.amerebagatelle.util;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<Long> getLongArray(List<String> stringArray) {
        ArrayList<Long> result = new ArrayList<>();
        for (String stringValue : stringArray) {
            try {
                //Convert String to Integer, and store it into integer array list.
                result.add(Long.parseLong(stringValue));
            } catch (NumberFormatException ignored) {
            }
        }
        return result;
    }

    public static List<Integer> getIntArray(List<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<>();
        for (String stringValue : stringArray) {
            try {
                //Convert String to Integer, and store it into integer array list.
                result.add(Integer.parseInt(stringValue));
            } catch (NumberFormatException ignored) {
            }
        }
        return result;
    }

    public static boolean charArraysEquals(char[][] first, char[][] second) {
        boolean result = true;
        outer:
        for (int i = 0; i < first.length; i++) {
            for (int i1 = 0; i1 < first[i].length; i1++) {
                if (first[i][i1] != second[i][i1]) {
                    result = false;
                    break outer;
                }
            }
        }
        return result;
    }
}
