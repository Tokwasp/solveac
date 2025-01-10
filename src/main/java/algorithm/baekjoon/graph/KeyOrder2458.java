package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class KeyOrder2458 {
    static final int INF = 501;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dist = new int[N + 1][N + 1];

        // 그래프 초기화
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                dist[i][j] = INF;
            }
            dist[i][i] = 0;
        }

        // 연결된 키 입력 받기
        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            dist[start][end] = 1;
        }

        // 플로이드
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(i == j) continue;
                    if(dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

       int count = 0;
        for(int i = 1; i <= N; i++){
            int pass = 1;
            for(int j = 1; j <= N; j++){
                if(dist[i][j] == INF && dist[j][i] == INF){
                    pass = 0;
                    break;
                }
            }
            count += pass;
        }
        System.out.println(count);
    }
}