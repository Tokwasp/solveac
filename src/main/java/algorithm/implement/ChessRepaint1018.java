package algorithm.implement;

import java.util.Scanner;

/*
   재풀이 :
   Scanner의 nextInt는 개행문자를 삭제해주지 않아서 nextInt 이후에 nextLine을 받을경우 개행문자를 제거해줘야한다.
 */
public class ChessRepaint1018 {
    static String[] board;

    private static int getSolution(int startRow, int startCol){
        String[] ogBoard = {"BWBWBWBW","WBWBWBWB"};
        int row, col = 0;
        int count = 0;

        for(int i=0; i<8; i++){
            row = startRow + i;
            for(int j=0; j<8; j++){
                col = startCol + j;
                if(board[row].charAt(col) != ogBoard[row % 2].charAt(j)) count++;
            }
        }
        return Integer.min(count,64-count);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        sc.nextLine();

        board = new String[row];

        for(int i=0; i<row; i++){
            board[i] = sc.nextLine();
        }

        int sol = Integer.MAX_VALUE;
        for(int i=0; i<row-7; i++){
            for(int j=0; j<col-7; j++){
                sol = Integer.min(sol,getSolution(i,j));
            }
        }
        System.out.print(sol);
    }
}
