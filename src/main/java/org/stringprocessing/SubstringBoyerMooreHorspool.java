package org.stringprocessing;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by murali on 26-04-2015.
 */
public class SubstringBoyerMooreHorspool {

    private Map<Character, Integer> shiftValueTable = new HashMap<>();
    private int patternLength;
    private int textLength;

    public static void main(String[] args) {
        SubstringBoyerMooreHorspool substringBoyerMooreHorspool = new SubstringBoyerMooreHorspool();
        System.out.println(substringBoyerMooreHorspool.indexOf("abcdef","def") + "," + "abcdef".indexOf("def"));
        System.out.println(substringBoyerMooreHorspool.indexOf("computer","muter") + "," + "computer".indexOf("muter"));
        System.out.println(substringBoyerMooreHorspool.indexOf("stringmatchingmat","ingmat") + "," + "stringmatchingmat".indexOf("ingmat"));
        System.out.println(substringBoyerMooreHorspool.indexOf("videobox","videobox") + "," + "videobox".indexOf("videobox"));
    }

    private int getShiftValue(Character c) {
        Integer val = shiftValueTable.get(c);
        return val == null ? patternLength : val;
    }

    private void preProcess(String pattern) {
        for (int i = 0; i < pattern.length(); i++) {
            shiftValueTable.put(pattern.charAt(i), Math.max(1, this.patternLength - 1 - i));
        }
    }

    public int indexOf (String text, String pattern) {
        return indexOf(text,pattern,0);
    }

    public int indexOf(String text, String pattern, int beginAt) {

        this.textLength = text.length();
        this.patternLength = pattern.length();
        int i = beginAt;
        boolean matchFound;

        preProcess(pattern);
        while (i <= this.textLength - this.patternLength) {

            matchFound = true;
            for (int j = this.patternLength - 1; j >= 0; j--) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    matchFound = false;
                    break;
                }
            }
            if (matchFound) {
                return i;
            } else {
                i = i + getShiftValue(text.charAt(i + this.patternLength - 1));
            }
        }
        return -1;
    }
}
