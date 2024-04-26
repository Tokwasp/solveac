package algorithm.baekjoon.implement;

import java.io.*;
import java.util.*;

public class NumberBaseball2503 {
    static ArrayList<Number> arrList;

    public static class Number {
        int num;
        int strike;
        int ball;

        public Number (int num, int strike, int ball) {
            this.num = num;
            this.strike = strike;
            this.ball = ball;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        arrList = new ArrayList<>();

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arrList.add(new Number(n, s, b)); // 질문
        }

        int cnt = 0;
        for (int i=123; i<988; i++) {
            int a2 = i / 100;            // 100의 자리
            int b2 = i / 10 - (a2 * 10); // 10의 자리
            int c2 = i % 10;             // 1의 자리

            // 0이 포함되거나 중복된 수가 있는 경우
            if (a2 == 0 || b2 == 0 || c2 == 0 || a2 == b2 || a2 == c2 || b2 == c2)  continue;

            if (find(a2, b2, c2)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    public static boolean find(int a2, int b2, int c2) {
        for (int i=0; i<arrList.size(); i++) {
            Number number = arrList.get(i);

            int a1 = number.num / 100;            // 100의 자리
            int b1 = number.num / 10 - (a1 * 10); // 10의 자리
            int c1 = number.num % 10;             // 1의 자리

            int st1 = number.strike;
            int ba1 = number.ball;

            int st2 = 0;
            int ba2 = 0;

            // 스트라이크
            if (a1 == a2) st2++;
            if (b1 == b2) st2++;
            if (c1 == c2) st2++;

            // 볼
            if (a2 == b1 || a2 == c1) ba2++;
            if (b2 == a1 || b2 == c1) ba2++;
            if (c2 == a1 || c2 == b1) ba2++;

            if (st1 != st2 || ba1 != ba2) {
                return false;
            }
        }

        return true;
    }
}