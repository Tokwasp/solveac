package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PresidentDraw2660WithFloyd {
    static final int INF = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N+1][N+1];

        //init Array
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++) {
                graph[i][j] = INF;
            }
        }
        for(int i = 1; i <= N; i++) graph[i][i] = 0;

        // input
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if(start == -1 && end == -1) break;
            graph[start][end] = 1;
            graph[end][start] = 1;
        }

        //Floyd
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(graph[i][j] > graph[i][k] + graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        List<int[]> scoreList = new ArrayList<>();
        for(int i = 1; i<= N; i++){
            int max = Arrays.stream(graph[i]).max().orElse(Integer.MAX_VALUE);
            scoreList.add(new int[] {i,max});
        }
        scoreList.sort(Comparator.comparingInt(a -> a[1]));
        List<int[]> result = scoreList.stream()
                .filter(a -> scoreList.get(0)[1] == a[1])
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append(scoreList.get(0)[1]).append(" ").append(result.size()).append("\n");
        for (int[] ints : result) {
            sb.append(ints[0]).append(" ");
        }
        System.out.println(sb);
    }
}