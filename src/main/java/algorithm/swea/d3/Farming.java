package algorithm.swea.d3;

import java.io.*;
import java.util.stream.Stream;

public class Farming {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int z=1; z<=testNum; z++) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n][n];

            int totalSum = 0;
            int sum = 0;

            //배열에 값넣기, 전체 값 구하기
            for (int i = 0; i < n; i++) {
                int[] row = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < n; j++) {
                    arr[i][j] = row[j];
                    totalSum += row[j];
                }
            }

            int leftCount = n / 2;
            //위쪽 값 구하기
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < leftCount; j++) {
                    sum += arr[i][j];
                }
                int leftIndex = n - 1;
                for (int k =0; k < leftCount; k++) {
                    sum += arr[i][leftIndex--];
                }
                leftCount--;
            }

            int rightcount = 1;
            //아랫쪽 값 구하기
            for (int i = (n / 2 + 1); i < n; i++) {
                for (int j = 0; j < rightcount; j++) {
                    sum += arr[i][j];
                }
                int rightIndex = n - 1;
                for (int k = 0; k < rightcount; k++) {
                    sum += arr[i][rightIndex--];
                }
                rightcount++;
            }
            String st = "#" + z + " ";
            sb.append(st).append(totalSum-sum).append("\n");
        }
        System.out.print(sb);
    }
}
