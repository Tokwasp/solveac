package algorithm.baekjoon.simulation;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class SharkElementary21608 {
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Map<Integer,int[]> myFavoriteFriends;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        myFavoriteFriends = new HashMap<>();

        for(int i = 0; i < N * N; i++){
            int[] favoriteFriends = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int myNumber = favoriteFriends[0]; int friend1 = favoriteFriends[1];
            int friend2 = favoriteFriends[2]; int friend3 = favoriteFriends[3];
            int friend4 = favoriteFriends[4];

            int[] friends = {friend1,friend2,friend3,friend4};
            myFavoriteFriends.put(myNumber, friends);

            // 학생의 않을 자리 찾는 함수
            seatStudent(myNumber,friends);
        }
        System.out.println(checkScore());
    }

    static void seatStudent(int myNumber, int[] friends){
        // 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (n1, n2) -> {
                    if(n1[0] < n2[0]) return 1;
                    else if(n1[0] == n2[0]){
                        if(n1[1] < n2[1]) return 1;
                        else if(n1[1] == n2[1]){
                            if(n1[2] > n2[2]) return 1;
                            else if(n1[2] == n2[2]){
                                if(n1[3] > n2[3]) return 1;
                            }
                        }
                    }
                    return -1;
                }
        );

        // 주변 4방향을 확인 좋아 하는 학생이 있는지 빈칸이 있는지 확인 한다.
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] != 0) continue;

                int blankCount = 0;
                int favoriteCount = 0;

                for(int k = 0; k < 4; k++){
                    int nextX = i + dx[k];
                    int nextY = j + dy[k];

                    boolean error = nextX < 0 || nextY < 0 || nextX > N-1 || nextY > N - 1;

                    if(!error){
                        for (int friend : friends) {
                            if(map[nextX][nextY] == friend) favoriteCount++;
                        }
                        if(map[nextX][nextY] == 0) blankCount++;
                    }
                }
                pq.add(new int[] {favoriteCount, blankCount, i, j});
            }
        }
        int[] seatIndex = pq.poll();
        int row = seatIndex[2]; int col = seatIndex[3];
        map[row][col] = myNumber;
    }

    // 점수 계산 함수
    static int checkScore(){
        int score = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int count = 0;

                for(int k = 0; k < 4; k++) {
                    int nextX = i + dx[k];
                    int nextY = j + dy[k];

                    boolean error = nextX < 0 || nextY < 0 || nextX > N-1 || nextY > N - 1;
                    if(!error) {
                        // myFavoriteFriends.get(map[i][j]) -> 좋아 하는 학생 번호 배열
                        for (int friend : myFavoriteFriends.get(map[i][j])) {
                            if(map[nextX][nextY] == friend) count++;
                        }
                    }
                }

                switch (count) {
                    case 1:
                        score += 1;
                        break;
                    case 2:
                        score += 10;
                        break;
                    case 3:
                        score += 100;
                        break;
                    case 4:
                        score += 1000;
                        break;
                }
            }
        }
        return score;
    }
}