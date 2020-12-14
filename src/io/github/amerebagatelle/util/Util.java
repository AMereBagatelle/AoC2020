package io.github.amerebagatelle.util;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static long chineseRemainder(Long[] n, Long[] a) {
        long prod = Arrays.stream(n).reduce(1L, (i, j) -> i * j);

        long p, sm = 0;
        for (int i = 0; i < n.length; i++) {
            p = prod / n[i];
            sm += a[i] * mulInv(p, n[i]) * p;
        }
        return sm % prod;
    }

    private static long mulInv(long a, long b) {
        long b0 = b;
        long x0 = 0;
        long x1 = 1;

        if (b == 1)
            return 1;

        while (a > 1) {
            long q = a / b;
            long amb = a % b;
            a = b;
            b = amb;
            long xqx = x1 - q * x0;
            x1 = x0;
            x0 = xqx;
        }

        if (x1 < 0)
            x1 += b0;

        return x1;
    }
}
