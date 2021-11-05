// This class is created by LookIP
package de.proxycord.cwbw.handlers;

import java.util.ArrayList;
import java.util.List;

public class SubStringHandler {

    private static String[] colorcodes = { "§a", "§b", "§c", "§d", "§e", "§f", "§m", "§o", "§k", "§0", "§1", "§2", "§3", "§4", "§5", "§6", "§7", "§8", "§9" };

    public static String[] getColorCodesList() {
        return colorcodes.clone();
    }

    public static String[] getColorCodesFireEntry() {
        return colorcodes;
    }

    public static String splitterator(final String input, final int end) {
        String output = "";
        final String[] splt = input.split("");

        for (int i = 0; i < end; i++) {
            if(i >= splt.length) {
                break;
            }

            output += splt[i];
        }

        return output;
    }

    public static String subString(final String input, final int begin, final int end) {
        String output = "";
        final String[] splt = input.split("");

        for(int i = begin; i < end; i++) {
            if(i >= splt.length) {
                break;
            }

            output += splt[i];
        }

        return output;
    }

    public static String getLastColorCode(final String input) {
        final String[] splt = input.split("");

        for(int i = splt.length - 1; i >= 0; i--) {
            if(i - 1 >= 0) {
                String code = splt[i - 1] + splt[i];

                if(containsColorCode(code) && !code.trim().equals("")) {
                    return code;
                }
            }
        }

        return "";
    }

    public static boolean containsColorCode(final String input) {
        for(final String code : colorcodes) {
            if(input.contains(code)) {
                return true;
            }
        }

        return false;
    }

    public static String replaceColorCodes(String input) {
        String output = input;

        for(final String colorcode : colorcodes) {
            output = output.replaceAll(colorcode, "");
        }

        return output;
    }

    public static String listSplitter(final List<String> input, final String splitkey) {
        String output = "";

        for(int i = 0; i < input.size(); i++) {
            output += output.equals("") ? input.get(i) : splitkey + input.get(i);
        }

        return output;
    }

    public static boolean isMinus(final double key) {
        return (key + "").startsWith("-");
    }
}
