package algorithm.programmers.enterprise.kakao;

public class BracketConversion {
    public String solution(String input) {
        if(input.isEmpty()){
            return "";
        }

        int left = 0;
        int right = 0;
        int index = 0;

        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            if(ch == '('){
                left++;
            }
            else{
                right++;
            }

            if(left == right){
                index = i;
                break;
            }
        }

        String u = input.substring(0, index + 1);
        String v = input.substring(index + 1);
        if(isCorrect(u)){
            return u + solution(v);
        }

        return "(" + solution(v) + ")" + reverse(u);
    }

    static boolean isCorrect(String input){
        int left = 0;
        int right = 0;
        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            if(ch == '('){
                left++;
            }
            else{
                right++;
            }

            if(left < right){
                return false;
            }
        }

        return left == right;
    }

    static String reverse(String input){
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < input.length() - 1; i++){
            char ch = input.charAt(i);
            if(ch == '('){
                sb.append(")");
            }
            if(ch == ')'){
                sb.append("(");
            }
        }
        return sb.toString();
    }
}
