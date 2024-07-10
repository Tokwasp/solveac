package algorithm.baekjoon.simulation;

import java.io.*;
import java.util.stream.Stream;

public class Ramp14890 {
    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NL = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = NL[0]; L = NL[1];

        map = new int[N][];

        for(int i = 0; i < N; i++) map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int count = 0;

        for(int i = 0; i < N; i++){
            if(isGoRoad(i,true)) count++;
            if(isGoRoad(i,false)) count++;
        }

        System.out.println(count);
    }

    static boolean isGoRoad(int x, boolean isRow){
        // 체크 할 길 배열
        int[] road = new int[N];
        // 경사로 체크 배열
        boolean[] isRamp = new boolean[N];

        if(isRow){
            for(int i = 0; i < N; i++) road[i] = map[x][i];
        }
        else{
            for(int i = 0; i < N; i++) road[i] = map[i][x];
        }

        for(int i = 0; i < N - 1; i++){
            int diff = road[i] - road[i + 1];

            if(Math.abs(diff) >= 2) return false;

            //오른쪽 경사로 놓는 경우
            if(diff == 1){
                for(int j = i + 1; j <= i + L; j++){
                    if(!isMapIn(j) || isRamp[j] || road[i+1] != road[j]) return false;
                    else{
                        isRamp[j] = true;
                    }
                }
            }

            //왼쪽 경사로 놓는 경우
            if(diff == -1){
                for(int j = i; j >= i + 1 - L; j--){
                    if(!isMapIn(j) || isRamp[j] || road[i] != road[j]) return false;
                    else{
                        isRamp[j] = true;
                    }
                }
            }
        }
        return true;
    }

    static boolean isMapIn(int num){
        return 0 <= num && num < N;
    }
}
