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
}
