package algorithm.baekjoon.graph;

import java.io.*;
import java.util.StringTokenizer;

public class SogangGround14938 {
    static final int INF = 1501;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] dist = new int[n + 1][n + 1];

        // 거리 INF 초기화
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                dist[i][j] = INF;
            }
            dist[i][i] = 0;
        }

        int[] items = new int[n + 1];

        st = new StringTokenizer(br.readLine(), " ");
        // 아이템 입력 받기
        for(int i = 1; i <= n; i++){
            items[i] = Integer.parseInt(st.nextToken());
        }

        // 길의 경로 입력 받기
        for(int i = 1; i <= r; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            dist[start][end] = Math.min(length, dist[start][end]);
            dist[end][start] = Math.min(length, dist[start][end]);
        }

        // 플로이드 알고리즘
        // 거치는 노드
        for(int k = 1; k <= n; k++){
            // 시작 노드
            for(int i = 1; i <= n; i++){
                // 도착 노드
                for(int j = 1; j <= n; j++){
                    if(i == j) continue;
                    if(dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 최단 거리 갱신 이후 거리가 수색 범위 이내인 경우 item 개수 더하기
        int maxItemCount = 0;
        int itemCount = 0;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(dist[i][j] <= m){
                    itemCount += items[j];
                }
            }
            maxItemCount = Math.max(maxItemCount, itemCount);
            itemCount = 0;
        }
        System.out.print(maxItemCount);
    }
}