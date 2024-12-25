package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class Traveling1976 {
    static final int INF = 2000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] graph = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++){
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= N; j++){
                int connect = Integer.parseInt(st.nextToken());
                if (connect == 1) {
                    graph[i][j] = 1;
                    graph[j][i] = 1;
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= M; i++){
            q.add(Integer.parseInt(st.nextToken()));
        }

        for(int n = 1; n <= N; n++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(graph[i][j] > graph[i][n] + graph[n][j]){
                        graph[i][j] = graph[i][n] + graph[n][j];
                    }
                }
            }
        }

        boolean pass = true;
        if(q.isEmpty()){
            System.out.println("YES");
            return;
        }
        int start = q.poll();

        while(!q.isEmpty()){
            int next = q.poll();
            if(graph[start][next] != INF){
                start = next;
            }
            else{
                pass = false;
                break;
            }
        }
        System.out.println(pass ? "YES" : "NO");
    }
}