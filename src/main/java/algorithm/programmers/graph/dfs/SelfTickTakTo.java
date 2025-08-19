package algorithm.programmers.graph.dfs;

public class SelfTickTakTo {
    private static char[][] map;
    private static char[][] originalMap;
    private static boolean isSameMap = false;

    public int solution(String[] board) {
        map = new char[board.length][board.length];
        originalMap = new char[board.length][board.length];

        // 입력 받기
        int shapeCount = 0;
        for(int i = 0; i < board.length; i++){
            String input = board[i];

            for(int j = 0; j < input.length(); j++){
                char shape = input.charAt(j);
                originalMap[i][j] = shape;

                if(shape != '.'){
                    shapeCount++;
                }
            }
        }

        // map 초기화
        for(int row = 0; row < map.length; row++){
            for(int col = 0; col < map[0].length; col++){
                map[row][col] = '.';
            }
        }

        dfs(0, shapeCount);
        return isSameMap ? 1 : 0;
    }

    private static void dfs(int depth, int targetDepth){
        if(isSameMap){
            return;
        }

        if(depth == targetDepth || isWinner('O') || isWinner('X')){
            if(isSameMapCheck()){
                isSameMap = true;
            }
            return;
        }

        for(int row = 0; row < map.length; row++){
            for(int col = 0; col < map[0].length; col++){
                if(map[row][col] == '.'){
                    char shape = depth % 2 == 0 ? 'O' : 'X';

                    map[row][col] = shape;
                    dfs(depth + 1, targetDepth);
                    map[row][col] = '.';
                }
            }
        }
    }

    private static boolean isSameMapCheck(){
        for(int row = 0; row < map.length; row++){
            for (int col = 0; col < map[0].length; col++) {
                if(map[row][col] != originalMap[row][col]){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isWinner(char shape){
        // 가로
        for(int row = 0; row < map.length; row++){
            if(map[row][0] == shape && map[row][1] == shape && map[row][2] == shape){
                return true;
            }
        }

        // 세로
        for(int col = 0; col < map[0].length; col++){
            if(map[0][col] == shape && map[1][col] == shape && map[2][col] == shape){
                return true;
            }
        }

        // 대각선
        if(map[0][0] == shape && map[1][1] == shape && map[2][2] == shape){
            return true;
        }

        if(map[0][2] == shape && map[1][1] == shape && map[2][0] == shape){
            return true;
        }

        return false;
    }
}
