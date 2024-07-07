package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Watch15683 {
    static int[][] map;
    static int[] north = {-1,0}; static int[] east = {0,1}; static int[] south = {1,0}; static int[] west = {0,-1};
    // 좌표 방향 전환을 위한 배열
    static int[][] direction = {north,east,south,west};
    // CCTV 타입 별로 가리 키는 방향
    static int[][] CCTVDirection = {{},{0}, {1,3}, {0,1}, {0,1,3}, {0,1,2,3}};

    // 방향 조합을 찾기 위한 List
    static List<Integer> myDirectionList = new ArrayList<>();
    // map CCTV List
    static List<CCTV> CCTVList = new ArrayList<>();
    static int N, M, CCTVCount;
    static int min = Integer.MAX_VALUE;

    static class CCTV{
        int row;
        int col;
        int type;

        public CCTV(int row, int col, int type) {
            this.row = row;
            this.col = col;
            this.type = type;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NM = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = NM[0]; M = NM[1]; CCTVCount = 0; map = new int[N][M];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 5) {
                    CCTVList.add(new CCTV(i, j, map[i][j]));
                    CCTVCount++;
                }
            }
        }

        //각 CCTV 방향 조합을 만들고 (1번 : 90도, 2번 180도...) CCTV 바로 보는 방향에 "#" 체크 하기 위한 배열
        int[][] copyMap = initCopyMap(N, M);
        combination(0);

        System.out.println(min);
    }

    // 각 CCTV 방향 조합을 만드는 함수 (0 : 90도 1: 180도 2: 270도 3: 360도)
    static void combination(int count){

        // 탈출 조건 CCTV 개수 만큼 방향을 만든 경우
        if(count == CCTVCount){
            min = Math.min(arrayFill(initCopyMap(N,M)),min);
            return;
        }

        for(int i = 0; i < 4; i++){
            myDirectionList.add(i);
            combination(count + 1);
            myDirectionList.remove(myDirectionList.size()-1);
        }
    }

    // 각 CCTV 에 방향 전환을 하고 해당 방향 으로 copyMap 에 -1 표기
    static int arrayFill(int[][] copyMap){
        for(int i = 0; i < CCTVList.size(); i++){
            CCTV cctv = CCTVList.get(i);
            int type = cctv.type;

            // CCTV 타입
            int d = myDirectionList.get(i);

            // CCTV 가리 키는 방향이 나온다. 타입 3 : 북,동,서
            // 각 방향 별로 방향 전환을 하고 해당 방향 으로 -1 표기 한다.
            for (int next : CCTVDirection[type]) {
                int nextDirection = (d + next) % 4;
                int nextRow = cctv.row + direction[nextDirection][0];
                int nextCol = cctv.col + direction[nextDirection][1];

                while(true){
                    // 갈 수 있는 방향 인 경우
                    if(!(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M || copyMap[nextRow][nextCol] == 6)){
                        copyMap[nextRow][nextCol] = -1;
                        nextRow += direction[nextDirection][0];
                        nextCol += direction[nextDirection][1];
                    }
                    else break;
                }
            }
        }
        return calculate(copyMap);
    }

    // 사각 지대 개수 찾기
    static int calculate(int[][] copyMap){
        int blank = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(copyMap[i][j] == 0) blank++;
            }
        }
        return blank;
    }

    // 각 조합 별로 사각 지대를 찾기 위한 배열
    static int[][] initCopyMap(int row, int col){
        int[][] copyMap = new int[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }
}
