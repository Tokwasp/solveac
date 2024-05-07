package algorithm.baekjoon.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Virus2606 {
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int computerNum = Integer.parseInt(br.readLine());
        int connectNum = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        visited = new boolean[computerNum + 1];

        for(int i=0; i < computerNum + 1 ; i++){
            list.add(new ArrayList<>());
        }

        for(int i=0; i<connectNum; i++){
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list.get(input[0]).add(input[1]);
            list.get(input[1]).add(input[0]);
        }

//        int index = 0;
//        for (ArrayList<Integer> integers : list) {
//
//            System.out.print(index + "번째 인덱스 : ");
//
//            for (Integer i : integers) {
//                System.out.print(i);
//            }
//            index++;
//            System.out.println();
//        }

        dfs(1);

        int virusNum = 0;
        for (boolean visited : visited) {
            if(visited) virusNum++;
        }

        System.out.println(virusNum - 1);
    }

    static void dfs(int index){

        if(!visited[index]){
            visited[index] = true;
//            System.out.println(index + "에 방문 했습니다.");
            ArrayList<Integer> rowList = list.get(index);

            for (int i : rowList) {
                if(!visited[i]) dfs(i);
            }
        }

    }
}
