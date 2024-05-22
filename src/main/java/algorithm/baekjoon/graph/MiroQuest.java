package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class MiroQuest {
    static int[][] arr;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken());

        arr = new int[N][];
        visited = new int[N][M];

        for(int i = 0; i < N; i++){
            arr[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        visited[0][0] = 1;
        bfs(0,0);

        System.out.println(visited[N-1][M-1]);
    }

    static void bfs(int StartX, int StartY){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {StartX,StartY});

        while(!q.isEmpty()) {

            int[] pollNum = q.poll();
            int pollX = pollNum[0]; int pollY = pollNum[1];

            int[] dx = {1, 0, 0, -1};
            int[] dy = {0, 1, -1, 0};

            for (int i = 0; i < 4; i++) {
                int x = pollX + dx[i];
                int y = pollY + dy[i];

                boolean error = x < 0 || y < 0 || x > (arr.length - 1) || y > (arr[0].length - 1);

                if (!error && visited[x][y] == 0 && arr[x][y] == 1) {
                    q.add(new int[] {x,y});
                    visited[x][y] = visited[pollX][pollY] + 1;
                }
            }
        }
    }
}
