package algorithm.baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class palindrome1259 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        List<String> resultArr = new ArrayList<>();
        //입력값이 0이 아니면 계속반복
        while(!input.equals("0")){
            String[] inputArr = input.split("");

            //스택에 입력한 input 넣기
            Stack<Integer> stack = new Stack<>();
            for(int i=0; i<inputArr.length; i++){
                stack.push(Integer.parseInt(inputArr[i]));
            }

            //스택 pop을 통해 뒤집힌 input을 결과값에 넣기
            int[] stackPop = new int[inputArr.length];

            //integer >> int Integer.value() 생략가능하다.
            for(int i=0; i<inputArr.length; i++){
                stackPop[i] = stack.pop();
            }

            //stack의 결과값을 String에 담는다.
            String reverseInput= Arrays.stream(stackPop)
                    .mapToObj(String::valueOf).reduce((x,y) -> x+y).get();

            //input과 뒤집은 Input을 비교한다 (기본 자료형이 아니므로 equals 비교)
            if(reverseInput.equals(input))
                resultArr.add("yes");
            else
                resultArr.add("no");

            input = br.readLine();
        }
        //output 출력
        for(String s: resultArr)
            System.out.println(s);
    }
}
