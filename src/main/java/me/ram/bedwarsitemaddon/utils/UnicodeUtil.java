package me.ram.bedwarsitemaddon.utils;

import java.util.regex.Pattern;

public final class UnicodeUtil {

    private static final String singlePattern = "[0-9|a-f|A-F]";
    private static final String pattern = singlePattern + singlePattern + singlePattern + singlePattern;

    private static String ustartToCn(final String str) {
        String sb = "0x" + str.substring(2, 6);
        Integer codeInteger = Integer.decode(sb);
        int code = codeInteger.intValue();
        char c = (char) code;
        return String.valueOf(c);
    }

    private static boolean isStartWithUnicode(final String str) {
        if (null == str || str.length() == 0) {
            return false;
        }
        if (!str.startsWith("\\u")) {
            return false;
        }
        if (str.length() < 6) {
            return false;
        }
        String content = str.substring(2, 6);
        return Pattern.matches(pattern, content);
    }

    public static String unicodeToCn(final String str) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; ) {
            String tmpStr = str.substring(i);
            if (isStartWithUnicode(tmpStr)) {
                sb.append(ustartToCn(tmpStr));
                i += 6;
            } else {
                sb.append(str.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
}