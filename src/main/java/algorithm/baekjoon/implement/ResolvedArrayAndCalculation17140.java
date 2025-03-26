package algorithm.baekjoon.implement;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class ResolvedArrayAndCalculation17140 {
    static final int INIT_ROW = 3;
    static final int INIT_COL = 3;
    static final int MAX_TIME = 100;
    static final int MAX_ARRAY_LENGTH = 100;
    static final int MAX_NUMBERCOUNTS_SIZE = 50;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int targetRow = Integer.parseInt(st.nextToken()) - 1;
        int targetCol = Integer.parseInt(st.nextToken()) - 1;
        int targetNum = Integer.parseInt(st.nextToken());

        // 입력 받기
        arr = new int[INIT_ROW][INIT_COL];
        for(int i = 0; i < arr.length; i++){
            arr[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int time = 0;
        while(time <= MAX_TIME){
            if(isArrayIn(targetRow,targetCol) && isFindTarget(targetRow, targetCol, targetNum)){
                break;
            }
            if(isRCalculation()){
                arr = RCalculation();
            }
            else{
                arr = CCalculation();
            }
            time++;
        }
        System.out.print(time > MAX_TIME ? -1 : time);
    }

    private static int[][] RCalculation(){
        List<List<NumberCount>> numberCounts = new ArrayList<>();

        // 초기화
        for(int row = 0; row < arr.length; row++){
            numberCounts.add(new ArrayList<>());
        }

        // 행 정렬
        for(int row = 0; row < arr.length; row++){
            Map<Integer, Integer> countMap = new HashMap<>();

            for(int col = 0; col < arr[0].length; col++){
                if(isZeroNumFromArr(row, col)){
                    continue;
                }
                countMap.put(arr[row][col], countMap.getOrDefault(arr[row][col], 0) + 1);
            }

            List<NumberCount> rowNumberCounts = numberCounts.get(row);
            for(Map.Entry<Integer, Integer> entry : countMap.entrySet()){
                rowNumberCounts.add(new NumberCount(entry.getKey(), entry.getValue()));
            }
            Collections.sort(rowNumberCounts);
        }

        // 최대 길이 찾기
        int maxLength = 0;
        for(List<NumberCount> rowNumberCounts : numberCounts){
            int numberCountsSize = rowNumberCounts.size();
            maxLength = Math.max(maxLength, numberCountsSize * 2);
        }

        // 최대 길이는 100
        maxLength = Math.min(maxLength, MAX_ARRAY_LENGTH);

        // 배열 채우기
        int[][] nextArr = new int[arr.length][maxLength];
        for(int row = 0; row < nextArr.length; row++){
            List<NumberCount> rowNumberCounts = numberCounts.get(row);

            for(int col = 0; col < rowNumberCounts.size(); col++){
                // 최대 100개 까지
                if(col >= MAX_NUMBERCOUNTS_SIZE){
                    break;
                }

                NumberCount colNumberCount = rowNumberCounts.get(col);
                nextArr[row][col * 2] = colNumberCount.num;
                nextArr[row][col * 2 + 1] = colNumberCount.count;
            }
        }
        return nextArr;
    }

    private static int[][] CCalculation(){
        List<List<NumberCount>> numberCounts = new ArrayList<>();

        // 초기화
        for(int col = 0; col < arr[0].length; col++){
            numberCounts.add(new ArrayList<>());
        }

        // 열 정렬
        for(int col = 0; col < arr[0].length; col++){
            Map<Integer, Integer> countMap = new HashMap<>();

            for(int row = 0; row < arr.length; row++){
                if(isZeroNumFromArr(row, col)){
                    continue;
                }
                countMap.put(arr[row][col], countMap.getOrDefault(arr[row][col], 0) + 1);
            }

            List<NumberCount> colNumberCounts = numberCounts.get(col);
            for(Map.Entry<Integer, Integer> entry : countMap.entrySet()){
                colNumberCounts.add(new NumberCount(entry.getKey(), entry.getValue()));
            }
            Collections.sort(colNumberCounts);
        }

        // 최대 길이 찾기
        int maxLength = 0;
        for(List<NumberCount> colNumberCounts : numberCounts){
            int numberCountsSize = colNumberCounts.size();
            maxLength = Math.max(maxLength, numberCountsSize * 2);
        }

        // 최대 길이는 100
        maxLength = Math.min(maxLength, MAX_ARRAY_LENGTH);

        // 배열 채우기
        int[][] nextArr = new int[maxLength][arr[0].length];
        for(int col = 0; col < nextArr[0].length; col++){
            List<NumberCount> rowNumberCounts = numberCounts.get(col);

            for(int row = 0; row < rowNumberCounts.size(); row++){
                // 100개 넘을수 있기 때문
                if(row >= MAX_NUMBERCOUNTS_SIZE){
                    break;
                }

                NumberCount colNumberCount = rowNumberCounts.get(row);
                nextArr[row * 2][col] = colNumberCount.num;
                nextArr[row * 2 + 1][col] = colNumberCount.count;
            }
        }
        return nextArr;
    }

    private static boolean isArrayIn(int targetRow, int targetCol) {
        return targetRow <= arr.length -1 && targetCol <= arr[0].length - 1;
    }

    private static boolean isFindTarget(int row, int col, int num){
        return arr[row][col] == num;
    }

    private static boolean isRCalculation(){
        return arr.length >= arr[0].length;
    }

    private static boolean isZeroNumFromArr(int row, int col) {
        return arr[row][col] == 0;
    }

    static class NumberCount implements Comparable<NumberCount>{
        private int num;
        private int count;

        public NumberCount(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(NumberCount another){
            int countResult = this.count - another.count;
            if(countResult != 0) return countResult;
            return this.num - another.num;
        }
    }
}