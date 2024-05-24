package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/*
    문제 풀이 :

    풀이 방법 : bfs

    토마토 Map을 돌며, 익은 토마토를 Q에 넣습니다. (1일차에 익은 토마토를 동시에 체크 하기 위해)
    bfs() 를 실행 하여 토마토를 모두 익힙니다.
    dayCheck 배열에 진행한 날짜가 담기게 되고 -> int day 에 담습니다.
    또한 방문시 visitedCheck 배열에 체크 합니다.

    visitedCheck 배열에 체크 안 된 곳은 두가지 입니다.
    1. 토마토 맵 "-1" 인 경우
    2. 진짜로 방문 안한 경우

    visitedCheck 배열에 방문 하지 않고, 토마토 맵이 -1이 아니면, -1을 출력 합니다.
    다 방문 할 경우 날짜를 출력 합니다.
 */
public class Tomato7576 {
    static int[][] tomatoMap;
    static int[][] dayCheck;
    static boolean[][] visitedCheck;
    static Queue<int[]> q;
    static int day = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        int M  = Integer.parseInt(st.nextToken()); int N = Integer.parseInt(st.nextToken());

        // dayCheck = 날짜 체크
        tomatoMap = new int[N][];
        dayCheck = new int[N][M];

        // 열의 길이를 비워둔 동적 할당
        for(int i = 0; i < tomatoMap.length; i++){
            tomatoMap[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 날짜 체크를 할때 0 부터 채워 나가 완성 시 (시작 인덱스, map "-1"부분, 접근 하지 못한 곳)이 모두 0이여서
        // 방문 하지 못한 곳을 체크 하기 위해 visitedCheck[][]
        q = new LinkedList<>();
        visitedCheck = new boolean[N][M];

        for(int i = 0; i < tomatoMap.length; i++){
            for(int j = 0; j < tomatoMap[0].length; j++){
                if(tomatoMap[i][j] == 1){
                    q.add(new int[] {i,j});
                    visitedCheck[i][j] = true;
                }
            }
        }

        bfs();

        if(!check()) sb.append("-1").append("\n");
        else{
            sb.append(day).append("\n");
        }
        System.out.print(sb);
    }

    static void bfs(){
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while(!q.isEmpty()){

            int[] pollXY = q.poll();
            int prevX = pollXY[0];
            int prevY = pollXY[1];

            for (int i = 0; i < 4; i++) {
                int x = prevX + dx[i];
                int y = prevY + dy[i];

                //오류 체크
                boolean error = x < 0 || x > (tomatoMap.length - 1) ||
                        y < 0 || y > (tomatoMap[0].length - 1);

                // !오류 && 방문 안한 곳 && 토마토 맵 == 0 인 경우
                if (!error && !visitedCheck[x][y] && tomatoMap[x][y] == 0) {
                    dayCheck[x][y] = dayCheck[prevX][prevY] + 1;
                    day = dayCheck[x][y];
                    visitedCheck[x][y] = true;
                    q.add(new int[] {x,y});
                }

            }
        }
    }

    static boolean check() {
        for(int i = 0; i < visitedCheck.length; i++){
            for(int j = 0; j < visitedCheck[0].length; j++){
                if(!visitedCheck[i][j] && tomatoMap[i][j] != -1) return false;
            }
        }
        return true;
    }
}
