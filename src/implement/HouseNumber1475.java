package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HouseNumber1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] StringInput =br.readLine().split("");
        int[] input = Arrays.stream(StringInput).mapToInt(Integer::parseInt).toArray();

        ArrayList N = new ArrayList();
        for(int i=0; i<input.length; i++) N.add(input[i]);

        Collections.replaceAll(N,6,9);
        List list = new ArrayList();
        List pack = new ArrayList();
        int count = 0;

        for(int i=0; i<N.size(); i++){
            if(!list.contains(N.get(i))){
                pack = Stream.iterate(0, n -> n +1 ).limit(10).collect(Collectors.toList());
                list.addAll(pack);
                Collections.replaceAll(list,6,9);
                count++;
            }
            list.remove(N.get(i));
        }
        System.out.println(count);
    }
}
