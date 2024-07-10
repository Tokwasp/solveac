package algorithm;

import java.io.*;
import java.util.stream.Stream;

public class ConveyorBelt20055 {
    static int[] durability;
    static boolean[] inRobot;
    static int N, K;
    static int index = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NK = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = NK[0]; K = NK[1];

        durability = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        inRobot = new boolean[2*N];

        int count = 0;

        while(true){
            move();
            count = checkZeroDurability();
            index++;

            if(count >= K) break;
        }

        System.out.println(index);
    }
    static void move(){

        // 회전 시키기
        int lastDurability = durability[durability.length - 1];
        boolean lastInRobot = inRobot[inRobot.length - 1];

        System.arraycopy(durability, 0, durability, 1, durability.length - 1);
        System.arraycopy(inRobot, 0, inRobot, 1, inRobot.length - 1);

        inRobot[N-1] = false;
        durability[0] = lastDurability; inRobot[0] = lastInRobot;


        // 2. 로봇이 움직일 수 있다면 한칸 이동

        //끝 인덱스 처리
        if(inRobot[durability.length - 1] && !inRobot[0] && durability[0] != 0){
            inRobot[0] = true;
            inRobot[durability.length - 1] = false;
            durability[0] -= 1;
        }

        for(int i = durability.length - 2; i >= 0; i--){
            if(!inRobot[i+1] && inRobot[i] && durability[i+1] != 0){
                inRobot[i+1] = true;
                inRobot[i] = false;
                durability[i+1] -= 1;

                if(i + 1 == N-1) inRobot[i + 1] = false;
            }
        }

        // 3. 첫째 칸에 로봇 올리기
        if(!inRobot[0] && durability[0] != 0) {
            inRobot[0] = true; durability[0] -= 1;
        }

    }

    static int checkZeroDurability(){
        int count = 0;
        for (int i : durability) {
            if(i == 0) count++;
        }
        return count;
    }
}