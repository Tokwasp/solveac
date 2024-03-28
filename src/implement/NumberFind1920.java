package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

/*
    문제 풀이법: 못풀어서 질문게시판을 통해 이분탐색을 써야한다는 것을 알았다.

    공부해야할것 : 스트림 전환, 이분탐색
    내림차순 정렬 : Collections.reverseOrder()
*/
public class NumberFind1920 {
    static int[] arr;

    //이진탐색
    private static int binarySearch(int key, int low, int high) {
        int mid;
        mid = (low + high) / 2;

        if (low <= high) {
            if (key == arr[mid]) return 1;
            else if (key > arr[mid]) return binarySearch(key, mid + 1, high);
            else return binarySearch(key, low, mid - 1);
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        //스트림을 이용해 String배열을 int배열로 전환
        arr = Stream.of(input).mapToInt(Integer::parseInt).toArray();

        int M = Integer.parseInt(br.readLine());
        String[] findInput = br.readLine().split(" ");
        int[] find = Stream.of(findInput).mapToInt(Integer::parseInt).toArray();

        StringBuilder sb = new StringBuilder();
        Arrays.sort(arr);

        for(int i : find){
            if(binarySearch(i,0,arr.length-1) != 0) sb.append("1").append("\n");
            else sb.append("0").append("\n");
        }
        System.out.print(sb);
    }
}
