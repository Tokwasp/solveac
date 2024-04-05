package algorithm.implement;

import java.io.IOException;
import java.util.Scanner;

public class Queue10845 {
    static int front = 0;
    static int back = -1;
    static int size = 0;
    static int[] arr;

    private static class Queue{

        static void push(int num){
            arr[size++] = num;
        }

        static int empty(){
            if(arr[front] == 0) return 1;
            else return 0;
        }

        static int pop(){
            int num = 0;
            int[] cloneArr = new int[10000];

            if(size != 0){
                num = arr[front];
                arr[front] = 0;
                System.arraycopy(arr, front+1, cloneArr, front, size);
                arr = cloneArr;
                size--;
                return num;
            }

            else return -1;
        }

        static int front(){
            if(size != 0)  return arr[front];
            else return -1;
        }

        static int back(){
            if(size != 0) return arr[size-1];
            else return -1;
        }

        static int size(){
            return size;
        }

    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        String comand = "";
        arr = new int[10000];

        for(int i=0; i<n; i++){
            comand = sc.next();
            if(comand.equals("push")){
                Queue.push(sc.nextInt());
            }
            if(comand.equals("empty")){
                sb.append(Queue.empty()).append("\n");
            }
            if(comand.equals("pop")){
                sb.append(Queue.pop()).append("\n");
            }
            if(comand.equals("front")){
                sb.append(Queue.front()).append("\n");
            }
            if(comand.equals("back")){
                sb.append(Queue.back()).append("\n");
            }
            if(comand.equals("size")){
                sb.append(Queue.size()).append("\n");
            }
        }
        System.out.print(sb);
    }
}
