package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class MovePerson16234 {
    static boolean pass = true;
    static int N = 0, R = 0, L = 0;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        // 맵 입력 받기
        for(int i = 0; i < N; i++){
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int day = 0;
        while(pass){
            // 시그널, 방문 초기화
            pass = false;
            visited = new boolean[N][N];

            checkNotVisitedCountry();
            if(pass) {
                day++;
            }
        }
        System.out.print(day);
    }

    static void checkNotVisitedCountry(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]){
                    boolean isMoved = movePeople(i, j);
                    if(isMoved) {
                        pass = true;
                    }
                }
            }
        }
    }

    static boolean movePeople(int row, int col){
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        Queue<int[]> queue = new LinkedList<>();
        List<int[]> unionList = new ArrayList<>();
        queue.add(new int[] {row, col});
        unionList.add(new int[] {row, col});
        visited[row][col] = true;

        int totalPeopleCount = map[row][col];

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int i = 0; i < 4; i++){
                int nextRow = cur[0] + dx[i];
                int nextCol = cur[1] + dy[i];

                if(!isMapIn(nextRow, nextCol)) continue;
                int differ = Math.abs(map[cur[0]][cur[1]] - map[nextRow][nextCol]);

                if(!visited[nextRow][nextCol] && R <= differ && differ <= L){
                    queue.add(new int[] {nextRow, nextCol});
                    unionList.add(new int[] {nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                    totalPeopleCount += map[nextRow][nextCol];
                }
            }
        }

        if(unionList.size() == 1){
            return false;
        }
        else{
            int divideCount = totalPeopleCount / unionList.size();
            for(int[] ints : unionList){
                map[ints[0]][ints[1]] = divideCount;
            }
        }
        return true;
    }

    static boolean isMapIn(int row, int col){
        return 0 <= row && row < N && 0 <= col && col < N;
    }
}