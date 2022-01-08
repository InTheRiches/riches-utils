package net.riches.common_utils;

import net.riches.common_utils.distance.DistanceUtil;
import net.riches.common_utils.distance.MeasuringUnit;

public class NumberUtil {

    public static void main(String[] args) {
        System.out.println(DistanceUtil.distanceBetween(21, 30, 3, 902, -833, 200));
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
        for (int i = str.length() -1; i > - 1; i--) {
            reversed.append(str.toCharArray()[i]);
        }
        return reversed.toString();
    }
}
