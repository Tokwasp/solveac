package algorithm.greedy;

import java.io.*;

public class LostParenthesis1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        StringBuilder sb = new StringBuilder();
        int sum = 0;
        // - 이후를 체크 하기 위한 변수
        boolean flag = false;

        for(int i=0; i< input.length(); i++){
            char ch = input.charAt(i);
            //'+', '-' 기호가 아니면
            if(!(ch == '+' || ch == '-')) {
                sb.append(ch);
            }
            //'+', '-' 기호일 경우
            else{
                if(flag) sum -= Integer.parseInt(sb.toString());
                else sum += Integer.parseInt(sb.toString());
                if (ch == '-') flag = true;
                sb = new StringBuilder();
            }
            //마지막에는 부호가 없으므로 따로 처리
            if(i == input.length() -1){
                if(flag) sum -= Integer.parseInt(sb.toString());
                else sum += Integer.parseInt(sb.toString());
            }
        }
        System.out.print(sum);
    }
}
