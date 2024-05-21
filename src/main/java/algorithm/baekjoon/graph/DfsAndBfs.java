package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/*
    문제 풀이 :

    풀이 방법 : dfs, bfs 구현

    노드 List 만들고 (노드의 개수 + 1)
    양쪽 에서 접근 할 수 있는 무방향 그래프 이므로 노드의 연결 값을 서로 넣어 준다.
    노드의 번호가 작은 것 부터 탐색을 위해 각 노드를 오름 차순 정렬 합니다.

    그후 기본 dfs를 실행 합니다. dfs의 결과 값은 dfsSb에 담습니다.

    bfs visited[] 배열을 재 사용 하기 위해 초기화 하고,
    기본 bfs를 수행 하여 결과 값을 bfsSb에 담습니다.

    배운 점 :
    List 정렬
    자바8 이후 List sort()가 추가 되어 리스트 정렬 할 수 있다.
    Comparator를 인수로 받아 리스트를 정렬 한다.
    오름 차순 : list.sort(Comparator.naturalOrder());
    내림 차순 : list.sort(Comparator.reverseOrder())

 */

public class DfsAndBfs {
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> list;
    static Queue<Integer> q;
    static StringBuilder dfsSb;
    static StringBuilder bfsSb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dfsSb = new StringBuilder(); bfsSb = new StringBuilder();

        int[] NandMandV = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = NandMandV[0]; int M = NandMandV[1]; int V = NandMandV[2];

        list = new ArrayList<>();
        visited = new boolean[N + 1];

        for(int i = 0; i <= N; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            int[] connect = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int node1 = connect[0]; int node2 = connect[1];
            list.get(node1).add(node2);
            list.get(node2).add(node1);
        }

        for(int i = 0; i < list.size(); i++){
           list.get(i).sort(Comparator.naturalOrder());
        }

        dfs(V);

        visited = new boolean[N + 1];
        q = new LinkedList<>();
        q.add(V);

        bfs(V);

        System.out.println(dfsSb);
        System.out.println(bfsSb);
    }

    static void dfs(int index){

        if(!visited[index]) {
            dfsSb.append(index).append(" ");
        }

        visited[index] = true;
        ArrayList<Integer> dfsList = list.get(index);

        for (int node : dfsList) {
            if(!visited[node]){
                dfs(node);
            }
        }
    }

    static void bfs(int index){
        if(!q.isEmpty()){
            int pollNum = q.poll();

            if(!visited[index]){
                bfsSb.append(index).append(" ");
            }

            visited[index] = true;

            ArrayList<Integer> bfsList = list.get(pollNum);

            for (int node : bfsList) {
                if(!visited[node]){
                    bfsSb.append(node).append(" ");
                    visited[node] = true;
                    q.add(node);
                }
            }

            if(!q.isEmpty()) bfs(q.peek());
        }
    }
}
