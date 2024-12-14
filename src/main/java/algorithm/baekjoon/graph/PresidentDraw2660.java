package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PresidentDraw2660 {
    static List<List<Node>> connect;
    static List<Node> resultList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        connect = new ArrayList<>();
        resultList = new ArrayList<>();

        for(int i = 0; i <= N; i++){
            connect.add(new ArrayList<>());
        }

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int human1 = Integer.parseInt(st.nextToken());
            int human2 = Integer.parseInt(st.nextToken());
            if(human1 == -1 && human2 == -1) break;

            connect.get(human1).add(new Node(human2, 0));
            connect.get(human2).add(new Node(human1, 0));
        }

        for(int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            bfs(i, visited);
        }

        resultList.sort(Comparator.comparingInt(a -> a.depth));
        List<Node> resultFilter = resultList.stream()
                .filter(a -> a.depth == resultList.get(0).depth)
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append(resultFilter.get(0).depth).append(" ").append(resultFilter.size()).append("\n");
        for (Node node : resultFilter) {
            sb.append(node.num).append(" ");
        }
        System.out.println(sb);
    }

    private static void bfs(int nodeNum, boolean[] visited) {
        Queue<Node> queue = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        Node initNode = new Node(nodeNum, 0);
        queue.add(initNode);
        stack.add(initNode);
        visited[nodeNum] = true;

        while(!queue.isEmpty()){
            Node currentNode = queue.poll();
            List<Node> nextNode = connect.get(currentNode.num);

            for (Node next : nextNode) {
                if(!visited[next.num]){
                    next.addDepth(currentNode);
                    queue.add(next);
                    stack.add(next);
                    visited[next.num] = true;
                }
            }
        }
        resultList.add(new Node(nodeNum, stack.pop().depth));
    }

    static class Node {
        int num;
        int depth;

        public Node(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }

        public void addDepth(Node node){
            this.depth = node.depth + 1;
        }
    }
}