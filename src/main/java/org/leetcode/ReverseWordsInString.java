package org.leetcode;

/**
 * Created by Murali M on 27/4/15.
 */
public class ReverseWordsInString {

    public static void main(String[] args) {
        //System.out.println(reverseWords("the sky is blue"));
        System.out.println(":" + reverseWords("   ")  + ":");
        System.out.println(":" + reverseWords(" 1")  + ":");
    }

    public static String reverseWords(String s) {

        if (s.length() <= 0)
            return s;

        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder(s.length());
        String delimiter = "";

        for (int i = words.length - 1; i >=0; i--) {
            if (words[i].trim().length() >0) {
                sb.append(delimiter + words[i].trim());//sb.append(" ");
            }
            delimiter = " ";
        }
        return sb.toString();
    }
}
