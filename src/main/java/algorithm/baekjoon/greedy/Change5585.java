package algorithm.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/*
   풀이
   1000원에서 입력값을 빼면 잔돈이 남는다.
   잔돈 갯수를 최대한 적게 줘야 하니까 잔돈의 크기를 내림차순으로 정렬하고
   잔돈이 0원이 될때까지 잔돈이 동전,지폐로 나눠진다면 나눠진 몫을 잔돈 갯수에 다하고
   나머지를 다시 잔돈으로 넣는다.

   * 만약 정렬이 안된 int[] 배열이 있다면?

   int[] noSortedChangeArr = {500,50,100,10,5,1};

   오름 차순으로 정렬
   Arrays.sort[noSortedChangeArr)

   내림 차순 정렬
   Integer[] integerChangeArr = Arrays.stream(noSortedChangeArr).         boxed().      toArray(Integer[]::new);
                 결과값                 IntStream                       Stream<Integer>         Integer[]
   Arrays.sort(integerChangeArr, Collections.reverseOrder());
   Integer[] > int[]로
   int[] changeArr = Arrays.stream(integerChangeArr).mapToInt(Integer::intValue).toArray();
 */
public class Change5585 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int change = 1000 - Integer.parseInt(br.readLine());
        int[] noSortedChangeArr = {500,50,100,10,5,1};
        int changeCount = 0;

        //만약 정렬안된  int[] 배열이 있을때 내림차순 정렬
        Integer[] integerChangeArr = Arrays.stream(noSortedChangeArr).boxed().toArray(Integer[]::new);
        Arrays.sort(integerChangeArr,Collections.reverseOrder());
        int[] changeArr = Arrays.stream(integerChangeArr).mapToInt(Integer::intValue).toArray();

        while(change !=0){
            for(int i=0; i<changeArr.length; i++) {
                if (change / changeArr[i] > 0) {
                    changeCount += change / changeArr[i];
                    change %= changeArr[i];
                }
            }
        }
        System.out.println(changeCount);
    }
}
