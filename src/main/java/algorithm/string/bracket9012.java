package algorithm.string;

import java.io.*;
import java.util.Stack;

/*
  풀이:
  () 은 한쌍 이여야 한다. ')' 나오면 그전에 무조건 '(' 가 나와야 한다. 앞에 '(' 2개 나올 경우 ')' 도 두개 나와야 한다.
  핵심은 ')"는 '(' 나오기 전엔 나올수 없다는 것이다.
  그레서 Stack에 '(' 문자가 나오면 넣고 ')'가 나올 경우 pop() 하도록 하였다. 문자열이 끝났을때 Stack이 비어있다면 그것은 VPS 다.
 */
public class bracket9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack();
        boolean flag = false;

        for(int i=0; i<T; i++){
            String input = br.readLine();
            for(int j=0; j<input.length(); j++){
                if(input.charAt(j) == '(') st.push('(');
                else if(input.charAt(j) == ')'){
                    if(!st.isEmpty())st.pop();
                    //스택이 비어 있는데 ')'가 나온 다면 VPS가 아니다.
                    else{
                        flag = true;
                        break;
                    }
                }
            }
           if(flag) {
               sb.append("NO").append("\n");
               flag = false;
           }
           // 문자열이 끝났을때 Stack이 비어있다면 VPS가 맞고, Stack에 남아있다면 VPS가 아니다.
            else {
                if(st.isEmpty()) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
            st.clear();
        }
        System.out.print(sb);
    }
}
