package algorithm.baekjoon.datastructure.stack;

import java.io.*;
import java.util.Stack;
import java.util.stream.Stream;

public class ResolvedOakensu17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberCount = Integer.parseInt(br.readLine());

        // 입력 받기
        int[] numArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 결과 배열 초기화
        int[] result = new int[numberCount];
        for(int index = 0; index < result.length; index++){
            result[index] = -1;
        }

        // 오큰수 로직
        Stack<Integer> stack = new Stack<>();
        for(int index = 0; index < numArr.length; index++){
            int currentNum = numArr[index];

            while(!stack.isEmpty() && numArr[stack.peek()] < currentNum){
                int subtractIndex = stack.pop();
                result[subtractIndex] = currentNum;
            }
            stack.push(index);
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for(int num : result){
            sb.append(num).append(" ");
        }
        System.out.print(sb);
    }
}