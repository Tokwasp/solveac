package algorithm.baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

/*
    재 풀이(당일) :
    첫째줄에 받은 배열을 정렬한후에 이분탐색을 사용하여 두번째받은 배열에 있는 수인지 체크한다.
    이분탐색은 재귀와 반복호출이 있는데 성능적으로는 반복호출이 좋으나 가독성적으론 재귀가 더 좋다.
*/
public class NumberFind1920 {
    static int[] arr;

    private static int getSolution(int findNum, int low, int high){
        int mid = 0;

        while(low <= high){
            mid = (low + high) / 2;
            if(findNum == arr[mid]) return 1;
            else if(findNum < arr[mid]) high = mid - 1;
            else low = mid + 1;
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        arr = new int[n];
//
//        for(int i=0; i<n; i++){
//            arr[i] = sc.nextInt();
//        }
//
//        int m = sc.nextInt();
//        int[] findArr = new int[m];
//        for(int i=0; i<m; i++){
//            findArr[i] = sc.nextInt();
//        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        arr = Stream.of(input).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());
        String[] findInput = br.readLine().split(" ");
        int[] findArr = Stream.of(findInput).mapToInt(Integer::parseInt).toArray();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<findArr.length; i++){
            sb.append(getSolution(findArr[i],0,arr.length-1)).append("\n");
        }
        System.out.print(sb);
    }
}
