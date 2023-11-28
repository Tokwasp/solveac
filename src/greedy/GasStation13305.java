package greedy;

import java.io.*;
import java.util.StringTokenizer;

/*
   풀이 :
   문제를 읽고 안것은 기름은 거리를 이동 하기 전에 가장 싼 곳에서 넣어야 한다는 것이다.
   처음의 거리는 무조건 첫 주유소 에서 넣어야 하고, 다음 거리는 거리를 이동 하기 전 주요소 중 가격이 싼것을 넣으면 된다.
 */

public class GasStation13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        long Totalprice = 0;
        long minOilPrice = 0;
        int[] dist = new int[N-1];
        int[] oilPrice = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N-1; i++) dist[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) oilPrice[i] = Integer.parseInt(st.nextToken());

        minOilPrice = oilPrice[0];
        for(int i=0; i<N-1; i++){
            Totalprice += dist[i] * minOilPrice;
            minOilPrice = Math.min(minOilPrice, oilPrice[i+1]);
        }
        System.out.println(Totalprice);
    }
}
