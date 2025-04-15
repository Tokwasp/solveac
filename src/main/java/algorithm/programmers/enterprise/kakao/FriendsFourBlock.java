package algorithm.programmers.enterprise.kakao;

import java.util.ArrayList;
import java.util.List;

public class FriendsFourBlock {
    static char[][] cellMap;
    static int dx[] = {0,1,1};
    static int dy[] = {1,0,1};

    public int solution(int row, int col, String[] board) {
        cellMap = new char[row][col];

        for(int boardRow = 0; boardRow < board.length; boardRow++){
            String rowInput = board[boardRow];

            for(int boardCol = 0; boardCol < rowInput.length(); boardCol++){
                char ch = rowInput.charAt(boardCol);
                cellMap[boardRow][boardCol] = ch;
            }
        }

        int totalRemovedCellsCount = 0;
        while(true){
            List<Cell> cellsToRemove = findCellsToRemove();
            int removedCellsCount = removeCellsReturnCount(cellsToRemove);
            if(removedCellsCount == 0){
                break;
            }
            totalRemovedCellsCount += removedCellsCount;
            downCells();
        }

        return totalRemovedCellsCount;
    }

    static List<Cell> findCellsToRemove(){
        List<Cell> cells = new ArrayList<>();

        for(int row = 0; row < cellMap.length - 1; row++){
            for(int col = 0; col < cellMap[0].length - 1; col++){
                char standardChar = cellMap[row][col];
                if(standardChar == 'x') {
                    continue;
                }

                boolean pass = true;
                for(int repeat = 0; repeat < 3; repeat++){
                    int checkRow = row + dx[repeat];
                    int checkCol = col + dy[repeat];

                    if(standardChar != cellMap[checkRow][checkCol]){
                        pass = false;
                        break;
                    }
                }

                if(pass){
                    cells.add(new Cell(row,col));
                    for(int repeat = 0; repeat < 3; repeat++){
                        int checkRow = row + dx[repeat];
                        int checkCol = col + dy[repeat];

                        cells.add(new Cell(checkRow,checkCol));
                    }
                }
            }
        }
        return cells;
    }

    static int removeCellsReturnCount(List<Cell> cells){
        int count = 0;
        for(int i = 0; i < cells.size(); i++){
            Cell cell = cells.get(i);
            if(cellMap[cell.row][cell.col] != 'x'){
                cellMap[cell.row][cell.col] = 'x';
                count++;
            }
        }
        return count;
    }

    static void downCells(){
        for(int row = cellMap.length -1; row > 0; row--){
            for(int col = 0; col < cellMap[0].length; col++){
                if(cellMap[row][col] != 'x'){
                    continue;
                }

                int moveRow = 0;
                for(int move = row -1; move >= 0; move--){
                    if(cellMap[move][col] != 'x'){
                        moveRow = move;
                        break;
                    }
                }

                cellMap[row][col] = cellMap[moveRow][col];
                cellMap[moveRow][col] = 'x';
            }
        }
    }

    static class Cell{
        int row;
        int col;

        public Cell(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}