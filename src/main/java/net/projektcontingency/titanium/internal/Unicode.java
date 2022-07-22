package net.projektcontingency.titanium.internal;

import org.bukkit.ChatColor;

public enum Unicode {
    DIVIDER(ChatColor.WHITE + "\uE050"),
    CONFIRM_MAIL(ChatColor.WHITE + "\uE100"),
    LEVEL(ChatColor.WHITE + "\uE013"),
    SAND_DOLLARS(ChatColor.WHITE + "\uDB86\uDDFA"),

    CUSTOM_MENU_SPACE(ChatColor.WHITE + "\uF809"),
    ADD_FRIEND_SPACE(ChatColor.WHITE + "\uF810"),

    SMILE(ChatColor.WHITE + "\uE015"),
    SLIGHT_SMILE(ChatColor.WHITE + "\uE016"),
    NEUTRAL(ChatColor.WHITE + "\uE017"),
    SAD(ChatColor.WHITE + "\uE018"),
    CRY(ChatColor.WHITE + "\uE019"),
    MAD(ChatColor.WHITE + "\uE020"),
    COLD(ChatColor.WHITE + "\uE021"),
    DEVIL(ChatColor.WHITE + "\uE022"),
    ANGEL(ChatColor.WHITE + "\uE023"),
    IN_LOVE(ChatColor.WHITE + "\uE024"),

    ADD_FRIEND_MENU(ChatColor.WHITE + "\uE025"),
    PROFILE_MENU(ChatColor.WHITE + "\uE027"),

    INFO_MARKER(ChatColor.WHITE + "\uE028"),
    POINT(ChatColor.WHITE + "\uE029"),

    COIN_ONE(ChatColor.WHITE + "\uE030"),
    COIN_TWO(ChatColor.WHITE + "\uE031"),
    COIN_THREE(ChatColor.WHITE + "\uE032"),
    COIN_FOUR(ChatColor.WHITE + "\uE033"),
    COIN_FIVE(ChatColor.WHITE + "\uE034"),
    COIN_SIX(ChatColor.WHITE + "\uE035"),
    COIN_SEVEN(ChatColor.WHITE + "\uE036"),
    COIN_EIGHT(ChatColor.WHITE + "\uE037"),
    COIN_NINE(ChatColor.WHITE + "\uE038"),
    COIN_START(ChatColor.WHITE + "\uE039"),
    COIN_END(ChatColor.WHITE + "\uE040"),
    COIN_ZERO(ChatColor.WHITE + "\uE041"),
    CART_MENU(ChatColor.WHITE + "\uE042"),

    CHECKOUT_MENU(ChatColor.WHITE + "\uE043"),
    CHAT_FILTER_SETTINGS_MENU_N(ChatColor.WHITE + "\uE044"),
    CHAT_FILTER_SETTINGS_MENU_L(ChatColor.WHITE + "\uE045"),
    CHAT_FILTER_SETTINGS_MENU_M(ChatColor.WHITE + "\uE046"),
    CHAT_FILTER_SETTINGS_MENU_S(ChatColor.WHITE + "\uE047"),
    CHAT_SETTINGS(ChatColor.WHITE + "\uE048"),
    SETTINGS(ChatColor.WHITE + "\uE049");

    private final String text;

    Unicode(String string) {
        this.text = string;
    }

    public static String numToUni(String num) {
        StringBuilder output = new StringBuilder();
        for (char c : num.toCharArray())
            switch (c) {
                case '0' -> output.append("\uE002");
                case '1' -> output.append("\uE003");
                case '2' -> output.append("\uE004");
                case '3' -> output.append("\uE005");
                case '4' -> output.append("\uE006");
                case '5' -> output.append("\uE007");
                case '6' -> output.append("\uE008");
                case '7' -> output.append("\uE009");
                case '8' -> output.append("\uE010");
                case '9' -> output.append("\uE011");
                case ',' -> output.append("\uE012");
            }

        return output.toString();
    }

    public static String formatInt(int num) {
        String reversed = reverseString(num + "");
        StringBuilder formatted = new StringBuilder();
        if (reversed.length() < 4) return num + "";
        int count = 0;
        while ((reversed.length() - 1) / 3 > count) {
            if (count != 0) formatted.append(",");
            formatted.append(reversed, count * 3, (count * 3) + 3);
            count++;
        }
        String end = reversed.substring(count*3);
        if (!end.equals("")) formatted.append(",").append(end);
        return reverseString(formatted.toString());
    }

    public static String reverseString(String str) {
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() -1; i > - 1; i--)
            reversed.append(str.toCharArray()[i]);

        return reversed.toString();
    }

    public String getText() {
        return this.text;
    }
}
