package io.github.amerebagatelle.util.objects;

import io.github.amerebagatelle.util.exception.PassportParseException;

import java.util.HashMap;
import java.util.List;

public class Passport {
    public String original;
    public final HashMap<String, String> fields = new HashMap<>();

    public Passport(String passport, boolean validate) throws PassportParseException {
        original = passport;
        parseValues(fields);
        if (validate) validateValues();
    }

    private void parseValues(HashMap<String, String> values) throws PassportParseException {
        List<String> parseFor = List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
        String[] split = original.split(" ");
        int parsed = 0;
        for (String s : split) {
            for (String value : parseFor) {
                if (s.contains(value)) {
                    values.put(value, s.substring(s.indexOf(":") + 1));
                    parsed++;
                }
            }
        }
        if (parsed < 7) throw new PassportParseException("");
    }

    private void validateValues() throws PassportParseException {
        int byr = Integer.parseInt(fields.get("byr"));
        int iyr = Integer.parseInt(fields.get("iyr"));
        int eyr = Integer.parseInt(fields.get("eyr"));
        String hgt = fields.get("hgt");
        String hcl = fields.get("hcl");
        String ecl = fields.get("ecl");
        String pid = fields.get("pid");
        if (!(byr >= 1920 && byr <= 2002 &&
                iyr >= 2010 && iyr <= 2020 &&
                eyr >= 2020 && eyr <= 2030 &&
                validateHeight(hgt) &&
                validateHair(hcl) &&
                ecl.matches("amb|blu|brn|gry|grn|hzl|oth") &&
                pid.length() == 9)
        ) {
            throw new PassportParseException("");
        }
    }

    private boolean validateHeight(String height) {
        int heightNumber = Integer.parseInt(height.replaceAll("cm|in", ""));
        if (height.contains("cm")) {
            return heightNumber >= 150 && heightNumber <= 193;
        } else if (height.contains("in")) {
            return heightNumber >= 59 && heightNumber <= 76;
        } else return false;
    }

    private boolean validateHair(String color) {
        if (color.charAt(0) != '#') return false;
        return color.matches("#([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8})");
    }
}
