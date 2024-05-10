package algorithm.swea.d3;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

/*
    문제 풀이 : 구현

    가로, 세로 , 양쪽 대각선 값을 모두 구했다.
    가로 최대값 : 배열에 값을 넣을떄 1행씩 배열로 받아서 그 배열의 합이 가로의 합이다. -> 가로 최대값 갱신 -> 다음 가로줄
    세로 최대값 : 2중 for 통해 한 세로줄 검사 -> 세로 최대값 갱신 -> 다음 세로줄

    왼쪽 -> 오른쪽 대각선 : 규칙  (0,0) (1,1) ... (99,99) / arr[N,N] (N: 100일때, N-1까지 검사)
    오른쪽 -> 왼쪽 대각선 : 규칙  (0,99) (1,98) .... (99,0) / arr[N,99-N] (N: 100일떄, N-1까지 검사)
 */

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

            //배열 값 넣기, 가로 최대값
            for(int j=0; j < arr.length; j++) {
                //배열에 가로 단위로 값 넣기
                int[] rowArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for(int k=0; k < arr[0].length; k++) arr[j][k] = rowArr[k];

                //한줄의 가로의 합을 구하고 최대값 갱신 하였다.
                int rowSum = Arrays.stream(rowArr).sum();
                rowMax = Math.max(rowMax,rowSum);
            }

            //열 최대값 찾기
            for(int j=0; j < arr[0].length; j++){
                int colSum = 0;
                for(int k=0; k < arr.length; k++){
                    colSum += arr[k][j] ;
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

            //가로, 세로, 왼쪽 -> 오른쪽 대각선, 오른쪽 -> 왼쪽 대각선중 제일 큰값 체크
            max = Math.max(leftDiagonal,Math.max(rightDiagonal,Math.max(rowMax,colMax)));
            String st = "#" + testCaseNum + " " + max;
            sb.append(st).append("\n");
        }
        System.out.print(sb);
    }
}
