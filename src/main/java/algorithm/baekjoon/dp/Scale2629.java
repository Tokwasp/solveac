package algorithm.baekjoon.dp;

import java.io.*;
import java.util.*;

public class Scale2629 {
    static int[] chu;
    static int[] beads;
    static int chuNumber;
    static int beadsNumber;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        chuNumber = Integer.parseInt(br.readLine());
        chu = new int[chuNumber];

        // 추 배열 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < chuNumber; i++) {
            chu[i] = Integer.parseInt(st.nextToken());
        }

        beadsNumber = Integer.parseInt(br.readLine());
        beads = new int[beadsNumber];

        // 구슬 배열 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < beadsNumber; i++) {
            beads[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[chuNumber + 1][40001];
        dfs(0, 0);

        // 추를 활용 하여 구슬의 무게를 만들수 있는가 ?
        for(int i = 0; i < beadsNumber; i++){
            if(visited[chuNumber][beads[i]]) sb.append("Y ");
            else sb.append("N ");
        }

        System.out.println(sb);

    }
    private static void dfs(int index, int weight) {

        boolean escape =  visited[index][weight] || weight > 40000;

        if(escape) return;

        visited[index][weight] = true;

        if(index == chuNumber) return;

        int newWeight = weight + chu[index];

        // 현재 추를 포함 하는 경우
        dfs(index + 1, newWeight);
        // 현재 추를 포함 하지 않는 경우
        dfs(index + 1, weight);

        // 현재 추들의 무게와 들어온 추중에 무거운 것을 오른쪽 저울에 두고 나머지 왼쪽 으로 옮긴 경우
        if(weight < chu[index]){
            dfs(index + 1, chu[index] - weight);
        }
        else {
            dfs(index + 1, weight - chu[index]);
        }
    }
}
