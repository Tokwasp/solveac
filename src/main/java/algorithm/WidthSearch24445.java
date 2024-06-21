package algorithm;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class WidthSearch24445 {
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int[] position;
    static int num = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1]; position = new int[N + 1];

        // 그래프 양방향 노드 넣기
        graph = new ArrayList<>();
        for(int i = 0 ; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 1; i <= M; i++){
            int[] startEndNode = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int startNode = startEndNode[0]; int endNode = startEndNode[1];

            // 양방향 그래프
            graph.get(startNode).add(endNode); graph.get(endNode).add(startNode);
        }

        // list 내림 차순 정렬
        for(int i = 1; i <= N; i++){
            graph.get(i).sort(Comparator.reverseOrder());
        }

        bfs(R);

        for(int i = 1; i <= N; i++){
            System.out.println(position[i]);
        }
    }

    static void bfs(int startNode){
        Queue<Integer> q = new LinkedList<>();
        q.add(startNode);
        visited[startNode] = true;
        position[startNode] = num;

        while(!q.isEmpty()){
            int start = q.poll();

            List<Integer> targetList = graph.get(start);

            for(int i = 0; i < targetList.size(); i++){
                int targetNode = targetList.get(i);

                if(!visited[targetNode]){
                    num += 1;
                    visited[targetNode] = true;
                    position[targetNode] = num;
                    q.add(targetNode);
                }
            }
        }
    }
}
