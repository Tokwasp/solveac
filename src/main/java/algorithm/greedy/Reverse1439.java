package algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
   풀이:
   연속된 0 혹은 1의 갯수중 작은것을 구하면된다.
 */
public class Reverse1439 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");
        int[] InputArr = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
        int[] result = new int[2];
        int privious = 5;

        //초기값 주기
        if(InputArr[0] == 0)
            privious = 0;
        else
            privious =1;
        result[InputArr[0]]++;

        //이전과 같으면 넘어가고 다를경우 이전값에 현재 원소를 넣고 연속이 끊겼으므로 개수를 더한다.
        for(int i=1; i<InputArr.length; i++){
           if(privious == InputArr[i])
               continue;
           else {
               privious = InputArr[i];
               result[InputArr[i]]++;
           }
        }
        System.out.println(Math.min(result[0],result[1]));
    }
}
