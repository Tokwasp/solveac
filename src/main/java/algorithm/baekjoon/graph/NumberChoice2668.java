package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class NumberChoice2668 {
    public static boolean[] visited;
    public static int[] arr;
    public static int n, start = 0;
    public static ArrayList<Integer> list = new ArrayList<>();

    public static void dfs(int i) {
        if (arr[i] == start) {
            list.add(start);
        }

        if (!visited[arr[i]]) {
            visited[arr[i]] = true;
            dfs(arr[i]);
            visited[arr[i]] = false;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {
            visited[i] = true;
            start = i;
            dfs(i);
            visited[i] = false;
        }

        System.out.println(list.size());
        for (int x : list) {
            System.out.println(x + " ");
        }

    }
}