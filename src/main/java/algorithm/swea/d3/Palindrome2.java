package algorithm.swea.d3;

import java.io.*;

/*
    문제 풀이 :

    풀이 방법 : 구현

    길이가 1 ~ 100인 펠린 드롬을 찾는다.

    ex) 1차원 배열의 길이가 5이고 길이가 2인 펠린 드롬을 찾는 다면?
    startIndex = 0,  endIndex = (2 - 1) 인덱스 0 부터 시작 이므로

    for(int i = startIndex; i <= endIndex; i++){
        sb.append(arr[j]); (sb = StringBuilder)
    }

    뽑은 문자 = sb.toString(); 뒤집은 문자 = sb.reverse().toString();
    if(뽑은 문자.equals(뒤집은 문자)) {
        maxPalindromeCount = 2;
        break; ( 이미 펠린 드롬을 찾아서 더이상 길이가 2인 펠린 드롬을 찾을 필요가 없다.)
    }

    startIndex++; endIndex++; ( 다음 target 1~3 인덱스 이다. )

    이러한 과정을 몇번 반복 해야 하는가? repeat  = (행의 길이 - 펠린 드롬 길이 + 1) -> 겉 for 감싸기
    한행을 완성 했다. 여러 행을 검사 하려면? -> 겉에 몇 행이 있는지 겉 for 감싸기

 */
public class Palindrome2 {
    static final int TEST_CASE = 10;
    static String[][] arr;
    static int maxPalindromeCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder resultSb = new StringBuilder();

        for(int t = 1; t <= TEST_CASE; t++){
            int testCaseNum = Integer.parseInt(br.readLine());
            arr = new String[100][];

            for(int i=0; i < arr.length; i++){
                arr[i] = br.readLine().split("");
            }

            // i == 펠린 드롬의 길이  / 1 ~ 100 길이의 펠린 드롬을 배열 에서 찾는다.
            for(int i= 1; i <= 100; i++) {
                CheckRowColPalindrome(i);
            }

            String st = "#" + testCaseNum + " " + maxPalindromeCount;
            resultSb.append(st).append("\n");
            maxPalindromeCount = 0;
        }

        System.out.print(resultSb);
    }

    static void CheckRowColPalindrome(int len){

        for(int i = 0; i < arr.length; i++){

            int repeat = arr.length - len + 1;
            int startIndex = 0;
            int lastIndex = len - 1;
            boolean pass = false;

            for(int j = 0; j < repeat; j++){

                StringBuilder rowSb = new StringBuilder();
                StringBuilder colSb = new StringBuilder();

                for(int k = startIndex; k <= lastIndex; k++){
                    rowSb.append(arr[i][k]);
                    colSb.append(arr[k][i]);
                }

                String rowString = rowSb.toString();
                String reverseRowString = rowSb.reverse().toString();

                String colString = colSb.toString();
                String reverseColString = colSb.reverse().toString();

                if(rowString.equals(reverseRowString) || colString.equals(reverseColString)) {
                    maxPalindromeCount = len;
                    pass = true;
                    break;
                }

                startIndex++; lastIndex++;
            }

            if(pass) break;
        }
    }
}
