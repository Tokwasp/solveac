package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class HideAndSeek1697 {
    static int[] map;
    static Queue<Integer> q;
    static int N, K;
    static int[] dx = {-1,1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());

        map = new int[100001];
        q = new LinkedList<>();

        q.add(N);
        bfs();

        if(N == K) System.out.println("0");
        else {
            System.out.println(map[K]);
        }
    }

    static void bfs(){
        while(!q.isEmpty()){
            int pollNum = q.poll();

            int newIndex;
            for(int i = 0; i < 3; i++){
                if(dx[i] == 0){
                    newIndex = pollNum * 2;
                }
                else {
                    newIndex = pollNum + dx[i];
                }

                boolean error = newIndex < 0 || newIndex > (map.length - 1);

                if(!error && map[newIndex] == 0){
                    map[newIndex] = map[pollNum] + 1;
                    q.add(newIndex);
                }
            }
        }
    }
}
