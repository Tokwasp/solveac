package algorithm;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Calculation17140 {
    static int rowLength = 3;
    static int colLength = 3;

    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] rck = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int r = rck[0] - 1; int c = rck[1] - 1; int k = rck[2];

        arr = new int[3][];
        for(int i = 0; i < 3; i++) arr[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int count = 0;

        while(count < 101){
            if(rowLength - 1 >= r && colLength - 1 >= c){
                if(arr[r][c] == k) break;
            }

            List<List<int[]>> resultList = new ArrayList<>();
            int max = Integer.MIN_VALUE;

            if(arr.length >= arr[0].length) {
                for (int i = 0; i < rowLength; i++) {
                    max = Math.max(rowCalculate(i, true, resultList), max);
                }
                arr = createArr(resultList, true, max);
            }
            else{
                for(int i = 0; i < colLength ; i++){
                    max = Math.max(rowCalculate(i,false, resultList),max);
                }
                arr = createArr(resultList, false, max);
            }

            count++;
            rowLength = arr.length;
            colLength = arr[0].length;
        }

        System.out.println(count > 100 ? -1 : count);
    }

    static int rowCalculate(int N, boolean isRow, List<List<int[]>> resultList){
        Map<Integer,Integer> map = new HashMap<>();
        List<int[]> list = new ArrayList<>();

        if(isRow){
            for(int i = 0; i < colLength; i++) {
                if(arr[N][i] != 0) map.put(arr[N][i], map.getOrDefault(arr[N][i], 0) + 1);
            }
        }
        else{
            for(int i = 0; i < rowLength; i++) {
                if(arr[i][N] != 0) map.put(arr[i][N], map.getOrDefault(arr[i][N], 0) + 1);
            }
        }

        int count = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(new int[] {entry.getKey(), entry.getValue()}); count += 2;
        }

        list.sort(Comparator.comparingInt((int[] a) -> a[1]).thenComparing(a -> a[0]));

        if(count > 100) {
            while(list.size() > 50) {
                list.remove(list.size() - 1);
            }
        }
        resultList.add(list);

        return count;
    }

        static int[][] createArr(List<List<int[]>> resultList, boolean isRow, int max){

            int rowSize = isRow ? resultList.size() : max;
            int colSize = isRow ? max : resultList.size();

            if(rowSize < 3) rowSize = 3;
            if(colSize < 3) colSize = 3;

            int[][] newArr = new int[rowSize][colSize];

            if(isRow) {
                for (int i = 0; i < resultList.size(); i++) {
                    List<int[]> list = resultList.get(i);
                    int colIndex = 0;

                    for (int[] pair : list) {
                        newArr[i][colIndex] = pair[0];
                        newArr[i][colIndex + 1] = pair[1];
                        colIndex += 2;
                    }
                }
            }
            else{
                for (int i = 0; i < resultList.size(); i++) {
                    List<int[]> list = resultList.get(i);
                    int rowIndex = 0;

                    for (int[] pair : list) {
                        newArr[rowIndex][i] = pair[0];
                        newArr[rowIndex + 1][i] = pair[1];
                        rowIndex += 2;
                    }
                }
            }
            return newArr;
    }
}