package algorithm.baekjoon.dp;

import java.io.*;
import java.util.*;

public class Mutalisk12869 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] scv = new int[3];
        for(int i=0; i<N; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }

        bfs(scv);
    }

    static void bfs(int[] scv) {
        // 뮤탈 리스크 공격 패턴
        int[][] damage = {{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 3, 9}, {1, 9, 3}};
        int[][][] dp = new int[61][61][61];

        //중복 체크를 위해 -1 초기화
        for(int i = 0; i <= 60; i++){
            for(int j = 0; j <= 60; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        dp[0][0][0] = 0;

        Queue<int[]> q = new LinkedList<>();
        // SCV 각 체력 + 뮤탈 리스크 공격 횟수
        q.add(new int[]{0, 0, 0, 0});

        while(!q.isEmpty()) {
            int[] poll = q.poll();

            for(int i = 0; i < 6; i++) {
                
                int SCV1 = poll[0] + damage[i][0];
                int SCV2 = poll[1] + damage[i][1];
                int SCV3 = poll[2] + damage[i][2];
                int count = poll[3] + 1;

                // 모든 SCV 체력 입력값 이상인 경우
                if(satisfied(scv, SCV1, SCV2, SCV3)) {
                    System.out.println(count);
                    return;
                }

                //체력은 최대 60
                if(SCV1 > 60) SCV1 = 60;
                if(SCV2 > 60) SCV2 = 60;
                if(SCV3 > 60) SCV3 = 60;

                // 방문한 곳은 큐에 넣지 않음
                if(dp[SCV1][SCV2][SCV3] != -1) continue;
                dp[SCV1][SCV2][SCV3] = count;
                q.add(new int[]{SCV1, SCV2, SCV3, count});
            }
        }
    }

    private static boolean satisfied(int[] scv, int SCV1, int SCV2, int SCV3) {
        return SCV1 >= scv[0] && SCV2 >= scv[1] && SCV3 >= scv[2];
    }
}