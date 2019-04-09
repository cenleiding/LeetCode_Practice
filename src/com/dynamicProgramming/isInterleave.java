package com.dynamicProgramming;

/**
 * @Description: 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * @Author CLD
 * @Date 2019/4/9 22:07
 */
public class isInterleave {

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "a";
        if(s1.length()+s2.length()!=s3.length()) {
            System.out.println(false);
        }
        int[][] dp=new int[s1.length()+1][s2.length()+1];
        dp[0][0]=1;
        for(int i=0;i<s1.length();i++)
            if(s1.charAt(i)==s3.charAt(i))
                dp[i+1][0]= dp[i][0];
        for(int i=0;i<s2.length();i++)
            if(s2.charAt(i)==s3.charAt(i))
                dp[0][i+1]=dp[0][i];
        for(int i=1;i<s1.length()+1;i++){
            for (int j=1;j<s2.length()+1;j++){
                if(s1.charAt(i-1)==s3.charAt(i+j-1) && dp[i-1][j]==1)
                    dp[i][j]= 1;
                if(s2.charAt(j-1)==s3.charAt(i+j-1) && dp[i][j-1]==1)
                    dp[i][j]= 1;
            }
        }
        if(dp[s1.length()][s2.length()]==1) System.out.println(true);
        else System.out.println(false);

    }
}
