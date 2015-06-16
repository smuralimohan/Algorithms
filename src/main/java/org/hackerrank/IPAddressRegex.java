package org.hackerrank;

import java.util.Scanner;

/**
 * Created by murali on 15-06-2015.
 */
public class IPAddressRegex {
    public static void main(String []argh)
    {
        Scanner in = new Scanner(System.in);
        while(in.hasNext())
        {
            String IP = in.next();
            //System.out.println(IP + "vv");
           // System.out.flush();
            System.out.println(IP.matches(new myRegex().pattern));
        }
    }
}

class myRegex {
    String pattern = "((2[0-5][0-5])|([0-1]\\d{2})|(\\d{1,2}))(\\.((2[0-5][0-5])|([0-1]\\d{2})|(\\d{1,2}))){3}";
}