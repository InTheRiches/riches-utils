package net.projektcontingency.titanium.internal;

import org.bukkit.ChatColor;

import java.util.*;

public enum Translations {
    PREFIX("Projekt-Axolotl | "),
    APPLIED(ChatColor.RED + "Currently Applied"),
    EQUIPPED(ChatColor.RED + "Currently Equipped"),
    INSUFFICIENT_FUNDS(ChatColor.RED + "Insufficient funds in wallet."),
    PURCHASE(ChatColor.RED + "Buy at market for " + ChatColor.GOLD + "%sdollars%" + ChatColor.RED + " sand dollars!"),
    FRIEND_ONLINE(ChatColor.AQUA + "➕ %player% just connected! Click this message to invite them to a party."),
    CLOSE_INVENTORY(ChatColor.RED + "Close"),
    CLOSE_INVENTORY_LORE(ChatColor.GRAY + "Click to close this menu."),
    FRIEND_REQUEST_SENT(ChatColor.GREEN + "You sent friend request to " + ChatColor.AQUA + "%player%!"),
    FRIEND_REQUEST_RECEIVED(ChatColor.GOLD + "%player% sent you a friend request!"),
    FRIEND_REQUEST_ACCEPT(ChatColor.AQUA + "%player%" + ChatColor.GREEN + " is now your friend!"),
    FRIEND_REMOVED(ChatColor.YELLOW + "You removed %player% from your friends. Were they a bad friend?"),
    FRIEND_REMOVED_ME(ChatColor.YELLOW + "%player% removed you from their friends."),
    FRIEND_PENDING_FAILED_LOCATE(ChatColor.RED + "Could not locate a pending friend with that name. Please check it and try again."),
    FRIEND_REQUEST_DENIED(ChatColor.RED + "You denied " + ChatColor.AQUA + "%player%" + ChatColor.RED + "'s friend request!"),
    FRIEND_REQUEST_IGNORED(ChatColor.RED + "You silently ignored " + ChatColor.AQUA + "%player%" + ChatColor.RED + "'s friend request!"),
    FRIEND_REQUEST_DENIED_YOU(ChatColor.AQUA + "%player%" + ChatColor.RED + "denied your friend request! You can send another in 3 min."),
    PLAYER_ALREADY_FRIEND(ChatColor.RED + "That player is already your friend!"),
    APPLICABLE_COSMETIC(ChatColor.YELLOW + "Click to apply!"),
    FAILED_PLAYER_LOCATE(ChatColor.DARK_RED + "Could not locate player " + ChatColor.AQUA + "%player%" + ChatColor.DARK_RED + "."),
    FRIEND_REQUEST_TO_SELF(ChatColor.RED + "You cannot friend yourself!"),
    FRIEND_IGNORE_TO_SELF(ChatColor.RED + "You cannot ignore yourself!"),
    UNKNOWN_SUBCOMMAND(ChatColor.RED + "Could not identify the sub command " + ChatColor.AQUA + "%subcommand%" + ChatColor.RED + ". Please check the command and try again. :("),
    SUCCESSFUL_SET_SPAWN(ChatColor.GREEN + "Successfully set this lobbies spawn!"),
    PLAYER_IS_SELF(ChatColor.RED + "You can't %msg% yourself!"),
    COMMAND_COOLDOWN(ChatColor.RED + "Please wait %s%s before executing this command again!"),
    ACTION_COOLDOWN(ChatColor.RED + "Please wait %s%s before executing this action again!"),
    PLAYER_VISIBILITY_ENABLED(ChatColor.AQUA + "Player Visibility is now " + ChatColor.GREEN + "enabled" + ChatColor.AQUA + "."),
    PLAYER_VISIBILITY_DISABLED(ChatColor.AQUA + "Player Visibility is now " + ChatColor.RED + "disabled" + ChatColor.AQUA + "."),
    SENT_SAME_MESSAGE(ChatColor.RED + "You cannot send the same message twice!"),
    UNICODE("\uE021 | \uE087 | (Divider) \uE050 | (Confirm Image For Mail) \uE100 | (Level) \uE013"),

    NO_FRIENDS_ITEM_NAME("Get some friends... :("),
    NO_PENDING_FRIENDS_ITEM_NAME(ChatColor.RED + "No friend requests..."),
    FRIENDS_BOOK_NAME(ChatColor.RED + "Get some friends... :("),
    FRIEND_REQUEST_ACCEPT_NAME(ChatColor.GREEN + "Accept"),
    FRIEND_REQUEST_DENY_NAME(ChatColor.RED + "Deny"),
    FRIEND_REQUEST_IGNORE_NAME(ChatColor.GRAY + "Ignore"),
//    INFO_DIVIDER(ChatColor.translateAlternateColorCodes('&', "&l&f&m一一一一一&f|&8&m一一一一一"));
    INFO_DIVIDER(ChatColor.WHITE + "\uE014");


    private final String text;

    Translations(String string) {
        this.text = string;
    }
    public String getText() {
        return this.text;
    }

    /** Precondition: 0 ≤ x < 10, 0 < y ≤ 10, and len > 0.

     * Draws a square on a -by- -coordinate grid

     * and prints the square’s side length and area.

     * The upper left corner of the square will be located

     * at the coordinate (x, y) and the side length of the

     * square will be len (or as large as will fit in the grid).

     */

    public static void longestStreak(String str) {
        String string = str.toLowerCase();
        HashMap<Character, Integer> repeating = new HashMap<>();

        for (char c :  string.toCharArray()) {
            if (repeating.containsKey(c)) {
                repeating.replace(c, (repeating.get(c)+1));
                continue;
            }
            repeating.put(c, 1);
        }
        List<Map.Entry<Character, Integer> > list =
                new LinkedList<>(repeating.entrySet());

        list.sort(Map.Entry.comparingByValue());

        HashMap<Character, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        System.out.println(temp.keySet().toArray()[0] + " " + temp.values().toArray()[0]);
    }
}
