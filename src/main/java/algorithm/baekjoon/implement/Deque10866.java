package algorithm.baekjoon.implement;

import java.io.*;

public class Deque10866 {
    static int[] arr;
    static int rear = 0;
    static int front = 0;
    static int count = 0;

    static void push_front(int num){
        if(count == 0) {
            arr[rear++] = num;
            count++;
        }
        else{
            int[] newarr = new int[arr.length];
            System.arraycopy(arr,0,newarr,1,count);
            newarr[0] = num;
            arr = newarr;
            count++; rear++;
        }
    }

    static void push_back(int num){
        arr[rear++] = num;
        count++;
    }

    static int pop_front(){
        if(count == 0) return -1;
        else{
            int num = arr[front];
            count--; rear--;
            int[] newarr = new int[arr.length];
            System.arraycopy(arr,1,newarr,0,count);
            arr = newarr;
            return num;
        }
    }

    static int pop_back(){
        if(count == 0) return -1;
        else{
            int num = arr[rear-1];
            arr[rear-1] = 0;
            rear--; count--;
            return num;
        }
    }

    static int size(){
        return count;
    }

    static int empty(){
        if(count == 0) return 1;
        else return 0;
    }

    static int front(){
        if(count == 0) return -1;
        else return arr[front];
    }

    static int back(){
        if(count == 0) return -1;
        else return arr[rear-1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new int[n];
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n; i++){
            String[] command = br.readLine().split(" ");

            if(command[0].equals("push_front")) {
                int num = Integer.parseInt(command[1]);
                push_front(num);
            }
            if(command[0].equals("push_back")) {
                int num = Integer.parseInt(command[1]);
                push_back(num);
            }
            if(command[0].equals("pop_front")) {
                sb.append(pop_front()).append("\n");
            }
            if(command[0].equals("pop_back")) {
                sb.append(pop_back()).append("\n");
            }
            if(command[0].equals("size")) {
                sb.append(size()).append("\n");
            }
            if(command[0].equals("empty")) {
                sb.append(empty()).append("\n");
            }
            if(command[0].equals("front")) {
                sb.append(front()).append("\n");
            }
            if(command[0].equals("back")) {
                sb.append(back()).append("\n");
            }
        }
        System.out.print(sb);
    }
}
