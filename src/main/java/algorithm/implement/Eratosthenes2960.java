package algorithm.implement;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/*
   풀이 :
   n과 k를 받고 2~n 크기의 배열을 만든다.
   소수의 배수인 경우 0으로 만들며 count 를 더했다.
   다음 숫자는 0이 아닌 숫자가 소수가 된다. 이렇게 반복 하며 count 가 k와 같아 지면
   flag를 true로 하여 반복문을 나온다.
 */
public class Eratosthenes2960 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //Ex) n: 7 2~7까지 숫자 6개
        int n = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        //2~n까지 숫자를 만들어 배열에 넣는다..
        arr = Stream.iterate(2, a -> a+1).limit(n).mapToInt(Integer::intValue).toArray();

        boolean flag = false;
        int count = 0, result = 0, sosu = 0;

        for(int i=0; i<arr.length; i++){
            if(flag) break;
            if(arr[i] != 0) sosu = arr[i];
            for(int j=i; j<arr.length; j++){
                if(arr[j] % sosu == 0 && arr[j] / sosu > 0 ){
                    result = arr[j];
                    arr[j] = 0;
                    count++;
                    if(count == k) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        System.out.print(result);
    }
}
