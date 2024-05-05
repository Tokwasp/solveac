package algorithm.swea.d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MaxReward {
    static int max = 0;
    static int[] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int j = 1; j <= testNum; j++) {
            String[] input = br.readLine().split(" ");
            arr = new int[input[0].length()];
            int target = Integer.parseInt(input[1]);

            for (int i = 0; i < arr.length; i++) {
                arr[i] = input[0].charAt(i) - '0';
            }

            if (arr.length < target) {
                target = arr.length;
            }

            DFS(0,0, target);

            String st = "#" + j + " " + max;
            sb.append(st).append("\n");
            max = 0;
        }
        System.out.print(sb);
    }

    static void DFS(int start, int depth, int target) {
        if (depth == target) {
            int result = 0;
            for (int i = arr.length-1; i >= 0; i--) {
                result += (int) (Math.pow(10,arr.length-1-i) * arr[i]);
            }

            max = Math.max(result, max);
            return;
        }

        for (int i = start; i < arr.length -1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                swap(i, j);
                DFS(start, depth + 1, target);
                swap(i, j);
            }
        }
    }

    static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
