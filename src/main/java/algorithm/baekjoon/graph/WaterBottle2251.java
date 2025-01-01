package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class WaterBottle2251 {
    static Set<Integer> resultSet;
    static int[] maxCapacity;
    static int A,B,C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        maxCapacity = new int[] {A,B,C};
        resultSet = new HashSet<>();

        bfs();

        ArrayList<Integer> resultList = new ArrayList<>(resultSet);
        resultList.sort(Comparator.naturalOrder());
        StringBuilder sb = new StringBuilder();
        for(int water : resultList){
           sb.append(water).append(" ");
        }
        System.out.print(sb);
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[201][201][201];

        queue.add(new int[] {0, 0, C});
        visited[0][0][C] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            // A 물병이 빈병 이라면 C 병의 물의 양 추가
            if(cur[0] == 0){
                resultSet.add(cur[2]);
            }

            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(i == j) continue;
                    int[] next = moveWater(i, j, cur);

                    int nextA = next[0];
                    int nextB = next[1];
                    int nextC = next[2];

                    if(!visited[nextA][nextB][nextC]){
                        visited[nextA][nextB][nextC] = true;
                        queue.add(new int[] {nextA, nextB, nextC});
                    }
                }
            }
        }
    }


    private static int[] moveWater(int from, int to, int[] cur) {
        int[] next = Arrays.copyOf(cur, 3);

        int transferable = Math.min(cur[from], maxCapacity[to] - cur[to]);
        next[from] -= transferable;
        next[to] += transferable;
        return next;
    }
}