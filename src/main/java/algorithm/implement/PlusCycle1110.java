package algorithm.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlusCycle1110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());
        int[] numberArr = new int[3];
        int cycleNumber = 0;

        initNumber(numberArr, input);

        do{
            numberArr[2] = (numberArr[0] + numberArr[1]) % 10;
            numberChange(numberArr);
            cycleNumber++;
        } while(input != inputNumber(numberArr));

        System.out.println(cycleNumber);
    }

    static public void numberChange(int[] arr){
        arr[0] = arr[1];
        arr[1] = arr[2];
    }
    static public int inputNumber(int[] arr){
        int result = arr[0] * 10 + arr[1];
        return result;
    }

    static public void initNumber(int[] arr, int input){
        if(input < 10) {
            arr[0] = 0;
            arr[1] = input;
        }
        else {
            arr[0] = input / 10;
            arr[1] = input % 10;
        }
    }
}
