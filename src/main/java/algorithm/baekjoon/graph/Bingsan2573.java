package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Bingsan2573 {
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    static List<int[]> meltList;
    static int row, col;
    static int year = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][];

        // 맵 입력 받기
        for(int i = 0; i < row; i++){
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        while(true){
            visited = new boolean[row][col];

            int bingsanCount = findBingsan();
            if(isZeroBingsanCount(bingsanCount)){
                year = 0;
                break;
            }
            if(isTwoBingsanCount(bingsanCount)){
                break;
            }
            year++;
        }
        System.out.println(year);
    }

    static int findBingsan(){
        int bingsanCount = 0;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(!visited[i][j] && map[i][j] != 0){
                    bfs(i,j);
                    bingsanCount++;
                    melt();
                }
            }
        }
        return bingsanCount;
    }

    static void bfs(int startRow, int startCol){
        Queue<Bingsan> queue = new LinkedList<>();
        Bingsan firstBingsan = new Bingsan(startRow, startCol);
        queue.add(firstBingsan);
        visited[startRow][startCol] = true;

        meltList = new ArrayList<>();
        while(!queue.isEmpty()) {
            Bingsan bingsan = queue.poll();

            int melt = 0;
            for (int i = 0; i < 4; i++) {
                int nextRow = bingsan.row + dx[i];
                int nextCol = bingsan.col + dy[i];

                if(isMapIn(nextRow, nextCol)){
                    if(!visited[nextRow][nextCol] && map[nextRow][nextCol] != 0) {
                        Bingsan nextBingsan = new Bingsan(nextRow, nextCol);
                        queue.add(nextBingsan);
                        visited[nextRow][nextCol] = true;
                    }

                    if(map[nextRow][nextCol] == 0){
                        melt++;
                    }
                }
            }

            if(melt > 0){
                meltList.add(new int[] {bingsan.row, bingsan.col, melt});
            }
        }
    }

    private static void melt() {
        for(int i = 0; i < meltList.size(); i++){
            int[] melt = meltList.get(i);
            int row = melt[0];
            int col = melt[1];
            int meltCount = melt[2];

            map[row][col] -= meltCount;

            if(map[row][col] < 0){
                map[row][col] = 0;
            }
        }
    }

    static boolean isMapIn(int nextRow, int nextCol){
        return 0 <= nextRow && nextRow <= row -1 && 0 <= nextCol && nextCol <= col -1;
    }

    private static boolean isTwoBingsanCount(int bingsanCount) {
        return bingsanCount >= 2;
    }

    private static boolean isZeroBingsanCount(int bingsanCount) {
        return bingsanCount == 0;
    }

    static class Bingsan{
        private int row;
        private int col;

        public Bingsan(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}