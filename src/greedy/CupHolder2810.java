package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
   풀이:
   양옆은 무조건 컵홀더가 있다 2개
   사이에 있는 컵홀더의 갯수는 S LL 의 갯수 -1 이다
   총 컵홀더의 갯수는 S LL +1 이다.

   처음에 밑에 풀이로 풀었으나 틀렸다고 하여서 다른방식으로 삽질을 했다...
   다른방식으로 풀어서 맞았지만 직관적이지 않고 보기힘든 코드를 풀었다.
   문제가 틀렸을때는 당황하지말고 문제를 다시읽어보고 내가 놓친게 있는지 천천히 확인 하는 습관이 필요하다.

 */
public class CupHolder2810 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());
        String seatOrder = br.readLine();
        int count = 0;
        int skip = 0;

        for(int i=0; i<seatOrder.length(); i+=skip){
            if(seatOrder.charAt(i) == 'L') {
                count++;
                skip = 2;
            }
            if(seatOrder.charAt(i) == 'S') {
                count++;
                skip =1;
            }
        }
        if( count+1 > number )
            System.out.println(number);
        else
            System.out.println(count+1);
    }
}
