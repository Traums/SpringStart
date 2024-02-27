package ru.mart.Practice.Sobes;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Sobes {
    ArrayList<Integer> mas = new ArrayList<Integer>();
    public static int lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();


        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
    public static void testPerformance(Runnable r1){
        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        r1.run();
        endTime = System.currentTimeMillis();
        System.out.printf("Time spend: %d ",(endTime-startTime));
    }
    public static void main(String[] args) {
        String s1 = "ABCD";
        String s2 = "ACBAD";
        System.out.println("Length of Longest Common Subsequence: " + lcs(s1, s2));
    }

//    public static void main(String[] args) {
//        int n;
//
//        BigInteger n1 = BigInteger.ONE;
//        BigInteger n2 = BigInteger.ONE;
//
//        try{
//            n = Integer.parseInt(args[0]);
//        }
//        catch (Exception e){
//            n = 340;//1_000_000;
//        }
//
//        long t1 = System.nanoTime();
//
//        if (n > 2)
//        {
//            BigInteger n3;
//            for (int i = 3; i <= n; ++i)
//            {
//                n3 = n1.add(n2);
//                n1 = n2;
//                n2 = n3;
//            }
//        }
//
//        System.out.println(n2);
//        System.out.println(n);
//        System.out.println("Время выполнения:" + (System.nanoTime() - t1)/1e6);
//
//    }
}
