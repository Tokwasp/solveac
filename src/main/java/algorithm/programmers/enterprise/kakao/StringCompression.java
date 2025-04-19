package algorithm.programmers.enterprise.kakao;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StringCompression {
    public int solution(String input) {
        int minLength = Integer.MAX_VALUE;

        for(int chopLength = 1; chopLength <= input.length(); chopLength++){
            Stack<String> stack = new Stack<>();
            List<ChopString> stringCounts = new ArrayList<>();
            int count = 1;

            for(int index = 0; index + chopLength <= input.length(); index += chopLength){
                String string = input.substring(index, index + chopLength);

                if(stack.isEmpty()){
                    stack.add(string);
                    continue;
                }

                if(stack.peek().equals(string)){
                    count++;
                }
                else{
                    stringCounts.add(new ChopString(stack.pop(), count));
                    stack.add(string);
                    count = 1;
                }
            }

            if(!stack.isEmpty()){
                stringCounts.add(new ChopString(stack.pop(), count));
            }

            // 길이 계산
            int totalLength = 0;
            for(ChopString chopString :stringCounts){
                int countlength = 1;
                int divideNum = chopString.count;
                while(divideNum >= 10){
                    divideNum /= 10;
                    countlength++;
                }

                if(chopString.count == 1){
                    totalLength += chopLength;
                }
                else {
                    totalLength += chopLength + countlength;
                }

            }
            int extra = input.length() % chopLength;
            totalLength += extra;
            minLength = Math.min(minLength, totalLength);
        }

        return minLength;
    }

    static class ChopString{
        String string;
        int count;

        public ChopString(String string, int count){
            this.string = string;
            this.count = count;
        }
    }
}