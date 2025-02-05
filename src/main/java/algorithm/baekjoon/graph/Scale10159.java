package algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Scale10159 {
    static final int INF = 101;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] dist = new int[N + 1][N + 1];

        // 플로이드 초기화
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                dist[i][j] = INF;
            }
            dist[i][i] = 0;
        }

        // 입력
        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            dist[end][start] = 1;
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

        //출력 값
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            int count = 1;
            for(int j = 1; j <= N; j++){
                if(i == j) continue;
                if(dist[i][j] == INF && dist[j][i] == INF) continue;
                count++;
            }
            sb.append(N - count).append("\n");
        }
        System.out.print(sb);
    }
}