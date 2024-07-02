package algorithm.baekjoon.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class chicken15686 {
    static int[][] map;
    static List<Place> chickenList;
    static List<Place> homeList;
    static List<Place> myChickenList;
    static int totalChickenDistance = Integer.MAX_VALUE;
    static int M;

    static class Place{
        int row;
        int col;

        public Place(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        homeList = new ArrayList<>();
        chickenList = new ArrayList<>();
        myChickenList = new ArrayList<>();

        for(int i = 1; i < map.length; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < map.length; j++){
                int present = Integer.parseInt(st.nextToken());
                map[i][j] = present;
                if(present == 1) homeList.add(new Place(i,j));
                if(present == 2) chickenList.add(new Place(i,j));
            }
        }

        dfs(0,0);
        System.out.println(totalChickenDistance);
    }
    static void dfs(int index, int count){

        if(count == M){
            totalChickenDistance = Math.min(calculateDistance(),totalChickenDistance);
            return;
        }

        if(index == chickenList.size()) return;

        for(int i = index; i < chickenList.size(); i++){
            myChickenList.add(chickenList.get(i));
            dfs(i + 1, count + 1);
            myChickenList.remove(myChickenList.size() - 1);
        }
    }

    static int calculateDistance(){
        int totalDistance = 0;

        for(int i = 0; i < homeList.size(); i++){
            Place homeXY = homeList.get(i);
            int myChickenDistance = Integer.MAX_VALUE;

            for(int j = 0; j < myChickenList.size(); j++){
                Place chickenXY = myChickenList.get(j);
                int distance = Math.abs(homeXY.row - chickenXY.row) + Math.abs(homeXY.col - chickenXY.col);
                myChickenDistance = Math.min(myChickenDistance, distance);
            }
            totalDistance += myChickenDistance;
        }
        return totalDistance;
    }
}
