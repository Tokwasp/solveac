package algorithm.swea.d3;

import java.io.*;
import java.util.stream.Stream;

/*
    문제 풀이:

    풀이 방법 : 구현

    수업 일수를 최소한 듣기 위해선 수업이 있는 날에 시작 해야 합니다.
    재귀를 사용 할 경우, stackOverflow에 유의 해야 합니다.

 */
public class ExchangeStudent {
    static int requireCount;
    static int minDay = Integer.MAX_VALUE;
    static int[] openClass;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            requireCount = Integer.parseInt(br.readLine());

            openClass = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            // 일주일 중 수업이 있는날 만 함수를 호출 한다. i == 요일
            for(int i = 0; i < 7; i++) {
                if(openClass[i] == 1) {
                    explore(i);
                }
            }

            String st = "#" + t + " " + minDay;
            sb.append(st).append("\n");
            minDay = Integer.MAX_VALUE;
        }
        System.out.print(sb);
    }

    static void explore(int index) {

        //수업을 한 일수
        int total = 0;
        // 날짜
        int day = 0;
        int newIndex = index;

        //수업 일수를 채우기 전 까지 반복
        while(true) {
            total += openClass[newIndex];
            day++; newIndex++;

            // 토요일 지나면 일요일로
            if(newIndex > 6) newIndex = 0;

            //수업 일수 다 채웠을 경우 날짜 갱신
            if(total == requireCount) {
                minDay = Math.min(minDay, day);
                break;
            }

        }
    }
}
