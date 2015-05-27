package org.hackerrank;

import java.util.Scanner;

/**
 * Created by murali on 14-05-2015.
 */
public class TwosComplementFast {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int testCaseCount = scanner.nextInt();
        while(testCaseCount-- > 0) {
            System.out.println(findSolutionFast(scanner.nextLong(), scanner.nextLong()));
        }
    }

    public static long getBitCountForPositiveNumber(long n) {
        if (n <= 0) return 0;

        long count = 0;
        int bitCount = 0, mask = ~(-1>>>1) >>> 1,j = 30;

        while (mask > 0) {
            if ((n & mask) != 0) {
                count += cumOneCount[j] + (bitCount * (1<<j));
                bitCount++;
            }
            mask >>= 1;
            j--;
        }
        return count;
    }

    public static long getBitCountForNegativeNumber(long n) {
        if (n >= 0) return 0;
        if (n == -1) return 32;

        long count = getBitCountForPositiveNumber( -(n + 1));
        if (n == Integer.MIN_VALUE) {
            return (-(n + 1)  * 32L) + 32L - count;
        }
        return 32L * -n - count;
    }

    public static long findSolutionFast(long min, long max) {

        if (min == max && max == Integer.MIN_VALUE) return 1;

        int sign = min < 0 ? (max < 0 ? 1 : -1) : (max > 0 ? 1 : -1);
        long minBitCount = 0, maxBitCount = 0, count;

        if (sign == 1) {
            if (min < 0) {
                maxBitCount = getBitCountForNegativeNumber(min);
                minBitCount = getBitCountForNegativeNumber(max + sign);
            } else {
                maxBitCount = getBitCountForPositiveNumber(max);
                minBitCount = getBitCountForPositiveNumber(min - sign);
            }
            count = maxBitCount - minBitCount;
        } else {
            if (min < 0) {
                maxBitCount = getBitCountForPositiveNumber(max);
                minBitCount = getBitCountForNegativeNumber(min);
            }
            count = maxBitCount + minBitCount;
        }
        return count;
    }
    static long[] cumOneCount ={1,
                                2,
                                5,
                                13,
                                33,
                                81,
                                193,
                                449,
                                1025,
                                2305,
                                5121,
                                11265,
                                24577,
                                53249,
                                114689,
                                245761,
                                524289,
                                1114113,
                                2359297,
                                4980737,
                                10485761,
                                22020097,
                                46137345,
                                96468993,
                                201326593,
                                419430401,
                                872415233,
                                1811939329,
                                3758096385L,
                                7784628225L,
                                16106127361L
                                };
//    static {
//
//        cumOneCount[0] = 1;
//        cumOneCount[1] = 2;
//        cumOneCount[2] = 5;
//        cumOneCount[3] = 13;
//        cumOneCount[4] = 33;
//        cumOneCount[5] = 81;
//        cumOneCount[6] = 193;
//        cumOneCount[7] = 449;
//        cumOneCount[8] = 1025;
//        cumOneCount[9] = 2305;
//        cumOneCount[10] = 5121;
//        cumOneCount[11] = 11265;
//        cumOneCount[12] = 24577;
//        cumOneCount[13] = 53249;
//        cumOneCount[14] = 114689;
//        cumOneCount[15] = 245761;
//        cumOneCount[16] = 524289;
//        cumOneCount[17] = 1114113;
//        cumOneCount[18] = 2359297;
//        cumOneCount[19] = 4980737;
//        cumOneCount[20] = 10485761;
//        cumOneCount[21] = 22020097;
//        cumOneCount[22] = 46137345;
//        cumOneCount[23] = 96468993;
//        cumOneCount[24] = 201326593;
//        cumOneCount[25] = 419430401;
//        cumOneCount[26] = 872415233;
//        cumOneCount[27] = 1811939329;
//        cumOneCount[28] = 3758096385L;
//        cumOneCount[29] = 7784628225L;
//        cumOneCount[30] = 16106127361L;
//    }
}
