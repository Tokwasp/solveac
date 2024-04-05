package algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SugarDelivery {
    public static void main(String[] args) throws Exception{
        //값 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());

        //예외처리
        int resultBucket = 0;
        int[] exceptionNumber = {1,2,4,7};
        for(int i=0; i<exceptionNumber.length; i++){
            if(input == exceptionNumber[i])
                resultBucket = -1;
        }

        //버킷수 찾기
        if(resultBucket != -1) {
            if (input % 5 == 0)
                resultBucket = input / 5;
            else if (input % 5 == 1 || input % 5 == 3)
                resultBucket = input / 5 + 1;
            else if (input % 5 == 2 || input % 5 == 4)
                resultBucket = input / 5 + 2;
        }
        System.out.println(resultBucket);
    }
}