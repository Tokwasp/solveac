package algorithm.baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
   풀이 :
   6을 9로 바꾸어서 풀었다.
   배열을 돌며 9가 없다면 0~9 배열을 넣고 6을 다시 9로 바꿔주었다.
 */
public class HouseNumber1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] StringInput =br.readLine().split("");
        StringBuilder sb = new StringBuilder();
        int[] input = Arrays.stream(StringInput).mapToInt(Integer::parseInt).toArray();

        ArrayList N = new ArrayList();
        for(int i=0; i<input.length; i++) N.add(input[i]);

        Collections.replaceAll(N,6,9);
        List list = new ArrayList();
        Integer[] pack = new Integer[]{0,1,2,3,4,5,9,7,8,9};
        int count = 0;

        for(int i=0; i<N.size(); i++){
            if(!list.contains(N.get(i))){
                Collections.addAll(list,pack);
                count++;
            }
            list.remove(N.get(i));

        }
        System.out.println(sb.append(count));
    }
}
