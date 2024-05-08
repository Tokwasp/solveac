package algorithm.swea.d3;

import java.io.*;

/*
    문제 풀이 :
    풀이 방법 : 구현

    모든 행과, 모든 열에, 펠린 드롬수 만큼 문자를 가져와 뒤집어 비교 하여 찾는다.
    행(가로)를 검사 한다고 하였을 떄,
    각 행에서 펠린 드롬을 만들 수 있는 개수는 가로 길이 - 펠린 드롬 수 + 1 이다.

    ex) 가로의 길이 : 8 , 길이가 4인 펠린 드롬을 만들 수 있는 경우의 수는 5 이다.
 */
public class Palindrome {
    static int PalindromeCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int i=1; i<11; i++) {
            int palindromeNum = Integer.parseInt(br.readLine());
            String[][] stArr = new String[8][8];

            for (int j = 0; j < stArr.length; j++) {
                String[] input = br.readLine().split("");
                for (int k = 0; k < input.length; k++) {
                    stArr[j][k] = input[k];
                }
            }

            PalindromeCount = widthHeightPalindromeCheck(stArr, palindromeNum);

            String st = "#" + i + " " + PalindromeCount;
            sb.append(st).append("\n");
            PalindromeCount = 0;
        }
        System.out.print(sb);
    }

    static int widthHeightPalindromeCheck(String[][] stArr, int palindromeNum){
        int palindromeCount = 0;
        int repeat = stArr.length - palindromeNum + 1;

        for(int i=0; i<stArr.length; i++) {

            int startNum = 0;
            int endNum = palindromeNum - 1;

            for(int j=0; j<repeat; j++) {

                //행 검사 StringBuilder
                StringBuilder rowSb = new StringBuilder();
                //열 검사 StringBuilder
                StringBuilder colSb = new StringBuilder();

                for (int k = startNum; k <= endNum; k++) {
                    rowSb.append(stArr[i][k]);
                    colSb.append(stArr[k][i]);
                }

                //문자로 만들고 StringBuilder.reverse()를 통해 문자를 뒤 집는다.

                //행 검사
                String rowSt = rowSb.toString();
                String rowReverseString = rowSb.reverse().toString();

                //열 검사
                String colSt = colSb.toString();
                String colReverseString = colSb.reverse().toString();

                //뒤 집은 문자 열과 원래 문자 열이 같을 경우 펠린 드롬 이다.
                if (rowSt.equals(rowReverseString)) palindromeCount++;
                if (colSt.equals(colReverseString)) palindromeCount++;
                startNum++; endNum++;
            }

        }
        return palindromeCount;
    }
}