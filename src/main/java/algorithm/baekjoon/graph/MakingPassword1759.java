package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class MakingPassword1759 {
    static List<Character> myPasswordList;

    static char[] charArr;
    static int passwordLength, alphabetLength;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] CL = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        passwordLength = CL[0]; alphabetLength = CL[1];

        charArr = new char[alphabetLength];
        myPasswordList = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < alphabetLength; i++){
            charArr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(charArr);
        dfs(0,0);

        System.out.print(sb);
    }

    static void dfs(int count, int index){

        if(count == passwordLength){
            print();
            return;
        }

        for(int i = index; i < charArr.length; i++){
            myPasswordList.add(charArr[i]);
            dfs(count + 1, i + 1);
            myPasswordList.remove(myPasswordList.size() - 1);
        }
    }

    static void print(){
        int count = 0;
        for (char c : myPasswordList) {
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') count++;
        }

        if(count < 1 || passwordLength - count < 2) return;
        else{
            for (Character c : myPasswordList) {
                sb.append(c);
            }
        }
        sb.append("\n");
    }
}