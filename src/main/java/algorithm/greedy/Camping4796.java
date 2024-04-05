package algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
   풀이:
   결과값은 휴가일(V) / 연속하는일(P) *  일동안(L) 에다가
   V % P 한값이 L보다 작으면 V % P 일을 더해주고 크거나 같은경우는 L을 더해주면된다.

   몰랐던것:
   String[] > int[]
   Arrays.stream(StringCaseArr).mapToInt(Integer::parseInt).toArray();
            Stream<String>            IntStream                int[]

   다른 사람 코드에서 배울점 :
   StringBuilder sb를 사용하여 결과값을 sb.append 하여 마지막에 출력하였다.
   이렇게 하면 마지막에 출력할때 for문을 통해 출력하지않아도 된다.
   값을 받을때 Scanner nextInt를 통해 값을 받은분도 있었다. 이렇게하면 공백을 제거하지 않아도된다.
 */
public class Camping4796 {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        int result = 0;
        int redundancy = 0;
        ArrayList<Integer> resultList = new ArrayList<>();

        while(true){
            String[] StringCaseArr = br.readLine().split("\\s");
            if(StringCaseArr[0].charAt(0) == '0')
                break;
            int[] intCaseArr = Arrays.stream(StringCaseArr).mapToInt(Integer::parseInt).toArray();
            if(intCaseArr[2] % intCaseArr [1] > intCaseArr[0] )
                redundancy = intCaseArr[0];
            else redundancy = intCaseArr[2] % intCaseArr [1];

            result = intCaseArr[2] / intCaseArr [1] * intCaseArr[0] + redundancy;
            count++;
            resultList.add(result);
        }
        for(int i=0; i<resultList.size(); i++){
            System.out.println("Case " + (i+1) + ": " + resultList.get(i));
        }
    }
}
