package algorithm.baekjoon.implement;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class CompetitionContagion18405 {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int[][] map;
    private static int K;
    private static List<Queue<int[]>> contagionCoordinate;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nAndK = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = nAndK[0];
        K = nAndK[1];
        map = new int[N][N];
        contagionCoordinate = new ArrayList<>();

        // virus 수 만큼 List 생성
        for (int i = 0; i <= K; i++) {
            contagionCoordinate.add(new LinkedList<>());
        }

        // 맵 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                contagionCoordinate.get(map[i][j]).add(new int[]{i, j});
            }
        }

        // 결과값 input 입력
        int[] targetInput = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int targetSecond = targetInput[0];
        int targetRow = targetInput[1];
        int targetCol = targetInput[2];

        int second = 0;
        while (second != targetSecond) {
            findDotAndBFS(contagionCoordinate);
            second++;
        }

        System.out.println(map[targetRow-1][targetCol-1]);
    }

    private static void findDotAndBFS(List<Queue<int[]>> contagionCoordinate) {
        for (int i = 1; i <= K; i++) {
            Queue<int[]> coordinateQ = contagionCoordinate.get(i);
            int repeatNum = coordinateQ.size();

            for (int j = 0; j < repeatNum; j++) {
                int[] poll = coordinateQ.poll();
                int x = poll[0];
                int y = poll[1];
                bfs(x, y, i, coordinateQ);
            }
        }
    }


    private static void bfs(int x, int y, int coordinateNum, Queue<int[]> coordinateQ) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if(isMapIn(nextX, nextY) && map[nextX][nextY] == 0){
                map[nextX][nextY] = coordinateNum;
                coordinateQ.add(new int[] {nextX, nextY});
            }
        }
    }

    private static boolean isMapIn(int nextX, int nextY) {
        return 0 <= nextX && nextX <= map.length -1 && 0 <= nextY && nextY <= map[0].length -1;
    }
}