package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Laboratory14502 {
    static int[][] map;
    static int[][] bfsMap;
    static Queue<int[]> initVirusQ;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int initVirusCount;
    static int wallCount = 0;
    static int mapSize;
    static int maxNoVirusCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken());

        map = new int[N][];

        for(int i = 0; i < N; i++){
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        initVirusQ = new LinkedList<>();

        // 바이러스 위치, 개수 찾기 + 벽 개수 찾기
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] == 2) initVirusQ.add(new int[] {i,j});
                if(map[i][j] == 1) wallCount++;
            }
        }

        initVirusCount = initVirusQ.size();
        wallCount += 3;
        mapSize = map.length * map[0].length;

        dfs(0);

        System.out.println(maxNoVirusCount);
    }

    static void dfs(int depth){
        // 3개 선택 bfs() -> 바이러스 감염x 개수 체크
        if(depth == 3){
            bfs();
            return;
        }

        int depthPlusOne = depth + 1;

        // 바이러스 감염x 3개 조합
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] == 0){
                    map[i][j] = 1;
                    dfs(depthPlusOne);
                    map[i][j] = 0;
                }
            }
        }
    }

    static void bfs(){
        Queue<int[]> virusQ = new LinkedList<>(initVirusQ);
        bfsMap = new int[map.length][map[0].length];

        // bfs 하기 위한 깊은 복사
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                bfsMap[i][j] = map[i][j];
            }
        }

        int virusCount = 0;

        // bfs
        while(!virusQ.isEmpty()){
            int[] virusXY = virusQ.poll();
            int virusX = virusXY[0];
            int virusY = virusXY[1];

            for(int i = 0; i < 4; i++){
                int x = virusX + dx[i];
                int y = virusY + dy[i];

                boolean error = x < 0 || y < 0 || x > (bfsMap.length -1) || y > (bfsMap[0].length - 1);

                if(!error && bfsMap[x][y] == 0){
                    bfsMap[x][y] = 2;
                    virusCount++;
                    virusQ.add(new int[] {x, y});
                }
            }
        }
        int noVirusCount = mapSize - virusCount - initVirusCount - wallCount;
        maxNoVirusCount = Math.max(maxNoVirusCount, noVirusCount);
    }
}
