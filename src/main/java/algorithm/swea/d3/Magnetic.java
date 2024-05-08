package algorithm.swea.d3;

import java.io.*;
import java.util.stream.Stream;

/*
    문제 풀이 : 1은 n극 아래로 간다. 2는 s극 위로 간다.

    열을 검사 할때 2가 먼저 나온 다면 2는 위로 가므로 교착 상태가 안된다.
    1이 먼저 나오고 2가 나와야 교착 상태가 된다.
    ex) 2122 << 첫번째 2는 위로 올라 가서 교착 상태x 122 는 하나의 교착 상태가 된다.

     -> (1,2) 순서 대로 나온 순서쌍 개수가 답이다.
 */
public class Magnetic {
    static int[][] arr;
    static int count = 0;
    static final int TEST_CASE = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= TEST_CASE; t++) {
            int rowColLength = Integer.parseInt(br.readLine());

            arr = new int[rowColLength][rowColLength];

            for (int i = 0; i < arr.length; i++) {
                int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < arr.length; j++) {
                    arr[i][j] = input[j];
                }
            }

            checkMagnetic();

            String st = "#" + t + " " + count;
            sb.append(st).append("\n");
            count = 0;
        }
        System.out.print(sb);
    }

    static void checkMagnetic(){

        //1이 나왔 는지 체크 하기 위한 변수
        boolean check = false;

        // 한 행을 체크 한 후 다음 열로 넘어 간다.
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){

                // 1이 나왔고(check) / 배열의 수가 2일 경우
                if(check && arr[j][i] == 2){
                    count++;
                    check = false;
                }

                if(arr[j][i] == 1) {
                    check = true;
                }

            }
            // 마지막 1이 나왔을 경우 check 될수 있기 때문에 초기화 한다.
            check = false;
        }
    }
}
