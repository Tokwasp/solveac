package algorithm.swea.d3;

import java.io.*;
import java.util.StringTokenizer;

public class DietDp{

    static int N,L;
    static int[] score;
    static int[] cal;
    static int[][] d;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());

        for(int t_c=1;t_c<=T;t_c++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            N=Integer.parseInt(st.nextToken());
            L=Integer.parseInt(st.nextToken());

            score=new int[N+1];  //점수
            cal=new int[N+1]; //칼로리
            d=new int[N+1][L+1];//해당 칼로리 일 때의 점수 값

            for(int i=1;i<=N;i++) {
                st=new StringTokenizer(br.readLine());
                score[i]=Integer.parseInt(st.nextToken());
                cal[i]=Integer.parseInt(st.nextToken());
                d[i][cal[i]]=score[i];
            }

            for(int i=1;i<=N;i++) {
                for(int j=1;j<=L;j++) {
                    if(cal[i]>j) d[i][j]=d[i-1][j];
                    else
                        d[i][j]=Math.max(d[i-1][j],d[i-1][j-cal[i]]+score[i]);
                }
            }

//            System.out.print("1번째 최대 칼로리 100 일때 점수: ");
//            System.out.println(d[1][100]);
//            System.out.print("2번째 최대 칼로리 200 일때 점수: ");
//            System.out.println(d[2][200]);
//            System.out.print("2번째 최대 칼로리 500 일떄 점수: ");
//            System.out.println(d[2][500]);
//            System.out.print("2번째 최대 칼로리 700 일떄 점수: ");
//            System.out.println(d[2][700]);

            System.out.println("#"+t_c+" "+d[N][L]);
        }
    }
}
