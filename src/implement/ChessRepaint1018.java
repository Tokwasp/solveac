package implement;

import java.util.Scanner;

/*
   문제를 읽고 고민해 보았지만 어떻게 풀어야할지 감이오지않아 답지를 보고 힌트를 얻고 풀었다. 현재까지 본 완전탐색중에선
   가장 어려운거 같은데 이게 왜 실버4인지는 모르겠다...
   다음주에 다시 풀어야 한다.
 */
public class ChessRepaint1018 {

    static int getSolution(int startRow, int startCol, String[] board){
        String[] pattern = {"BWBWBWBW","WBWBWBWB"};
        int sol = 0;

        for(int i=0; i<8; i++){
            int row = i + startRow;
            for(int j=0; j<8; j++){
                int col = j + startCol;
                if(board[row].charAt(col) != pattern[row % 2].charAt(j)) sol++;
            }
        }
        return Integer.min(sol,64-sol);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        sc.nextLine();

        String[] board = new String[row];
        for(int i=0; i<row; i++) {
            board[i] = sc.nextLine();
        }

        int prior = Integer.MAX_VALUE;
        int min,compare = 0;
        for(int i=0; i<row-7; i++){
            for(int j=0; j<col-7; j++){
                min = getSolution(i,j,board);
                compare = Integer.min(prior,min);
                prior = compare;
            }
        }
        System.out.print(compare);
    }
}
