package algorithm.swea.d3;

import java.io.*;
import java.util.stream.Stream;

public class MemoryRecovery {
    static String myBinaryCode;
    static int[] myBinaryCodeArr;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder resultSb = new StringBuilder();

        for(int t = 1; t <= testCase; t++) {

            StringBuilder sb = new StringBuilder();

            //정답 이진수 코드
            String answerBinaryCode = br.readLine();
            int[] answerBinaryCodeArr = Stream.of(answerBinaryCode.split("")).mapToInt(Integer::parseInt).toArray();

            //이진수 코드를 초기화 ( "0100" -> 길이 : 4 -> "0000")
            for (int i = 0; i < answerBinaryCodeArr.length; i++) sb.append("0");

            myBinaryCode = sb.toString();
            myBinaryCodeArr = Stream.of(myBinaryCode.split("")).mapToInt(Integer::parseInt).toArray();

            count = 0;
            while (!myBinaryCode.equals(answerBinaryCode)) {

                int index = 0;
                int change = 0;

                for (int i = 0; i < myBinaryCodeArr.length; i++) {

                    // 내 이진수 코드와 정답 이진수 코드가 다를 경우
                    if (!(myBinaryCodeArr[i] == answerBinaryCodeArr[i])) {

                        index = i;
                        //index 부터 그 이후를 정답 이진수 코드로 바꾼다.
                        change = answerBinaryCodeArr[i];
                        reverse(index, change);
                        count++;
                    }

                }
            }

            String st = "#" + t + " " + count;
            resultSb.append(st).append("\n");
            count = 0;

        }
        System.out.print(resultSb);
    }

    static void reverse(int index, int change){

        for(int i=index; i< myBinaryCodeArr.length; i++){
            myBinaryCodeArr[i] = change;
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0; i< myBinaryCodeArr.length; i++){
            sb.append(myBinaryCodeArr[i]);
        }

        myBinaryCode = sb.toString();
    }
}
