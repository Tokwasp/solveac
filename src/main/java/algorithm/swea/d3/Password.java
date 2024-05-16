package algorithm.swea.d3;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/*
    문제 풀이:

    풀이 방법 : 구현

    시간 복잡도 1개 = 2초 이고 문자열의 최대 길이는 100이다.
    List.remove()를 사용 하면 System.arraycopy()가 호출 되고 배열을 옮기게 된다.
    System.arraycopy()의 시간 복잡도는 n 이다.
    길이가 100인 list의 맨앞 부분을 50번 제거 할 경우 아무리 커도 100 * 50 일 것이다. (2개씩 제거)

    패스워드의 한 자리씩 잘라서, List에 넣는다.
    패스워드에 중복된 수가 있는지 체크 한다. ( for문을 돌며 이전 수는 prev 현재는 present가 된다. )
    중복된 수가 있을 경우, 해당 두 인덱스를 삭제하고 함수를 종료한다.
    while문을 통해 다시 함수가 호출 되고, 반복 한다.
    List의 길이-1 까지 탐색 하였을 때, 중복된 수가 없으므로 pass = true로 while문이 종료 된다.
 */
public class Password {
    static List<Integer> passwordList;
    static boolean pass;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= 10; t++) {
            String[] lengthAndPassword = Stream.of(br.readLine().split(" ")).toArray(String[]::new);

            int length = Integer.parseInt(lengthAndPassword[0]);
            String passWord = lengthAndPassword[1];

            passwordList = new ArrayList<>();

            for(int i = 0; i < length; i++) {
                passwordList.add(passWord.charAt(i) - '0');
            }

            pass = false;

            while(!pass) {
                check();
            }

            String st = "#" + t + " ";
            sb.append(st);
            for(int num : passwordList) {
                sb.append(num);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void check() {
        int prev = passwordList.get(0);
        int prevIndex = 0;

        for(int i = 1; i < passwordList.size(); i++) {
            int present = passwordList.get(i);
            int presentIndex = i;

            if(present == prev) {
                passwordList.remove(presentIndex);
                passwordList.remove(prevIndex);
                break;
            }

            prev = passwordList.get(i);
            prevIndex = i;

            //마지막 까지 온 경우는 중복된 수가 없을 경우 이다.
            if(i == passwordList.size() - 1) pass = true;
        }
    }
}
