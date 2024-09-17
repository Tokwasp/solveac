package algorithm.baekjoon;

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class SnakeBridgeGame16928 {
    static int[] rollCount;
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int snake = Integer.parseInt(st.nextToken());
        int bridge = Integer.parseInt(st.nextToken());

        map = new HashMap<>();

        for(int i = 1; i <= snake + bridge; i++){
            st = new StringTokenizer(br.readLine());
            map.put(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        rollCount = new int[101];
        Arrays.fill(rollCount, 101);

        dfs(1,0);
        System.out.println(rollCount[100]);
    }

    static void dfs(int idx, int count){
        if(idx > 100 || rollCount[idx] < count) return;

        rollCount[idx] = count;

        for(int i = 6; i >= 1; i--) {
            if(map.get(idx + i) != null){
                dfs(map.get(idx + i), count + 1);
            }
            else{
                dfs(idx + i, count + 1);
            }
        }
    }
}