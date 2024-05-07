package algorithm.baekjoon.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ConnectionNum11724 {
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] visited;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dotAndConnectionNum = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int dotNum = dotAndConnectionNum[0]; int connectNum = dotAndConnectionNum[1];

        list = new ArrayList<>();
        visited = new boolean[dotNum + 1];

        for(int i=0; i < dotNum + 1; i++){
            list.add(new ArrayList<>());
        }

        for(int i=0; i<connectNum; i++){
            int[] nodeArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list.get(nodeArr[0]).add(nodeArr[1]); list.get(nodeArr[1]).add(nodeArr[0]);
        }

        // node 연결 요소 확인용

//        int index = 0;
//        for (ArrayList<Integer> integers : list) {
//            System.out.print("index " + index + ": ");
//            for (Integer i : integers) {
//                System.out.print(i);
//            }
//            index++;
//            System.out.println();
//        }

        for(int i=1; i < visited.length; i++) {
            if(!visited[i]) {
                dfs(i);
                count++;
            }
        }
        System.out.println(count);
    }

    static void dfs(int index){

        visited[index] = true;

        ArrayList<Integer> rowIndex = list.get(index);
        for (int i : rowIndex) {
            if(!visited[i]) dfs(i);
        }
    }
}
