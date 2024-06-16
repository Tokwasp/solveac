package algorithm.baekjoon.implement;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class MergeSort24060 {
    static int target = 0;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        mergeSort(arr, arr.length);

        if(count < target){
            System.out.println("-1");
        }
    }

    // tmp 배열 초기화, mergeSort 호출
    static void mergeSort(int[] arr, int arrLength){
        int[] tmp = new int[arrLength];
        mergeSort(arr, tmp, 0, arrLength - 1);
    }

    // 배열의 크기가 1이 될때 까지 나눈후 병합
    static void mergeSort(int[] arr, int[] tmp, int start, int end){
        if(start < end){
           int mid = (start + end) / 2;

           mergeSort(arr,tmp,start,mid);
           mergeSort(arr,tmp,mid+1,end);
           merge(arr,tmp,start,mid,end);
        }
    }

    static void merge(int[] arr, int[] tmp, int start, int mid, int end){
        //정렬할 배열을 arr 로부터 가져 온다. ( 이전에 정렬한 것을 참고 하기 위해 )
        for(int i = start; i <= end; i++){
            tmp[i] = arr[i];
        }

        // 비교할 두 배열의 시작 지점과 배열에 넣을 순서인 index
        int part1 = start;
        int part2 = mid + 1;
        int index = start;

        // 두 배열 중 하나를 다 탐색 할때 까지
        while(part1 <= mid && part2 <= end){
            if(tmp[part1] <= tmp[part2]){
                arr[index] = tmp[part1];
                part1++;
            }
            else{
                arr[index] = tmp[part2];
                part2++;
            }

            count++;
            if(count == target){
                System.out.println(arr[index]);
            }
            index++;
        }

        // 왼쪽 배열의 부분이 남았을 경우
        for(int i = 0; i <= mid - part1; i++){
            arr[index + i] = tmp[part1 + i];
            count++;

            if(count == target){
                System.out.println(arr[index + i]);
            }
        }

        /* 오른쪽 배열 부분이 남았을 경우
        원래 병합 정렬 에서는 오른쪽 부분이 남았을 경우 처리 해주지 않아도 된다.
        ex) 1 3 / 2 4  배열을 비교 할 때
        1 2 3 까지 정렬 하고 오른쪽 4가 남았 으나 arr[] 의 오른쪽은 오름 차순 정렬 되어 있으 므로
        처리를 안해도 되지만 문제 count를 찾기 위해 해야 한다.  */

        for(int i = 0; i <= end - part2; i++){
            arr[index + i] = tmp[part2 + i];
            count++;

            if(count == target){
                System.out.println(arr[index + i]);
            }
        }
    }
}
