package implement;

import java.util.Scanner;

public class Recursion17478 {
    static int barCount = 0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

       recursion(N);
       System.out.print(sb);
    }
    static void recursion(int N){
        sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.").append("\n");
        for(int i=0; i<=N; i++) {
            String print = barCountPrint(barCount);
            if(i != N){
                String[] context = new String[]{
                        "\"재귀함수가 뭔가요?\"",
                        "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.",
                        "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.",
                        "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\""
                };
                for (int j = 0; j < context.length; j++) sb.append(print + context[j]).append("\n");
                barCount += 4;
            }
            else{
                String[] ending = new String[]{
                        "\"재귀함수가 뭔가요?\"",
                        "\"재귀함수는 자기 자신을 호출하는 함수라네\""
                };
                for(int j=0; j<ending.length; j++) sb.append(print + ending[j]).append("\n");
            }
        }
        for(int i=0; i<=N; i++){
            String print = barCountPrint(barCount);
            String ending = new String(print + "라고 답변하였지.");
            sb.append(ending).append("\n");
            barCount -= 4;
        }

    }
    static String barCountPrint(int barCount){
        char[] barArr = new char[barCount];
        for(int i=0; i<barArr.length; i++) barArr[i] = '_';
        String result = new String(barArr);
        return result;
    }
}