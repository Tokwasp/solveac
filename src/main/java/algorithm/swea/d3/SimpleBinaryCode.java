package algorithm.swea.d3;

import java.io.*;
import java.util.stream.Stream;

/*
   문제 풀이 :

   풀이 방법 : 구현

   암호의 맨 뒷 자리는 모두 1이다.
   배열에 값을 넣고, row 뒤에서 부터 접근 하여 1인 위치를 찾는다.
   StringBuilder 를 사용 하여 (해당 위치 - 6) 부터 7개를 append 하고 String을 만든다.(sb.toString())
   암호와 일치 하는 것을 찾고 해당 암호를 result[index]에 넣는다. (index 초기값 : 7)
   재귀 호출을 위한 정보를 바꾸고, (해당 위치 - 7, index--)
   index != 0 까지 재귀 호출 하여 8개의 암호 배열을 채운다.

 */
public class SimpleBinaryCode {
    static String arr[][];
    static int result[];

    public static void main(String[] args) throws IOException {
        StringBuilder resultSb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            int[] rowAndCol = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int row = rowAndCol[0];
            int col = rowAndCol[1];

            String[] password = {"0001101", "0011001", "0010011", "0111101", "0100011",
                    "0110001", "0101111", "0111011", "0110111", "0001011"};

            arr = new String[row][];
            result = new int[8];

            //배열 입력 받기
            for (int i = 0; i < row; i++) {
                arr[i] = br.readLine().split("");
            }

            // 행에서 뒤에서 부터 접근 하여 시작점을 찾는다 ("1")
            boolean pass = false;
            for (int i = 0; i < arr.length; i++) {
                for (int j = arr[0].length - 1; j >= 0; j--) {
                    if (arr[i][j].equals("1")) {
                        checkBinaryCode(i, j, 7, password);
                        pass = true;
                        break;
                    }
                }
                if (pass) break;
            }

            boolean check = checkCorrectCode();
            int sum = 0;
            for (int i : result) {
                sum += i;
            }

            String st = "#" + t + " ";
            if (check) resultSb.append(st).append(sum).append("\n");
            else resultSb.append(st).append("0").append("\n");
        }
        System.out.print(resultSb);
    }

    // 시작점 으로 부터 7개씩 문자를 받아 암호 코드로 만든다.
    static void checkBinaryCode(int row, int col, int index, String[] password){
        StringBuilder sb = new StringBuilder();
        int count = 7;
        //앞에서 부터 문자를 받아야 한다.
        int checkCol = col - 6;

        for(int i=0; i < count; i++){
           sb.append(arr[row][checkCol]);
           checkCol++;
        }

        String binaryString = sb.toString();
        int num = 0;

        //암호 코드와 비교 하여 맞는 수를 찾는다.
        for(int i=0; i < password.length; i++){
            if(password[i].equals(binaryString)) num = i;
        }

        result[index] = num;
        //다음 열의 끝 지점
        int newcol = col -7;

        //총 8개의 암호를 찾아야 하므로 재귀 호출
        if(index != 0) checkBinaryCode(row, newcol, index - 1, password);
    }

    // (홀수 자리 * 3) + ( 짝수 자리 합) 구하는 메소드
    static boolean checkCorrectCode(){

        int oddSum = 0;
        int evenSum = 0;

        for(int i=0; i < result.length; i += 2){
            oddSum += result[i];
        }

        for(int i=1; i < result.length; i += 2){
            evenSum += result[i];
        }

        int check = (oddSum * 3) + evenSum;
        if(check % 10 == 0) return true;
        else return false;
    }
}
