package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class Exercise1956 {
    static final int INF = 170000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] connect = new int[V + 1][V + 1];
        for(int i = 1; i <= V; i++){
            Arrays.fill(connect[i], INF);
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            connect[start][end] = weight;
        }

        for(int N = 1; N <= V; N++){
            for(int i = 1; i <= V; i++){
                for(int j = 1; j <= V; j++){
                    if(connect[i][j] > connect[i][N] + connect[N][j]){
                        connect[i][j] = connect[i][N] + connect[N][j];
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= V; i++){
            for(int j = 1; j <= V; j++){
                int startValue = connect[i][j];
                int endValue = connect[j][i];
                if(startValue != INF && endValue != INF){
                    min = Math.min(startValue + endValue, min);
                }
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
}