package algorithm.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

/*
    풀이:
    내 투표수와 다른사람의 투표수 배열를 받았을때
    다른사람의 투표수 배열을 내림차순 정렬하여
    제일 큰 0번째 인덱스에서 계속가져와서 내 투표수가 많아질때까지 반복하였다.

    다른사람의 풀이에서 배울점:
    자바에서 int[]의 내림차순 정렬은 너무 불편하다 Integer[]로 바꿔서 정렬후 int[]로 바꿔야한다.
    다른분의 풀이를 보니 오름차순 정렬해서 끝값과 비교하였다 나는 왜 저런생각을 못햇을까 ㅠㅠ

 */
public class Election1417 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int personNum = sc.nextInt();

        // 나를 제외한 각특표수 배열
        int[] votesNum = new int[personNum+10];
        int count = 0, max = 0;
        int me = sc.nextInt();

        //배열의 크기가 0이면 예외
        if(votesNum.length == 0) {
            System.out.println("0");
            return;
        }

        //각 투표수를 배열에 넣는다.
        for(int i=0; i<personNum-1; i++)
            votesNum[i] = sc.nextInt();

        //내림차순 정렬한다.
        Integer[] votesNumInte =  Arrays.stream(votesNum).boxed().toArray(Integer[]::new);
        Arrays.sort(votesNumInte, Collections.reverseOrder());
        votesNum = Stream.of(votesNumInte).mapToInt(Integer::valueOf).toArray();

        //내림차순중 가장큰것과 자신의 투표수를 비교한다.
        if (me <= votesNum[0]) max = votesNum[0];
        else max = me;

        //내투표수가 가장 많아질때 까지 [0] 원소에서 투표수를 가져오고 만약 [0] < [1] 번째 인덱스가 투표수가 더많다면 다시 내림차순 정렬한다.
        while ( !(votesNum[0] < me) ){
            me++;
            votesNum[0]--;
            count++;
        if( votesNum[0] < votesNum[1]) {
            votesNumInte = Arrays.stream(votesNum).boxed().toArray(Integer[]::new);
            Arrays.sort(votesNumInte, Collections.reverseOrder());
            votesNum = Stream.of(votesNumInte).mapToInt(Integer::valueOf).toArray();
            }
        }
        System.out.println(count);
    }
}
