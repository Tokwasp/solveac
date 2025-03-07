package algorithm.baekjoon.dp;

import java.io.*;

public class LCS9252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for(int i = 1; i <= s1.length(); i++){
            for(int j = 1; j <= s2.length(); j++){
                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = dp[i-1][j - 1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        System.out.println(dp[s1.length()][s2.length()]);

        if(dp[s1.length()][s2.length()] != 0) {
            StringBuilder sb = new StringBuilder();
            int x = s1.length();
            int y = s2.length();

            while (x > 0 && y > 0) {
                if(dp[x][y] == dp[x-1][y]){
                    x--;
                } else if(dp[x][y] == dp[x][y-1]){
                    y--;
                } else{
                    sb.append(s1.charAt(x - 1));
                    x--;
                    y--;
                }
            }
            System.out.println(sb.reverse().toString());
        }
    }
}