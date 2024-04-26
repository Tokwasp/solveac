package algorithm.baekjoon.implement;

import java.io.*;
/*
  풀이 :
  Input의 길이가 짝수인 경우 대칭이 되지만, 홀수인 경우에 가운데 문자 하나가 더 들어 가게 된다.
  Stack을 활용 하여 앞 문자만 구하면 stack.pop()을 통해서 뒤 집을 수 있다고 판단 하였다.

  다른사람 풀이에서 배울점:
  StringBuilder에서 reverse() 메소드를 이용 하면 스택을 사용 하지 않아도 되었다.
  앞 문자와 가운데 문자 까지 구한후 reverse()를 append()하면 구할 수 있습니다.
 */
public class Palindrome1254 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringBuilder sb = new StringBuilder();
        String error = "I'm Sorry Hansoo";
        String result = "";

        //A-Z 문자 개수가 들어갈 배열
        int[] chArr = new int[26];
        int oddCount = 0;
        // 문자 개수가 홀수인 문자는 가운데에 들어 가게 된다.
        char oddCh = ' ';
        // 문자 개수가 홀수인 문자가 2개 이상 이면 펠린드롬수가 아니다.
        boolean oddCountException = false;

        // 문자의 개수를 체크한다.
        for(int i=0; i<input.length(); i++){
            chArr[input.charAt(i) - 'A']++;
        }
        //홀수 문자의 개수를 체크한다.
        for(int i=0; i<chArr.length; i++){
            if(oddCount == 1 && chArr[i] % 2 == 1){
                oddCountException = true;
                break;
            }
            else if(chArr[i] % 2 == 1){
                oddCount++;
                oddCh = (char)(i+'A');
            }
        }
        //홀수 문자 개수가 짝수인 경우에 예외 발생
        if(oddCountException){
            sb.append(error);
            System.out.println(sb);
            return;
        }
        else{
            for(int i=0; i<chArr.length; i++){
                char ch = (char)(i+65);
                // AAB C BAA 앞에 들어갈 AAB는 문자 개수/2 만큼 반복된다.
                if(chArr[i] / 2 > 0){
                    for(int j=0; j<chArr[i]/2; j++) sb.append(ch);
                }
            }
        }
        result += sb.toString();
        String reverse = sb.reverse().toString();
        if(input.length() %2 ==1) result += oddCh;

        sb = new StringBuilder();
        sb.append(result).append(reverse);
        System.out.println(sb);
    }
}
