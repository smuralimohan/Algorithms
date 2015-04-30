package org.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murali M on 29/4/15.
 */
public class ValidNumber {
    public static void main(String[] args) {
        ValidNumber validNumber = new ValidNumber();
//        System.out.println(validNumber.isNumber("e7"));
//        System.out.println(validNumber.isNumber(".1"));
//        System.out.println(validNumber.isNumber("3."));
//        System.out.println(validNumber.isNumber(".1."));
//        System.out.println(validNumber.isNumber("3.5e+3.5e+3.5"));
        System.out.println(validNumber.isNumber(".0e"));
    }

    private int getCharIndex(char c) {
        int index = 0;

        switch (c) {
            case '-' : index = 0; break;
            case '+' : index = 1; break;
            case '.' : index = 3; break;
            case 'e' : index = 4; break;
            default: index = Character.isDigit(c) ? 2 : 5;
        }
        return index;
    }

    public boolean isNumber(String s) {

        int currentState = 0;
        int[] finalStates = new int[] {2,3,7,8,11};

        int[][] dfa = new int[][]
                {{1, 12,12,12, 5, 12,12,12, 12, 12,12,12,12},
                 {1, 12,12,12, 5, 12,12,12, 12, 12,12,12,12},
                 {2, 2, 2, 3,  8, 8, 7, 7,  8,  7,11,11,12},
                 {10,10,3, 12, 12, 6, 12,12, 12,  12,12,12,12},
                 {12,12,4, 4,  12,12,12,12, 12, 12,12, 4,12},
                 {12,12,12,12, 12,12,12,12, 12, 12,12,12,12}
                };

        s = s.trim();
        if (s.length() <= 0) return false;

        for (char c: s.toCharArray()) {
            int charIndex = getCharIndex(c);
            currentState = dfa[charIndex][currentState];
            if (currentState == 12) return false;
        }
        for (int i = 0; i < finalStates.length; i++) {
            if (currentState == finalStates[i])
                return true;
        }
        return false;
    }
}
