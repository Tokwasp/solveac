package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 공백으로 split 하여 배열에 담아 길이를 구하면 끝나는 문제라 생각햇는데
   이 문제는 첫공백, 끝공백을 어떻게 처리하냐를 묻는 문제다.
   처음과 끝에서 공백일경우 제거해야하므로 양방향 접근되는 LinkedList을 사용하면 되지않을까 한다.
   문제를 풀다보니 알게된것은 공백 단위로 끊기때문에 첫공백만 생각하면 된다.
   처음들어간것을 제거해야하므로 큐를 써도 될것같다.
 */
public class WordCount1152 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("\\s");

        //LimkedList에 input[] 배열 넣기
        LinkedList<String> linkedList = new LinkedList<>();
        Collections.addAll(linkedList,input);

        //첫문자가 공백이라면 제거
        if(!linkedList.isEmpty() && linkedList.get(0).isEmpty())
            linkedList.removeFirst();

        System.out.println(linkedList.size());

    }
}
