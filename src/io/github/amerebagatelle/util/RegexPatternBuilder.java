package io.github.amerebagatelle.util;

import java.util.regex.Pattern;

public class RegexPatternBuilder {
    private final StringBuilder builder = new StringBuilder();

    private RegexPatternBuilder() {

    }

    public static RegexPatternBuilder create() {
        return new RegexPatternBuilder();
    }

    public RegexPatternBuilder addGroup(String key, String matchTo) {
        builder.append("(?<").append(key).append(">").append(matchTo).append(")");
        return this;
    }

    public RegexPatternBuilder addLiteral(String literal) {
        builder.append(literal);
        return this;
    }

    public Pattern build() {
        return Pattern.compile(builder.toString());
    }
}
