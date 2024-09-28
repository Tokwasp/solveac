package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class Tree1068 {
    static List<List<Integer>> tree;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        tree = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        int root = -1;
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
            } else {
                tree.get(parent).add(i);
            }
        }

        int deleteNodeNum = Integer.parseInt(br.readLine());
        deleteNode(deleteNodeNum, root);

        if (deleteNodeNum == root) System.out.println(0);
        else System.out.println(count);

    }

    static void deleteNode(int deleteNodeNum, int root) {
        List<Integer> list = tree.get(root);

        if (list.contains(deleteNodeNum)) {
            list.remove(Integer.valueOf(deleteNodeNum));
        }
        if (list.isEmpty()) count++;

        for (int nextNode : list) {
            deleteNode(deleteNodeNum, nextNode);
        }
    }
}