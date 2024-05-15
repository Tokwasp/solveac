package algorithm.swea.d3;

import java.io.*;
import java.util.stream.Stream;

/*
    문제 풀이 :

    구현 방법 : dfs(완전 탐색, 순열)

    selected[] : 0 ~ 18 중 규영이가 선택 한 숫자를 제외한 수를 가지고 있다.
    dfs(int index, gyuyoungScore)
    index : 규영이가 낸 배열의 index를 의미한다.
    gyuyoungScore : 규영이가 얻은 점수를 의미한다.

 */
public class GyuyoungCardGame {
    static boolean selected[];
    static int[] gyuyoungCard;
    static int winCount = 0;
    static int loseCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase  = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {

            //전체 0 ~ 18
            selected = new boolean[19];

            gyuyoungCard = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for(int cardNum : gyuyoungCard) {
                selected[cardNum] = true;
            }

            dfs(0,0);
            String st = "#" + t + " " + winCount + " " +loseCount;
            sb.append(st).append("\n");
            winCount = 0; loseCount = 0;
        }

        System.out.print(sb);
    }

    static void dfs(int index, int gyuyoungScore) {

        // 8개의 숫자가 모든 선택 된 경우
        if(index == 9) {
            //1 ~ 18 을 모든 더한 수는 171이다.
            int inyoungScore = 171 - gyuyoungScore;
            if(gyuyoungScore > inyoungScore) winCount++;
            else loseCount++;
        }

        for(int i = 1; i <= 18; i++) {
            if(selected[i]) continue;

            selected[i] = true;

            int gyuyoungWinScore = gyuyoungScore +  i + gyuyoungCard[index];

            if(gyuyoungCard[index] > i) dfs(index + 1, gyuyoungWinScore);
            else dfs(index + 1, gyuyoungScore);

            selected[i] = false;
        }
    }
}
