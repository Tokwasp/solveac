package algorithm.baekjoon.graph;

import java.io.*;
import java.util.StringTokenizer;

public class CycleGame20040 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] parents = new int[n];
        for(int i = 0; i < parents.length; i++){
            parents[i] = i;
        }

        int count = 0;
        boolean isCycle = false;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            if(isCycle) continue;

            count++;
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(getParents(parents, start) != getParents(parents, end)){
                union(parents, start, end);
            }
            else{
                isCycle = true;
            }
        }

        if(isCycle){
            System.out.println(count);
        }
        else{
            System.out.println(0);
        }
    }

    private static int getParents(int[] parents, int x){
        if(parents[x] == x){
            return x;
        }
        return parents[x] = getParents(parents, parents[x]);
    }

    private static void union(int[] parents, int start, int end) {
        int startParents = getParents(parents, start);
        int endParents = getParents(parents, end);

        if(startParents < endParents){
            parents[endParents] = startParents;
        }
        else{
            parents[startParents] = endParents;
        }
    }
}