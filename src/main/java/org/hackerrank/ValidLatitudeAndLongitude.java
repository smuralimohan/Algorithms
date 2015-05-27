package org.hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by murali on 17-05-2015.
 */
public class ValidLatitudeAndLongitude {

//    static BufferedReader br;
//    static PrintWriter out;
//    static String INPUT = "";
//
//    static void solve() throws Exception
//    {
//        Pattern p = Pattern.compile("^\\([+-]?((90(\\.0+)?)|((9|[1-8]\\d)(\\.\\d+)?)), [+-]?((180(\\.0+)?)|(([1-9]\\d?|1[0-7]\\d)(\\.\\d+)?))\\)$");
//        int n = Integer.parseInt(br.readLine());
//        for(int i = 0;i < n;i++){
//            String line = br.readLine();
//            if(p.matcher(line).matches()){
//                out.println("Valid");
//            }else{
//                out.println("Invalid");
//            }
//        }
//    }
//
//    public static void main(String[] args) throws Exception
//    {
//        long S = System.currentTimeMillis();
//        br = new BufferedReader(INPUT.isEmpty() ? new InputStreamReader(System.in) : new StringReader(INPUT));
//        out = new PrintWriter(System.out);
//
//        solve();
//        out.flush();
//        long G = System.currentTimeMillis();
//        tr(G-S+"ms");
//    }
//
//    static void tr(Object... o) { if(INPUT.length() != 0)System.out.println(Arrays.deepToString(o)); }

    public static void main (String[] args) throws java.lang.Exception
    {
        String[] test = new String[]{
                 "(-7, -177)",
                 "(-7.271374, -177.271374)",
                  "(-7, -14)",
               "(-7.799195, -14.799195)",
              "(-66.435670, -43.435670)",
                "(75, 180)",
                "(+90.0, -147.45)",
                "(77.11112223331, 149.99999999)",
                "(+90, +180)",
                "(90, 180)",
                "(-90.00000, -180.0000)",
                "(75, 280)",
                "(+190.0, -147.45)",
                "(77.11112223331, 249.99999999)",
                "(+90, +180.2)",
                "(90., 180.)",
                "(-090.00000, -180.0000)"
        };
        Pattern t = Pattern.compile("\\(\\d*\\)$");
        Pattern p = Pattern.compile("^\\([+-]?((90(\\.0+)?)|((9|[1-8]\\d?)(\\.\\d+)?)), [+-]?((180(\\.0+)?)|(([1-9]\\d?|1[0-7]\\d)(\\.\\d+)?))\\)$");
        for (String s: test)
            System.out.println(p.matcher(s).matches());
    }
}
