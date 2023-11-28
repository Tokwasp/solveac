package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
   풀이 :
   각 로프는 w/k의 중량이 걸린다.
   예를 들면 4 5 중량을 버티는 로프로 8kg을 들때 각 로프는 4kg 까지만 들 수 있다.
   각 로프의 최대 중량은 작은 중량을 버티는 로프 * 개수 인 것이다.
 */
public class Rope2217 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] weight = new int[N];

        for(int i=0; i<N; i++) weight[i] = Integer.parseInt(br.readLine());

        Arrays.sort(weight);

        int maxWeight = 0, prior = 0;

        for(int i=0; i<N; i++) {
            maxWeight = weight[i] * (N - i);
            maxWeight = Math.max(prior, maxWeight);
            prior = maxWeight;
        }
        System.out.println(maxWeight);
    }
}
