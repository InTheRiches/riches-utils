package net.projektcontingency.titanium.internal;

import org.bukkit.ChatColor;

public enum Unicode {
    DIVIDER( "\uE050"),
    CONFIRM_MAIL( "\uE100"),
    LEVEL( "\uE013"),
    SAND_DOLLARS( "\uDB86\uDDFA"),

    CUSTOM_MENU_SPACE( "\uF809"),
    ADD_FRIEND_SPACE( "\uF810"),

    SMILE( "\uE015"),
    SLIGHT_SMILE( "\uE016"),
    NEUTRAL( "\uE017"),
    SAD( "\uE018"),
    CRY( "\uE019"),
    MAD( "\uE020"),
    COLD( "\uE021"),
    DEVIL( "\uE022"),
    ANGEL( "\uE023"),
    IN_LOVE( "\uE024"),

    ADD_FRIEND_MENU( "\uE025"),
    PROFILE_MENU( "\uE027"),

    INFO_MARKER( "\uE028"),
    POINT( "\uE029"),

    COIN_ONE( "\uE030"),
    COIN_TWO( "\uE031"),
    COIN_THREE( "\uE032"),
    COIN_FOUR( "\uE033"),
    COIN_FIVE( "\uE034"),
    COIN_SIX( "\uE035"),
    COIN_SEVEN( "\uE036"),
    COIN_EIGHT( "\uE037"),
    COIN_NINE( "\uE038"),
    COIN_START( "\uE039"),
    COIN_END( "\uE040"),
    COIN_ZERO( "\uE041"),
    CART_MENU( "\uE042"),

    CHECKOUT_MENU( "\uE043"),
    CHAT_FILTER_SETTINGS_MENU_N( "\uE044"),
    CHAT_FILTER_SETTINGS_MENU_L( "\uE045"),
    CHAT_FILTER_SETTINGS_MENU_M( "\uE046"),
    CHAT_FILTER_SETTINGS_MENU_S( "\uE047"),
    CHAT_SETTINGS( "\uE048"),
    SETTINGS( "\uE049");

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
        return ChatColor.WHITE + this.text;
    }
}
