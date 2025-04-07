package algorithm.programmers.enterprise.kakao;

public class NBase {
    public String solution(int base, int targetCount, int participantCount, int myTurnNum) {
        int order = 0;
        int num = 0;
        int myTurnCount = 0;
        boolean escape = false;

        StringBuilder resultSb = new StringBuilder();
        while(true){
            String next = convert(num, base);
            for(int index = 0; index < next.length(); index++){
                if(order % participantCount == myTurnNum - 1){
                    resultSb.append(next.charAt(index));
                    myTurnCount++;
                }

                if(myTurnCount == targetCount){
                    escape = true;
                    break;
                }
                order++;
            }

            if(escape){
                break;
            }
            num++;
        }
        return resultSb.toString();
    }

    static String convert(int num, int base){
        String[] characters = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        StringBuilder sb = new StringBuilder();

        while(base <= num){
            int remain = num % base;
            sb.append(characters[remain]);
            num /= base;
        }
        sb.append(characters[num]);
        return sb.reverse().toString();
    }
}
