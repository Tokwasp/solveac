package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class ABCDE13023 {
    static boolean pass;
    static ArrayList<ArrayList<Integer>> connection;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        connection = new ArrayList<>();
        visited = new boolean[N];

        //연결된 노드의 개수
        for(int i = 0; i < N; i++){
            connection.add(new ArrayList<>());
        }

        //연결 노드 정보 입력
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            //( 양 방향 )
            connection.get(start).add(end);
            connection.get(end).add(start);
        }

        // 각 시작 노드 마다 DFS 실행
        for(int i = 0; i < N; i++){
            DFS(i,1);
            if(pass) break;
        }

        System.out.println(pass ? 1: 0);
    }

    static void DFS(int node, int connectNum){
        //탈출 조건
        if(connectNum == 5){
            pass = true;
            return;
        }

        ArrayList<Integer> list = connection.get(node);

        // 연결된 노드 순회
        for (int nextNode : list) {
            if(!visited[nextNode]){
                visited[node] = true;
                DFS(nextNode, connectNum + 1);
                visited[node] = false;
            }
        }
    }
}