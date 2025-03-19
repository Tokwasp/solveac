package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class ResolvedSudoku2239 {
    static final int SUDOKU_ROW_SIZE = 9;
    static List<Blank> blanks;
    static int[][] sudoku;
    static boolean escape = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sudoku = new int[SUDOKU_ROW_SIZE][];

        // 입력받기
        for (int i = 0; i < SUDOKU_ROW_SIZE; i++) {
            sudoku[i] = Stream.of(br.readLine().split("")).mapToInt(string -> string.charAt(0) - '0').toArray();
        }

        // 빈칸 개수 찾기
        blanks = new ArrayList<>();
        for (int row = 0; row < sudoku.length; row++) {
            for (int col = 0; col < sudoku[0].length; col++) {
                if (sudoku[row][col] == 0) {
                    blanks.add(new Blank(row, col));
                }
            }
        }

        dfs(0);
        printSudoku();
    }

    static void dfs(int depth) {
        if (depth == blanks.size()) {
            escape = true;
            return;
        }

        Blank blank = blanks.get(depth);
        int row = blank.row;
        int col = blank.col;

        for (int num = 1; num <= 9; num++) {

            if (isNotDuplicationInRow(row, num) &&
                    isNotDuplicationInCol(col, num) &&
                    isNotDuplicationInSection(row, col, num)) {

                sudoku[row][col] = num;
                dfs(depth + 1);

                if (escape) return;
                sudoku[row][col] = 0;
            }
        }
    }

    static boolean isNotDuplicationInRow(int row, int num) {
        for (int col = 0; col < sudoku[0].length; col++) {
            if (sudoku[row][col] == num) return false;
        }
        return true;
    }

    static boolean isNotDuplicationInCol(int col, int num) {
        for (int row = 0; row < sudoku.length; row++) {
            if (sudoku[row][col] == num) return false;
        }
        return true;
    }

    static boolean isNotDuplicationInSection(int startRow, int startCol, int num) {
        int rowSection = startRow / 3 * 3;
        int colSection = startCol / 3 * 3;

        for (int row = rowSection; row < rowSection + 3; row++) {
            for (int col = colSection; col < colSection + 3; col++) {
                if (sudoku[row][col] == num) return false;
            }
        }
        return true;
    }

    static void printSudoku() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < sudoku.length; row++) {
            for (int col = 0; col < sudoku[0].length; col++) {
                sb.append(sudoku[row][col]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static class Blank {
        private int row;
        private int col;

        public Blank(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}