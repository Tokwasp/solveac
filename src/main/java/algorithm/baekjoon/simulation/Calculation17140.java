package algorithm.baekjoon.simulation;

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
            // arr[r][c] == k 를 찾은 경우
            if (isFind(r, c, k)) break;

            List<List<int[]>> resultList = new ArrayList<>();
            int length = 0;

            // 행의 길이가 열의 길이 이상인 경우
            if(arr.length >= arr[0].length) {
                for (int i = 0; i < rowLength; i++) {
                    // 로직 실행후 열의 길이 리턴
                    length = Math.max(rowCalculate(i, true, resultList), length);
                }
                // 새로운 배열을 생성
                arr = createArr(resultList, true, length);
            }
            else{
                for(int i = 0; i < colLength ; i++){
                    length = Math.max(rowCalculate(i,false, resultList),length);
                }
                arr = createArr(resultList, false, length);
            }

            count++;
            rowLength = arr.length;
            colLength = arr[0].length;
        }

        System.out.println(count > 100 ? -1 : count);
    }

    private static boolean isFind(int r, int c, int k) {
        if(rowLength - 1 >= r && colLength - 1 >= c){
            if(arr[r][c] == k) return true;
        }
        return false;
    }

    static int rowCalculate(int N, boolean isRow, List<List<int[]>> resultList){
        // 숫자의 개수를 세기 위한 Map
        Map<Integer,Integer> map = new HashMap<>();
        // 결과를 담기 위한 list
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

        // list.size * 2 = count 이다.
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(new int[] {entry.getKey(), entry.getValue()}); count += 2;
        }

        // 숫자의 개수 만큼 정렬후 같으면 숫자 순으로 오름 차순
        list.sort(Comparator.comparingInt((int[] a) -> a[1]).thenComparing(a -> a[0]));

        // 길이가 100을 넘을 경우
        while(list.size() > 50) {
            list.remove(list.size() - 1);
        }

        resultList.add(list);

        return count;
    }

    //List 정보를 배열로 전환
    static int[][] createArr(List<List<int[]>> resultList, boolean isRow, int max){

        int rowSize = isRow ? resultList.size() : max;
        int colSize = isRow ? max : resultList.size();

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