package algorithm.baekjoon.string;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class CombineWord9177 {
    static char[] c1,c2, out;
    static StringBuilder sb;
    static boolean[][] check;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        sb = new StringBuilder();
        int idx = 0;

        while(idx++ < n) {
            String[] str = br.readLine().split(" ");
            c1 = str[0].toCharArray();
            c2 = str[1].toCharArray();
            out = str[2].toCharArray();

            bfs(idx);
        }
        System.out.println(sb);
    }

    static void bfs(int idx) {
        Queue<int[]> q = new LinkedList<>();
        check = new boolean[201][201];
        q.add(new int[] {0,0,0});
        check[0][0] = true;

        while(!q.isEmpty()) {
            int idx1 = q.peek()[0];
            int idx2 = q.peek()[1];
            int outIdx = q.peek()[2];
            q.poll();

            if(outIdx == out.length) {
                sb.append("Data set " + idx+": yes").append("\n");
                return;
            }

            if(idx1 < c1.length && !check[idx1+1][idx2] && c1[idx1] == out[outIdx]) {
                q.add(new int[] {idx1+1, idx2, outIdx+1});
                check[idx1+1][idx2] = true;
            }

            if(idx2 < c2.length && !check[idx1][idx2+1] && c2[idx2] == out[outIdx]) {
                q.add(new int[] {idx1, idx2+1, outIdx+1});
                check[idx1][idx2+1] = true;
            }
        }
        sb.append("Data set " + idx+": no").append("\n");
    }
}