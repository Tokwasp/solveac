package algorithm.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//현재 N층 K호는 (N-1층의 K호수) + (N층의 (K-1) 호수의 사람수)가 합쳐진 값입니다.
public class Chairman2775 {
        public static void main(String[] args) throws IOException {
            //층과 호수 입력받기
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int repeat = Integer.parseInt(br.readLine());
            List<Integer> floorList = new ArrayList<>();

            //반복수 만큼 입력받기 list add
            for(int i=0; i<repeat; i++){
                int kFloor = Integer.parseInt(br.readLine());
                int nHo = Integer.parseInt(br.readLine());
                floorList.add(kFloor);
                floorList.add(nHo);
            }
            //반복수 만큼 결과값 출력
            for (int i=0; i<repeat; i++)
                System.out.println(floorCalculate(floorList.get(2*i),floorList.get(2*i+1)));
        }
        static int floorCalculate(int x, int y){
            //0층의 사람수
            int[] zeroFloor = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
            int[] beforeFloor = new int[15];
            int[] nowFloor = new int[15];

            //1층의 이전층은 0층이므로 0층의 값들을 옮겨줍니다.
            System.arraycopy(zeroFloor, 0, beforeFloor, 0 , zeroFloor.length);
            //X층수만큼 반복
            for(int i=0; i < x; i++) {
                //1호는 무조건 1명
                beforeFloor[0] = 1;
                nowFloor[0] = 1;
                //Y호수 만큼 반복
                for (int j = 1; j < y; j++) {
                    //현재의 j호는 이전층의 j호 + 현재층의 j-1호의 사람수를 합친것입니다.
                    nowFloor[j] = beforeFloor[j] + nowFloor[j-1];
                }
                //현재층의 계산을 끝내고 다음층으로 가기전 현재층의 사람수를 이전층으로 옮겨줍니다.
                System.arraycopy(nowFloor, 0 , beforeFloor, 0 , nowFloor.length);
            }
            return nowFloor[y-1];
        }
    }
