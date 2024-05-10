package algorithm.swea.d3;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/*
   문제 풀이 :

   풀이 방법 : 구현 ( 자료 구조 Queue )

 */
public class PasswordGenerator {

    static final int MAX_CYCLE_NUM = 5;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int t=1; t <= 10; t++) {
            int testNum = Integer.parseInt(br.readLine());
            q = new LinkedList<>();
            StringBuilder result = new StringBuilder();

            // 데이터 읽어와 큐에 넣기
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int num : input) {
                q.add(num);
            }

            // 암호 생성기 함수
            cycle();

            //결과 값 result에 넣기
            while(!q.isEmpty()){
                result.append(q.poll()).append(" ");
            }

            String st = "#" + testNum + " " + result;
            sb.append(st).append("\n");
        }

        System.out.print(sb);
    }

    static void cycle() {
        int cycleNum = 1;

        while (true) {
            // 5를 초과한 경우 1로 초기화
            if (MAX_CYCLE_NUM < cycleNum) cycleNum = 1;

            // q.poll 한 수를 cycleNum 만큼 빼고 다시 q에 넣는다.
            if (!q.isEmpty()) {
                int pollNum = q.poll();
                pollNum -= cycleNum;

                // 탈출 조건
                if (pollNum <= 0) {
                    pollNum = 0;
                    q.offer(pollNum);
                    break;
                } else {
                    // 탈출 조건이 아닐 경우
                    q.offer(pollNum);
                }
            }
            cycleNum++;
        }
    }
}
