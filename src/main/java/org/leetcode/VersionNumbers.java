package org.leetcode;

/**
 * Created by murali on 26-05-2015.
 */
public class VersionNumbers {

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int len1 = version1.length();
        int len2 = version2.length();
        int[] v1Nums = new int[len1];
        int[] v2Nums = new int[len2];
        int[] rest = null;

        int sign = 1;

        if (len1 < len2) {
            rest = v2Nums;
            sign = -1;
        } else if (len1 > len2) {
            rest = v1Nums;
        }

        for (int i = 0; i < v1.length; i++) {
            v1Nums[i] = Integer.parseInt(v1[i]);
        }
        for (int i = 0; i < v2.length; i++) {
            v2Nums[i] = Integer.parseInt(v2[i]);
        }

        for (int i = 0; i < Math.min(len1, len2); i++) {
            int diff = v1Nums[i] - v2Nums[i];
            if (diff < 0)
                return -1;
            else if (diff > 0)
                return 1;
        }

        if (rest == null)
            return 0;

        for (int i = Math.min(len1, len2); i < Math.max(len1, len2); i++) {
            if (rest[i] > 0)
                return sign;
        }
        return 0;
    }

    public static void main(String[] args) {
        VersionNumbers versionNumbers = new VersionNumbers();
//        System.out.println(versionNumbers.compareVersion("0.1", "1.1"));
//        System.out.println(versionNumbers.compareVersion("1.1", "0.1"));
//        System.out.println(versionNumbers.compareVersion("1.1", "1.2"));
//        System.out.println(versionNumbers.compareVersion("1.1", "1.1"));
//        System.out.println(versionNumbers.compareVersion("1.2", "1.1"));
//        System.out.println(versionNumbers.compareVersion("1", "2.1"));
        System.out.println(versionNumbers.compareVersion("10.6.5", "10.6"));
        System.out.println(versionNumbers.compareVersion("00001", "0001"));
        System.out.println(versionNumbers.compareVersion("1.0", "1"));
        System.out.println(versionNumbers.compareVersion("1.1", "1.10"));
    }
}
