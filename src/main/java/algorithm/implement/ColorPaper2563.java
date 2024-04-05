package algorithm.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ColorPaper2563 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        //좌표 0~99 가로 100 0~99 세로100인 배열
        int[][] Arr = new int[100][100];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            // 최댓값 90이 들어오면 90~99까지 1로 채워진다.
            for(int j=X; j<X+10; j++){
                for(int k=Y; k<Y+10; k++){
                    Arr[j][k] = 1;
                }
            }
        }
        int sum = 0;
        for(int i=0; i<Arr.length; i++){
            for(int j=0; j<Arr[i].length; j++){
                if(Arr[i][j] == 1) sum++;
            }
        }
        System.out.println(sum);
    }
}
