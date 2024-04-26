package algorithm.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
  풀이:
  1-20까지 표를 그려서 규칙성을 찾을려고했다.
  그러던중에 끝자리에 따라서 발견되는 규칙성이 있었다.

  다른분 풀이에서 배울점:
  내가푼 풀이는 그리디로 접근했다기 보다 수학으로 푼거같다.
  다른분들의 풀이를 보니 n%5의 나머지 값에따라서 접근하였다.
  그리디는 최적의 결과를 찾아야 하니 큰것으로 나눈나머지로 접근하는 방식이 더옳다고 생각한다.
 */
public class Change14916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int result = Integer.parseInt(input);
        int rastNumber = input.charAt(input.length()-1) - '0';

        int fiveQuotient = 0, TwoQuotient = 0, remainder = 0, changeCount = 0;

        //예외처리
        if(result == 1 || result == 3) {
            changeCount = -1;
            System.out.println(changeCount);
            return;
        }

        // 끝자리가 1,3,6,8 인경우
        if (rastNumber == 1 || rastNumber == 3 || rastNumber == 6 || rastNumber == 8)
            fiveQuotient = (result / 5) - 1;

        // 0,2,4,5,7,9 인 경우
        else
            fiveQuotient = result / 5;

        remainder = result - (fiveQuotient * 5);
        TwoQuotient = remainder / 2;
        changeCount = fiveQuotient + TwoQuotient;

        System.out.println(changeCount);
    }
}
