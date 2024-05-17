package algorithm.swea.d3;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/*
    문제 풀이 :

    풀이 방법 : 구현

    특정 index에 숫자를 넣고, 그뒤 숫자 배열을 한칸씩 미는 문제 입니다.
    출력은 앞의 10개의 숫자 입니다. -> 10개 뒤론 의미가 없기 때문 입니다.

    크기가 11인 List를 만듭니다. ( 10개의 숫자에 하나가 들어올 시 11개가 되기 때문 )
    10개 이상의 숫자가 들어 와도 앞의 10개만 List에 넣습니다.
    명령어 라인을 받고, 암호를 특정 index에 넣고 움직 이는 함수를 실행 합니다.

    넣을 int[] 배열을 만들고, int 배열의 각 num 에 대해서
    startIndex 부터 list.add(num, startIndex) 합니다.
    List(int index, Element e) 함수는 index에 (Element e) 를 넣고, 그 뒤에 수를 한칸씩 밀어 줍니다.(System.arraycopy())
    넣은 후에 10번째 인덱스는 필요가 없으 므로 삭제 합니다. (List 뒤 에서 접근 시 시간 복잡도(1) 입니다.)
    넣은 후에 다음 index 인 startIndex++; 하고 int[] 배열을 다 넣을 때 까지 실행 합니다.
    단, 넣을 index가 마지막 인덱스 이상인 경우, 출력 값이 바뀌지 않기 때문에 return; 합니다.
 */
public class EightPassword {
    static String[] commandInput;
    static List<Integer> list;
    static int[] moveArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= 10; t++) {

            int N = Integer.parseInt(br.readLine());

            list = new ArrayList<>(11);

            //읽어 온 암호 배열
            int[] passwordInput = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            //10개 까지만 list에 넣습니다.
            for(int i = 0; i < 10; i++) {
                list.add(passwordInput[i]);
            }

            //명령어 수
            int commandNum = Integer.parseInt(br.readLine());

            //명령어 라인을 받습니다.
            commandInput = Stream.of(br.readLine().split(" ")).toArray(String[]::new);

            //"I" 문자를 발견 할시 암호를 넣고 움직 이는 함수를 실행 합니다.
            for(int i = 0; i < commandInput.length; i++) {
                if(commandInput[i].equals("I")) {
                    fillAndMove(i + 1);
                }
            }

            String st = "#" + t + " ";
            sb.append(st);

           for(int i = 0; i < 10; i++){
               sb.append(list.get(i)).append(" ");
           }

            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void fillAndMove(int index) {

        int moveStartIndex = Integer.parseInt(commandInput[index]);
        int moveNumCount = Integer.parseInt(commandInput[index + 1]);

        //마지막 index 이상에 넣을시 return;
        if(moveStartIndex >= 10) return;

        moveArr = new int[moveNumCount];

        for(int i = 1; i <= moveArr.length; i++) {
            moveArr[i - 1] = Integer.parseInt(commandInput[index + 1 + i]);
        }

        //마지막 index 이상 일시 return;
        //list에 int[] 배열의 값을 넣고 마지막 index를 삭제 합니다.
        for(int num : moveArr) {
            if(moveStartIndex >= 10) return;
            list.add(moveStartIndex, num);
            list.remove(10);
            moveStartIndex++;
        }
    }
}
