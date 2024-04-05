package algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class ATM11399 {
    public static void main(String[] args) throws Exception {
        //입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력받은 String배열 공백으로 자르고 int[] 배열로 바꾸기
        String[] inputWithdrawTime = br.readLine().split("\\s");
        int[] WithdrawTime = Stream.of(inputWithdrawTime).mapToInt(Integer::parseInt).toArray();

        //시간 짧은순으로 오름차순 정렬
        Arrays.sort(WithdrawTime);

        //개인당 걸리는 시간 계산
        int[] includeWaitingWithdrawTime = new int[WithdrawTime.length];
        for(int i=0; i< includeWaitingWithdrawTime.length; i++){
            for(int j=0; j < i+1; j++){
                includeWaitingWithdrawTime[i] += WithdrawTime[j];
            }
        }
        //총 시간계산
        int resultWithdrawTime = 0;
        for(int i : includeWaitingWithdrawTime)
            resultWithdrawTime += i;

        System.out.println(resultWithdrawTime);
    }
}
