package algorithm.baekjoon;

import java.io.*;
import java.util.*;

public class PhoneNumberList5052 {

    static class Node{
        // 현재 char 값과 자식 노드
        Map<Character, Node> childNode = new HashMap<>();
        // 단어의 끝 인지 체크
        boolean endOfWord;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int N = Integer.parseInt(br.readLine());

            // 루트 노트 생성
            Node rootNode = new Node();
            // 문자열 담을 Queue
            Queue<String> q = new LinkedList<>();

            for (int j = 0; j < N; j++) {
                String present = br.readLine();
                q.add(present);

                Node node = rootNode;
                for(int k = 0; k < present.length(); k++){
                    node = node.childNode.computeIfAbsent(present.charAt(k), key -> new Node());
                }
                node.endOfWord = true;
            }

            // Trie 에서 문자열 검색
            boolean consistent = false;

            while(!q.isEmpty()){
                Node searchNode = rootNode;
                String pollString = q.poll();

                for(int j = 0; j < pollString.length(); j++){
                    searchNode = searchNode.childNode.get(pollString.charAt(j));
                    if(searchNode.endOfWord && j != pollString.length() - 1){
                        consistent = true;
                        break;
                    }
                }

                if(consistent) break;
            }
            if(consistent) sb.append("NO").append("\n");
            else sb.append("YES").append("\n");
        }
        System.out.println(sb);
    }
}
