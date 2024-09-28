package algorithm.baekjoon.graph;

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

        bfs();

    }

    static void bfs(){
        boolean[] visited = new boolean[101];
        visited[1] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {1,0});

        boolean pass = false;

        while(!q.isEmpty()){
            int[] poll = q.poll();
            int index = poll[0];
            int rollCount = poll[1];

            if(index == 100){
                System.out.print(rollCount);
                pass = true;
            }

            if(pass) return;

            for(int i = 1; i <= 6; i++){
                int nextIndex = index + i;
                if(nextIndex > 100 || visited[nextIndex]) continue;

                if(map.get(nextIndex) != null){
                    q.add(new int[]{map.get(nextIndex), rollCount + 1});
                }
                else {
                    q.add(new int[]{nextIndex, rollCount + 1});
                }

                visited[nextIndex] = true;
            }
        }
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