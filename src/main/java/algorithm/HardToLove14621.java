package algorithm;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class HardToLove14621 {
    static class Node{
        int startNode;
        int endNode;
        int distance;

        public Node(int startNode, int endNode, int distance) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.distance = distance;
        }

        public int getDistance() {
            return distance;
        }
    }

    static int getParent(int[] parent, int x){
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent, parent[x]);
    }

    static void unionParent(int[] parent, int a, int b){
        a = getParent(parent, a);
        b = getParent(parent, b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); int nodeCount = Integer.parseInt(st.nextToken());

        String[] gender = new String[N+1];
        int[] parent = new int[N+1];
        List<Node> list = new ArrayList<>();

        // 성별 입력 받기
        String[] genderInput = br.readLine().split(" ");
        for(int i = 1; i <= N; i++){
            gender[i] = genderInput[i-1];
        }

        //입력 값 받고 list 넣기
        for(int i = 0; i < nodeCount; i++){
            int[] nodeInput = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int startNode = nodeInput[0]; int endNode = nodeInput[1]; int distance = nodeInput[2];

            list.add(new Node(startNode, endNode, distance));
        }

        // 거리 기준 오름 차순 정렬
        list.sort(Comparator.comparingInt(Node::getDistance));

        for(int i = 1; i <= N; i++){
            parent[i] = i;
        }

        int distanceSum = 0;
        int connectNodeCount = 0;

        for(int i = 0; i < nodeCount; i++){
            Node node = list.get(i);
            int startNode = node.startNode; int endNode = node.endNode;
            boolean equalsGender = gender[startNode].equals(gender[endNode]);

            if(getParent(parent,startNode) != getParent(parent,endNode) && !equalsGender) {
                distanceSum += node.distance;
                unionParent(parent, startNode, endNode);
                connectNodeCount++;
            }

            if(connectNodeCount == N - 1) break;
        }

        if(connectNodeCount == N - 1) System.out.println(distanceSum);
        else System.out.println("-1");
    }
}
