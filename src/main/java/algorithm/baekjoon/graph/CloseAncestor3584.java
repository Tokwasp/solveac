package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class CloseAncestor3584 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= testCase; t++){
            int nodeCount = Integer.parseInt(br.readLine());

            // 입력 배열 초기화
            int[] parents = new int[nodeCount + 1];

            // 입력 받기
            for(int node = 1; node < nodeCount; node++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                parents[end] = start;
            }

            // 공통 조상 구하기
            StringTokenizer st = new StringTokenizer(br.readLine());
            int target1 = Integer.parseInt(st.nextToken());
            int target2 = Integer.parseInt(st.nextToken());

            sb.append(solution(target1, target2, parents)).append("\n");
        }
        System.out.print(sb);
    }

    static int solution(int a, int b, int[] parents){
        Set<Integer> set = new HashSet<>();
        set.add(a);
        set.add(b);

        while(a != 0){
            if(set.contains(parents[a])) return parents[a];
            set.add(parents[a]);
            a = parents[a];
        }

        while(b != 0){
            if(set.contains(parents[b])) return parents[b];
            b = parents[b];
        }

        return -1;
    }
}