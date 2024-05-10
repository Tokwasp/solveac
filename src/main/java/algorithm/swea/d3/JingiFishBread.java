package algorithm.swea.d3;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

/*
   문제 풀이 :

   풀이 방법 : 구현

   주의 사항 :
   '이전/이후' 모두 기준이 되는 수를 포함 하는 것이 옳습니다.

   구현 방법:
   사람이 오는 초에 대한 배열을 오름 차순 정렬 -> 가장 빠른 손님이 먼저 온다.
   가장 늦게 오는 손님의 초를 구한다.
   for 문을 돌며 ( 1초 부터 ~ 가장 늦은 손님의 초 까지 ) 빵이 있을 경우 주고,
   없을 경우 Impossible 을 결과 값에 넣고 다음 케이스 이동
   마지막 손님 초에 빵을 줬다면 Possible 결과 값에 넣고 다음 케이스 이동

   빵을 만드는 방법 :
   진기는 M초에 K개의 빵을 만든다.
   for 문은 1초씩 흘러 간다. (i = 1; i <= maxCount; i++)
   if(i % M == 0) 이라면 K개의 빵을 만든다.

 */
public class JingiFishBread {

    static String Impossible = "Impossible";
    static String Possible = "Possible";

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {

            // N, M, K를 입력 받아 input[] 넣는다.
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int N = input[0]; int M = input[1]; int K = input[2];

            // 사람이 오는 초를 받고 personSecondArr[] 넣는다.
            int[] personSecondArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            //오름 차순 정렬 하여 가장 빨리 오는 사람 부터 받는다.
            Arrays.sort(personSecondArr);

            //가장 늦게 오는 손님의 초를 구한다.
            int maxSecond = Arrays.stream(personSecondArr).max().getAsInt();

            int index = 0;
            int bread = 0;
            String st = "#" + t + " ";

            // 0초에 온 손님이 있다면
            if(personSecondArr[0] == 0){
                sb.append(st).append(Impossible).append("\n");
                continue;
            }

            for (int i = 1; i <= maxSecond; i++) {

                //빵 생성
                if(i % M == 0) bread += K;

                // 손님이 온 초에 빵이 없다면
                if (personSecondArr[index] == i && bread == 0) {
                    sb.append(st).append(Impossible).append("\n");
                    break;
                }

                // 손님이 온 초에 빵이 있다면
                if (personSecondArr[index] == i && bread > 0) {
                    bread--;
                    index++;
                }

                //마지막 손님이 빵을 받는 경우 통과
                if (i == maxSecond) sb.append(st).append(Possible).append("\n");
            }
        }
        System.out.print(sb);
    }
}
