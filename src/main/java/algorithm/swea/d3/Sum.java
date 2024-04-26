package algorithm.swea.d3;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class Sum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int i=1; i<11; i++) {
            int[][] arr = new int[100][100];
            int testCaseNum = Integer.parseInt(br.readLine());

            int max = 0;
            int rowMax = Integer.MIN_VALUE;
            int colMax = Integer.MIN_VALUE;
            int leftDiagonal = 0;
            int rightDiagonal = 0;

            //배열 값 넣기, 행 최대값
            for(int j=0; j<100; j++) {
                int[] rowArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for(int t=0; t<100; t++) arr[j][t] = rowArr[t];
                int rowSum = Arrays.stream(rowArr).sum();
                rowMax = Math.max(rowMax,rowSum);
            }

            //열 최대값 찾기
            for(int k=0; k<100; k++){
                int colSum = 0;
                for(int l=0; l<100; l++){
                    colSum += arr[l][k] ;
                }
                colMax = Math.max(colMax,colSum);
            }

            //왼쪽 대각선
            for(int o=0; o<100; o++){
                leftDiagonal += arr[o][o];
            }

            //오른쪽 대각선
            for(int m=0; m<100; m++){
                rightDiagonal += arr[m][99-m];
            }
            max = Math.max(leftDiagonal,Math.max(rightDiagonal,Math.max(rowMax,colMax)));
            String st = "#" + i + " " + max;
            sb.append(st).append("\n");
        }
        System.out.print(sb);
    }
}
