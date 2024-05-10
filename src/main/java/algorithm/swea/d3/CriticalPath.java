package algorithm.swea.d3;

import java.util.ArrayList;
import java.util.Scanner;

public class CriticalPath {

    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> list;
    static int max = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= testCase; t++) {
            int nodeNum = sc.nextInt();
            int lineNum = sc.nextInt();

            list = new ArrayList<>();
            visited = new boolean[nodeNum + 1];

            for (int i = 0; i <= nodeNum; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 0; i < lineNum; i++) {
                int line1 = sc.nextInt();
                int line2 = sc.nextInt();
                list.get(line1).add(line2);
                list.get(line2).add(line1);
            }

            for(int i = 1; i <= nodeNum; i++){
                dfs(i,1);
            }

            String st = "#" + t + " " + max;
            sb.append(st).append("\n");
            max = 0;
        }
        System.out.print(sb);
    }

    static void dfs(int index, int count){

        if(max < count) max = count;

        int newCount = count + 1;
        ArrayList<Integer> connectList = list.get(index);

        for (int node : connectList) {
            if(!visited[node]) {
                visited[index] = true;
                dfs(node, newCount);
                visited[index] = false;
            }
        }
    }
}