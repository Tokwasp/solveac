package algorithm.swea.d3;

import java.io.*;
import java.util.stream.Stream;

/*
   문제 풀이 :

   풀이 방법 : 구현

   2중 for문 사용 하면 시간 복잡도가 맞겠다 생각 하였다. ( 1억 / 25 = 4백만 , 2중 for문 1000 * 1000 = 백만 )
   단조를 체크할 때, productNum -> String 변환 하여 각 자리 수를 chat - '0' 으로 체크 하였다.
   이전수가 (prev) 현재수 보다 크면 단조가 아니다.
   for문을 모두 통과 하면 단조 이고, 최댓값을 갱신 하였다.
 */
public class DanjoIncreseNum {
    static int danjo = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            int N = Integer.parseInt(br.readLine());

            //입력 받기
            int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            // 4 2 1 3 을 체크할 때, 4의 경우 : 2,1,3 / 2의 경우 : 1,3 이런 식이다.
            for(int i = 0; i < arr.length - 1; i++) {
                for(int j = i + 1; j < arr.length; j++) {
                    int product = arr[i] * arr[j];
                    if(danjoCheck(product)) danjo = Math.max(danjo, product);
                }
            }

            String st = "#" + t + " " + danjo;
            sb.append(st).append("\n");
            danjo = -1;
        }
        System.out.print(sb);
    }

    static boolean danjoCheck(int num) {
        String numString = String.valueOf(num);
        int prev = numString.charAt(0) - '0';

        for(int i = 1; i < numString.length(); i++) {
            if(prev > numString.charAt(i) - '0') return false;
            prev = numString.charAt(i) - '0';
        }

        return true;
    }
}
