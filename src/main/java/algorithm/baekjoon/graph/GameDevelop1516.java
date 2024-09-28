package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class GameDevelop1516 {
    static StringBuilder sb = new StringBuilder();
    static ArrayList<ArrayList<Integer>> connectList;
    static int[] indegree;
    static int[] cost;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N + 1];
        indegree = new int[N + 1];
        connectList = new ArrayList<>();

        for(int i = 0; i <= N; i++){
            connectList.add(new ArrayList<>());
        }

        //입력 받기
        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());

            if(st.hasMoreTokens()){
                while(true){
                    int num = Integer.parseInt(st.nextToken());
                    if(num == -1) break;
                    else {
                        connectList.get(num).add(i);
                        indegree[i]++;
                    }
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[N + 1];
        for(int i = 1; i <= N; i++){
            if(indegree[i] == 0) q.add(i);
        }

        while(!q.isEmpty()){
            int node = q.poll();
            ArrayList<Integer> nextNodeList = connectList.get(node);

            for (int nextNode : nextNodeList) {
                indegree[nextNode]--;
                result[nextNode] = Math.max(result[nextNode], result[node] + cost[node]);

                if (indegree[nextNode] == 0) {
                    q.add(nextNode);
                }
            }
        }

        for(int i = 1; i <= N; i++){
            sb.append(result[i] + cost[i]).append("\n");
        }
        System.out.println(sb);
    }
}
