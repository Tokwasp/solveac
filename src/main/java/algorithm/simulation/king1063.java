package algorithm.simulation;

import java.io.*;
import java.util.StringTokenizer;

/*
   King과 stone은 같은 행동을 한다.
   is-a 관계도 아니고 has-a 관계도 아니 므로 상속과 합성을 사용할 순 없다.
   King stone 객체를 둘다 만드는 것은 코드의 중복이 많기에 King 객체만 으로 해결 하였 는데,
   두 객체를 각각 나누 어서 코드의 재사용 할 수 있는 방법에 대해 찾아 봐야 겠다.
 */
public class king1063 {

    static class King{
        int row;
        int col;

        public King(int row, int col) {
            this.row = row;
            this.col = col;
        }

        String goBack = "";

        void move(String command){
            if(command.equals("R")){
                this.col += 1;
                goBack = "L";
            }
            if(command.equals("L")){
                this.col -= 1;
                goBack = "R";
            }
            if(command.equals("B")){
                this.row -= 1;
                goBack = "T";
            }
            if(command.equals("T")){
                this.row += 1;
                goBack = "B";
            }
            if(command.equals("RT")){
                this.row += 1;
                this.col += 1;
                goBack = "LB";
            }
            if(command.equals("LT")){
                this.row += 1;
                this.col -= 1;
                goBack = "RB";
            }
            if(command.equals("RB")){
                this.row -= 1;
                this.col += 1;
                goBack = "LT";
            }
            if(command.equals("LB")){
                this.row -= 1;
                this.col -= 1;
                goBack = "RT";
            }
        }
        void back(){
            move(goBack);
            goBack = "";
        }
        boolean out(){
            if(this.row == 0 || this.col == 0 || this.row > 8 || this.col > 8) return true;
            else return false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        String kingInput = st.nextToken();
        String stoneInput = st.nextToken();
        int n = Integer.parseInt(st.nextToken());

        //주어진 입력이 (열,행) 순이다
        int kingRow = kingInput.charAt(0) - 'A' + 1;
        int kingCol = kingInput.charAt(1) - '0';
        King king = new King(kingCol,kingRow);

        int stoneRow = (int)stoneInput.charAt(0) - 'A' + 1;
        int stoneCol = (int)stoneInput.charAt(1) - '0';
        King stone = new King(stoneCol,stoneRow);
        StringBuilder sb =new StringBuilder();

        for(int i=0; i<n; i++){
            String comand = br.readLine();
            king.move(comand);

            //킹이 체스판 밖을 나갈경우 다시 되돌아 간다.
            if(king.out()) {
                king.back();
                continue;
            }

            //킹과 스톤의 행열이 같다면 만난 것 이므로 스톤을 움직 인다.
            if((king.row == stone.row) && (king.col == stone.col)) {
                stone.move(comand);
                //스톤이 체스판 밖으로 나갈 경우
                if(stone.out()){
                    stone.back();
                    king.back();
                }
            }
        }
        sb.append((char)(king.col+64)).append(king.row).append("\n");
        sb.append((char)(stone.col+64)).append(stone.row);
        System.out.println(sb);
    }
}
