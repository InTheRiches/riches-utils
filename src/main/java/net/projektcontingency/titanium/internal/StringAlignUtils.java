package net.projektcontingency.titanium.internal;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

public class StringAlignUtils extends Format {

    private static final long serialVersionUID = 1L;

    public enum Alignment {
        LEFT, CENTER, RIGHT,
    }

    /**
     * Current justification for formatting
     */
    private Alignment currentAlignment;

    /**
     * Current max length of a line
     */
    private final int maxChars;

    public StringAlignUtils(int maxChars, Alignment align) {
        switch (align) {
            case LEFT, CENTER, RIGHT -> this.currentAlignment = align;
            default -> throw new IllegalArgumentException("invalid justification arg.");
        }
        if (maxChars < 0)
            throw new IllegalArgumentException("maxChars must be positive.");

        this.maxChars = maxChars;
    }

    public static String alignCenter(String msg) {
        return new StringAlignUtils(Alignment.CENTER).format(msg);
    }

    public static String alignCenter(int maxChars, String msg) {
        return new StringAlignUtils(maxChars, Alignment.CENTER).format(msg);
    }

    public StringAlignUtils(Alignment align) {
        int maxChars = 30;
        switch (align) {
            case LEFT, CENTER, RIGHT -> this.currentAlignment = align;
            default -> throw new IllegalArgumentException("invalid justification arg.");
        }

        this.maxChars = maxChars;
    }

    public StringBuffer format(Object input, StringBuffer where, FieldPosition ignore) {
        String s = input.toString();
        List<String> strings = splitInputString(s);

        for (String wanted : strings) {
            //Get the spaces in the right place.
            switch (currentAlignment) {
                case RIGHT -> {
                    pad(where, maxChars - wanted.length());
                    where.append(wanted);
                }
                case CENTER -> {
                    int toAdd = maxChars - wanted.length();
                    pad(where, toAdd / 2);
                    where.append(wanted);
                    pad(where, toAdd - toAdd / 2);
                }
                case LEFT -> {
                    where.append(wanted);
                    pad(where, maxChars - wanted.length());
                }
            }
            where.append("\n");
        }
        return where;
    }

    protected final void pad(StringBuffer to, int howMany) {
        to.append(" ".repeat(Math.max(0, howMany)));
    }

    String format(String s) {
        return format(s, new StringBuffer(), null).toString();
    }

    /**
     * ParseObject is required, but not useful here.
     */
    public Object parseObject(String source, ParsePosition pos) {
        return source;
    }

    private List<String> splitInputString(String str) {
        List<String> list = new ArrayList<String>();
        if (str == null)
            return list;
        for (int i = 0; i < str.length(); i = i + maxChars) {
            int endindex = Math.min(i + maxChars, str.length());
            list.add(str.substring(i, endindex));
        }
        return list;
    }
}