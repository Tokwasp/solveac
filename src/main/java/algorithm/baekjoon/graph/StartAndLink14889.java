package algorithm.baekjoon.graph;

import java.io.*;
import java.util.stream.Stream;

public class StartAndLink14889 {
    static int[][] map;
    static boolean[] visited;
    static int N;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N];

        //map 데이터 넣기
        for(int i = 0; i < N; i++){
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dfs(0,0);

        System.out.println(min);
    }
    static void dfs(int count , int index){
        if(count == N/2){

            int team1 = 0;
            int team2 = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(visited[i] && visited[j]){
                        team1 += map[i][j];
                    }
                    else if(!visited[i] && !visited[j]){
                        team2 += map[i][j];
                    }
                }
            }
            int differ = Math.abs(team1- team2);
            min = Math.min(min,differ);
            return;
        }

        for (int i = index; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(count + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}
