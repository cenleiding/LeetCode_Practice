package com.stack;

/**
 * @Description: 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * @Author CLD
 * @Date 2019/4/9 21:56
 */
public class numTrees {

    public static void main(String[] args){
        int n=3;
        int[] dp=new int[n+1];
        dp[0]=1;
        for(int i=1;i<=n;i++){
            int sum=0;
            for(int j=0;j<i;j++){
                sum+= dp[j]*dp[i-j-1];
            }
            dp[i]=sum;
        }
        System.out.println(dp[n]);
    }
}
