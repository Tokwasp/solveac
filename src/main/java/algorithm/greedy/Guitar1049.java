package algorithm.greedy;

import java.util.Arrays;
import java.util.Scanner;

/*
  풀이:
  기타줄을 최소 가격으로 사려면 정렬한후에 six[0] solo[0] 값만 비교 하면 된다.
  끊어진 기타줄의 개수가 6개 이상일때와 6개 미만일때로 나누었고
  6묶음이 낱개6개 사는것보다 비싸면 낱개로 구매하고 아니면 6개묶음으로 구매 하도록 하였다.

  다른사람의 풀이에서 배울점:
  대부분 다른사람들은 Math.min()을 사용하여 풀었다.
  Math.min()을 통해 푸니 경우의수가 잘보여서 가독성이 더 좋았다.
  1.나머지가 남아도 6개 묶음으로 다사기 ex) (N/6+1) * six[0]
  2.낱개로 다사기
  3.6개 묶음으로 사고 나머지를 낱개로 사기
  로 나누어서 푸셨다.

 */
public class Guitar1049 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int brand = sc.nextInt();
        int[] six = new int[brand];
        int[] solo = new int[brand];
        int money = 0;

        for(int i=0; i<brand; i++){
            six[i] = sc.nextInt();
            solo[i] = sc.nextInt();
        }
        Arrays.sort(six);
        Arrays.sort(solo);

        while(N != 0){
            // 끊어진 기타줄이 6개 이상일때
            if(N/6 >= 1 && six[0] <= solo[0]*6){
                money += N/6 * six[0];
                N %= 6;
                continue;
            }
            if(N/6 >= 1 && six[0] > solo[0]*6) money += solo[0] * N;
            //끊어진 기타줄이 6개 미만인경우
            if(N/6 < 1 && six[0] <= solo[0]*N) money += six[0];
            if(N/6 < 1 && six[0] > solo[0]*N) money += solo[0] * N;
            N =0;
        }
        System.out.print(money);
    }
}
