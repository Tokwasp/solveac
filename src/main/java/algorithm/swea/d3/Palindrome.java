package algorithm.swea.d3;

import java.io.*;

public class Palindrome {
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

            //가로체크
            int widthPalindromeCount = widthPalindromeCheck(stArr, palindromeNum);
            //세로체크
            int heightPalindromeCount = heightPalindromeCheck(stArr, palindromeNum);

            int PalindromeCount = widthPalindromeCount + heightPalindromeCount;
            String st = "#" + i + " " + PalindromeCount;
            sb.append(st).append("\n");
        }
        System.out.print(sb);
    }

    static int widthPalindromeCheck(String[][] stArr, int palindromeNum){
        int palindromeCount = 0;
        int repeat = stArr.length - palindromeNum + 1;

        for(int i=0; i<stArr.length; i++) {
            int startNum = 0;
            int endNum = palindromeNum - 1;
            for(int j=0; j<repeat; j++) {
                StringBuilder sb = new StringBuilder();

                for (int k = startNum; k <= endNum; k++) {
                    sb.append(stArr[i][k]);
                }

                String st = sb.toString();
                String reverseString = sb.reverse().toString();
                if (st.equals(reverseString)) palindromeCount++;
                startNum++; endNum++;
            }
        }
        return palindromeCount;
    }

    static int heightPalindromeCheck(String[][] stArr, int palindromeNum){
        int palindromeCount = 0;
        int repeat = stArr[0].length - palindromeNum + 1;

        for(int i=0; i<stArr.length; i++) {
            int startNum = 0;
            int endNum = palindromeNum - 1;

            for(int j=0; j<repeat; j++) {
                StringBuilder sb = new StringBuilder();

                for (int k = startNum; k <= endNum; k++) {
                    sb.append(stArr[k][i]);
                }

                String st = sb.toString();
                String reverseString = sb.reverse().toString();
                if (st.equals(reverseString)) palindromeCount++;
                startNum++; endNum++;
            }
        }
        return palindromeCount;
    }
}