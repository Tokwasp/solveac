package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class AddParentheses16637 {
    static int max = Integer.MIN_VALUE;
    static List<Integer> numList;
    static List<Character> opList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String input = br.readLine();
        numList = new ArrayList<>();
        opList = new ArrayList<>();

        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            if(ch == '+' || ch == '-' || ch == '*') opList.add(ch);
            else numList.add(ch - '0');
        }

        dfs(0, numList.get(0));
        System.out.println(max);
    }

    static int cal(char op, int num1, int num2){
        switch (op){
            case '+':
                return (num1 + num2);
            case '-':
                return (num1 - num2);
            case '*':
                return (num1 * num2);
        }
        return -1;
    }

    static void dfs(int opIndex, int result){
        if(opIndex == opList.size()){
            max = Math.max(result,max);
            return;
        }

        int newResult = cal(opList.get(opIndex),result,numList.get(opIndex + 1));
        dfs(opIndex + 1, newResult);

        if(opIndex + 1 < opList.size()){
            int rightResult = cal(opList.get(opIndex + 1), numList.get(opIndex + 1), numList.get(opIndex + 2));

            int precalculated = cal(opList.get(opIndex), result, rightResult);
            dfs(opIndex + 2, precalculated);
        }
    }
}
