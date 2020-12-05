package io.github.amerebagatelle.util.objects;

import io.github.amerebagatelle.util.RegexPatternBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Passport {
    private static final Pattern pattern = RegexPatternBuilder.create()
            .addGroup("key", ".*")
            .addLiteral(":")
            .addGroup("restOfLine", ".*").build();

    private static HashMap<String, String> fields = new HashMap<>();

    public static boolean parsePassportString(String passport, boolean validate) {
        fields = new HashMap<>();
        boolean parsed = parseValues(passport);
        boolean validated;
        if (validate && parsed) validated = validateValues();
        else validated = true;
        return parsed && validated;
    }

    private static boolean parseValues(String passportString) {
        List<String> parseFor = List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
        String[] split = passportString.split(" ");
        int parsed = 0;
        for (String s : split) {
            Matcher matcher = pattern.matcher(s);
            if (!matcher.find()) {
                throw new IllegalStateException("Can't match to matcher, is something wrong with the input?");
            }
            for (String value : parseFor) {
                if (s.contains(value)) {
                    fields.put(value, matcher.group("restOfLine"));
                    parsed++;
                }
            }
        }
        return parsed == 7;
    }

    private static boolean validateValues() {
        int byr = Integer.parseInt(fields.get("byr"));
        int iyr = Integer.parseInt(fields.get("iyr"));
        int eyr = Integer.parseInt(fields.get("eyr"));
        String hgt = fields.get("hgt");
        String hcl = fields.get("hcl");
        String ecl = fields.get("ecl");
        String pid = fields.get("pid");
        return !(byr >= 1920 && byr <= 2002 &&
                validateHair(hcl) &&
                validateHeight(hgt) &&
                ecl.matches("amb|blu|brn|gry|grn|hzl|oth") &&
                iyr >= 2010 && iyr <= 2020 &&
                eyr >= 2020 && eyr <= 2030 &&
                pid.length() == 9);
    }

    private static boolean validateHeight(String height) {
        int heightNumber = Integer.parseInt(height.replaceAll("cm|in", ""));
        if (height.contains("cm")) {
            return heightNumber >= 150 && heightNumber <= 193;
        } else if (height.contains("in")) {
            return heightNumber >= 59 && heightNumber <= 76;
        } else return false;
    }

    private static boolean validateHair(String color) {
        if (color.charAt(0) != '#') return false;
        return color.matches("#([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8})");
    }
}
