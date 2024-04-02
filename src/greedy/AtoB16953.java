package greedy;

import java.util.Scanner;

public class AtoB16953 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int count = 1;

        while(a < b){
          if(b % 2 == 0) {
              b /= 2;
          }
          else {
              if(b % 10 == 1) {
                  b /= 10;
              }
              else break;
          }
            count++;
        }
        if(a != b) System.out.print(-1);
        else System.out.print(count);
    }
}
