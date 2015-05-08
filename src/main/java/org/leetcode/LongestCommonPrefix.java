package org.leetcode;

import java.util.Arrays;

/**
 * Created by murali on 08-05-2015.
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        System.out.println(longestCommonPrefix.longestCommonPrefix(new String[] {"abc", "abd"}));
        System.out.println(longestCommonPrefix.longestCommonPrefix(new String[] {"abc", ""}));
        System.out.println(longestCommonPrefix.longestCommonPrefix(new String[] {"abc", "dac"}));
        System.out.println(longestCommonPrefix.longestCommonPrefix(new String[] {"abc", "a"}));
        System.out.println(longestCommonPrefix.longestCommonPrefix(new String[] {}));
    }
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";

        String prefix = "";
        int minStrLen = Integer.MAX_VALUE;

        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minStrLen)
                minStrLen = strs[i].length();
        }

        if (minStrLen > 0 ) {
            for (int i = 0; i < minStrLen; i++) {
                char c = strs[0].charAt(i);
                for (int j = 1; j < strs.length; j++) {
                    if (strs[j].charAt(i) != c) {
                        return prefix;
                    }
                }
                prefix += c;
            }
        }
        return prefix;
    }
}
