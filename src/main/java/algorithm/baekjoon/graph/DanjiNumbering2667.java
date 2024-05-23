package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class DanjiNumbering2667 {
    static int[][] map;
    static int[][] visited;
    static int numbering = 1;
    static int numberingCount = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        map = new int[N][];
        visited = new int[N][N];
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < map.length; i++){
            map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] == 1 && visited[i][j] == 0) {
                    dfs(i, j, numbering);
                    resultAddAndInit(result);
                }
            }
        }

        sb.append(result.size()).append("\n");
        result.sort(Comparator.naturalOrder());
        for (int num : result) {
            sb.append(num).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int row, int col, int number){

        visited[row][col] = number;

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};


        for(int i = 0; i < 4; i++){
            int x = row + dx[i];
            int y = col + dy[i];

            boolean error = x < 0 || x > (map.length - 1) || y < 0 || y > (map[0].length - 1);

            if(!error && map[x][y] == 1 && visited[x][y] == 0){
                dfs(x, y, number);
                numberingCount++;
            }
        }
    }

    private static void resultAddAndInit(List<Integer> result) {
        numbering++;
        result.add(numberingCount);
        numberingCount = 1;
    }
}
