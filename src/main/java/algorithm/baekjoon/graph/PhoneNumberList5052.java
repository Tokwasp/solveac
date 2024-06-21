package algorithm.baekjoon.graph;

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

                // 현재 노드 : rootNode ->
                Node node = rootNode;
                for(int k = 0; k < present.length(); k++){
                    // 자식 노드에 값이 없을 경우 새 노드 만들기
                    node = node.childNode.computeIfAbsent(present.charAt(k), key -> new Node());
                }
                //마지막 노드를 알리기 위한 변수
                node.endOfWord = true;
            }

            // Trie 에서 접두어 검색
            boolean consistent = false;

            while(!q.isEmpty()){
                // 찾을 노드의 참조
                Node searchNode = rootNode;
                String pollString = q.poll();

                for(int j = 0; j < pollString.length(); j++){
                    searchNode = searchNode.childNode.get(pollString.charAt(j));

                    //노드의 마지막 부분 이지만 현재 찾는 문자가 끝이 아닐 경우 접두어 입니다.
                    //ex) cacao,cacaoTree 가 있고 cacaoTree 찾는 다면 cacao 일때 노드의 마지막 부분 이고 cacaoTree 찾는 중 이므로
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
