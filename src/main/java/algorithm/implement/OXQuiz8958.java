package algorithm.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
  내 해결방안 : String으로 OX값을받는다. 연속된숫자의 처리를 위해
  int continuity변수와 결과값을 담을 int result변수 을 준비하고
  Charat(i)의 값이 "O"이면 continuity 값을 1추가하고 "X"이면 0으로 초기화한다.
  그후 result에 continuity값을 더한다.
 */
public class OXQuiz8958 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int[] result = new int[count];
        int continuity = 0;

        for(int i=0; i<count; i++) {
            String OXQuizString = br.readLine();
            for (int j = 0; j < OXQuizString.length(); j++) {
                if (OXQuizString.charAt(j) == 'O')
                    continuity += 1;
                else
                    continuity = 0;
                result[i] += continuity;
            }
            continuity = 0;
        }
        for(int i=0; i<result.length; i++)
            System.out.println(result[i]);
    }
}
