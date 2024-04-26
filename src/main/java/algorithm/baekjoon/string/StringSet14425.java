package algorithm.baekjoon.string;

import java.util.*;

public class StringSet14425 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        Set<String> nSet = new HashSet<>();

        int count = 0;
        for(int i=0; i<n; i++){
            nSet.add(sc.nextLine());
        }
        for(int i=0; i<m; i++){
            String input = sc.nextLine();
            if(nSet.contains(input)) count++;
        }
        System.out.print(count);
    }
}
