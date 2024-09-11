package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Lotto6603 {
    static int[] input;
    static List<Integer> list;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        while (true) {
            input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (input[0] == 0) break;

            list = new ArrayList<>();
            dfs(1, 0);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int index, int count) {
        if (count == 6) {
            for (int i : list) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = index; i < input.length; i++) {
            list.add(input[i]);
            dfs(i + 1, count + 1);
            list.remove(list.size() - 1);
        }
    }
}