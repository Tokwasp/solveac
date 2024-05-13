package algorithm.swea.d3;

import java.io.IOException;
import java.util.Scanner;

public class NewCalculation {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int testCase = sc.nextInt();

        for(int t = 1; t <= testCase; t++){
            int p = sc.nextInt();
            int q = sc.nextInt();

            int pSum = 0;
            int pX = 0;
            int pY = 0;
            int pDiagonal = 1;
            while(true){
                pSum += pDiagonal;
                if(pSum >= p){
                    pX = pDiagonal - (pSum - p);
                    pY = (pDiagonal + 1) - pX;
                    break;
                }
                pDiagonal++;
            }

            int qSum = 0;
            int qX = 0;
            int qY = 0;
            int qDiagonal = 1;
            while(true){
                qSum += qDiagonal;
                if(qSum >= q){
                    qX = qDiagonal - (qSum - q);
                    qY = (qDiagonal + 1) - qX;
                    break;
                }
                qDiagonal++;
            }

            int newX = pX + qX; int newY = pY + qY;
            int newSum = 0;
            int newDiagonal = newX + newY - 1;

            for(int i=1; i <= newDiagonal; i++){
                newSum += i;
            }
            newSum -= (newDiagonal - newX);

           String st = "#" + t + " " + newSum;
           sb.append(st).append("\n");
        }
        System.out.print(sb);
    }
}
