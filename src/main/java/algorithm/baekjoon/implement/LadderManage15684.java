package algorithm.baekjoon.implement;

import java.io.*;
import java.util.stream.Stream;

public class LadderManage15684 {
    static int ladderCount = 0;
    static int col,M,row;
    static boolean[][] connect;
    static boolean pass = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NMH = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        col = NMH[0]; M = NMH[1]; row = NMH[2];

        //사다리 연결 체크 배열
        connect = new boolean[row + 1][col + 1];

        for(int i = 0; i < M; i++){
            int[] ab = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = ab[0]; int b = ab[1];
            // true -> b, b + 1 연결 되어 있다는 표시
            connect[a][b] = true;
        }

        //추가 사다리 개수는 0개 ~ 3개
        for(int i = 0; i <= 3; i++) {
            // 추가 사다리 조합 찾기
            permutationAndGame(1, 1, 0);
            if(pass) break;
            else ladderCount++;
        }

        System.out.println(pass ? ladderCount : -1);
    }

    static void permutationAndGame(int x, int y, int count){
        // 사다리 조합 찾을 시 사다리 타기 시작
        if(count == ladderCount){
            ladderGame();
            return;
        }

        for(int i = x; i <= row; i++){
            // j < col -> 사다리 배열 -1 까지 설치 되므로
            for(int j = x == i ? y : 1; j < col; j++){
                // n, n + 1 사다리 / n - 1, n 사다리 없을 경우
                if(!connect[i][j] && !connect[i][j-1]){
                    connect[i][j] = true;
                    permutationAndGame(i, j + 1, count + 1);
                    connect[i][j] = false;

                    // 조건 만족시 조합 그만 찾기
                    if(pass) return;
                }
            }
        }
    }

    static void ladderGame(){
        // 현재 사다리 번호 node
        int node = 1; int ladderRow = 1; int ladderCol = 1;

        while(true){
            // 왼쪽 이동 하는 사다리
            if(connect[ladderRow][ladderCol-1]) ladderCol -= 1;
            // 오른쪽 이동 사다리
            else if(connect[ladderRow][ladderCol]) ladderCol += 1;
            ladderRow += 1;

            // 사다리 끝 도달 && 마지막 사다리 번호가 아닌 경우
            if(ladderRow == connect.length && node != col){
                // 출발 번호와 끝 번호가 다를 경우
                if(node != ladderCol) break;
                // 같을 경우 다음 사다리 번호로
                else{
                    node += 1;
                    ladderRow = 1; ladderCol = node;
                }
            }
            // 사다리 끝 도달 && 마자믹 사다리 번호
            else if(ladderRow == connect.length){
                if(node != ladderCol) break;
                // 통과 표시
                else {
                    pass = true;
                    break;
                }
            }
        }
    }
}