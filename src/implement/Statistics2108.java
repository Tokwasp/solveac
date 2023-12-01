package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
   풀이 : 처음에 위치 index와 객체의 수를 표현하는 count를 포함하는 객체를 만들어 풀었는데
   정렬하는 과정에서 스트림을 사용하여 정렬을 하니 메모리가 너무많이 소모되었다.

   다른분 풀이 :
   정수의 절댓값은 4000이 넘지 않는 것을 이용 하여 + - 배열의 길이가 4001(0포함)인 배열을 만들었다.
   num값이 -일 경우 minus 배열에 |num|번째 배열에 count++; 를 한다. plus인 경우 num번째 배열에 count++;를 한다.
   그 후 plus,minus 배열을 돌며 max count값을 찾아낸다.
 */
public class Statistics2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] arr = new int[N];
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(br.readLine());

        //산술 평균
        long sum =Arrays.stream(arr).summaryStatistics().getSum();
        sb.append(Math.round(sum/(double)N)).append("\n");

        //중앙값
        Arrays.sort(arr);
        sb.append(arr[arr.length / 2]).append("\n");

        //최빈값
        int[] plus = new int[4002];
        int[] minus = new int[4001];
        ArrayList maxList = new ArrayList();

        for(int i=0; i<arr.length; i++){
            if(arr[i] < 0 ) minus[Math.abs(arr[i])]++;
            else plus[arr[i]]++;
        }
        int max = 0;
        for(int i=0; i<plus.length; i++){
            max = Math.max(max,plus[i]);
        }
        for(int i=0; i<minus.length; i++){
            max = Math.max(max,minus[i]);
        }

        for(int num : arr){
            if(num < 0 && max == minus[Math.abs(num)] && !maxList.contains(num)) maxList.add(num);
            if(num >= 0 && max == plus[num] && !maxList.contains(num)) maxList.add(num);
        }
        if(maxList.size() >= 2) sb.append(maxList.get(1)).append("\n");
        if(maxList.size() == 1) sb.append(maxList.get(0)).append("\n");

        //범위
        sb.append(Math.abs(arr[0] - arr[arr.length-1]));
        System.out.println(sb);
    }
}
