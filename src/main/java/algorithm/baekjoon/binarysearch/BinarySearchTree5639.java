package algorithm.baekjoon.binarysearch;

import java.io.*;

public class BinarySearchTree5639 {
    static StringBuilder sb = new StringBuilder();

    static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        Node rootNode = null;

        while (!(input = br.readLine()).isEmpty()) {
            int num = Integer.parseInt(input);
            if(rootNode == null){
                rootNode = new Node(num);
             }
            else{
                insert(rootNode, num);
            }
        }
        postOrder(rootNode);
        System.out.print(sb);
    }

    static void insert(Node node, int num){
        if(node.data < num){
            if(node.right == null){
                node.right = new Node(num);
            }
            else insert(node.right, num);
        }
        else if (node.data > num){
            if(node.left == null){
                node.left = new Node(num);
            }
            else insert(node.left, num);
        }
    }

    static void postOrder(Node node){
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            sb.append(node.data).append("\n");
        }
    }
}