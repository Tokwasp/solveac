package algorithm.baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 1<= N <= 1000
   반복수만큼 출력하며, 수는 중복되지 않으니 TreeSet(이진트리로 순서대로저장)을 쓰면 되겠다
 */
public class NumberSort2750 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int repeatNumber = Integer.parseInt(br.readLine());

        Set<Integer> set = new TreeSet<>();
        for(int i=0; i<repeatNumber; i++) {
            set.add(Integer.parseInt(br.readLine()));
        }
        Object[] objArr = set.toArray();
        Integer[] resultArr = Arrays.copyOf(objArr,objArr.length, Integer[].class);

        for(int i=0; i<resultArr.length; i++){
            if(i != resultArr.length-1)
                System.out.println(resultArr[i]);
            else
                System.out.printf("%d",resultArr[i]);
        }
    }
}
