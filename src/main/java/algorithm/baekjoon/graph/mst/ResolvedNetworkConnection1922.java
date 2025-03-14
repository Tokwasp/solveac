package algorithm.baekjoon.graph.mst;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class ResolvedNetworkConnection1922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int computerCount = Integer.parseInt(br.readLine());
        int connectCount = Integer.parseInt(br.readLine());

        // 초기화
        List<List<ComputerConnection>> computerConnections = new ArrayList<>();
        for(int i = 0; i <= computerCount; i++){
            computerConnections.add(new ArrayList<>());
        }

        PriorityQueue<ComputerConnection> priceAscPriorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(a -> a.price)
        );

        // 입력 받기
        for(int i = 0; i < connectCount; i++){
            int[] computerInput = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = computerInput[0];
            int end = computerInput[1];
            int price = computerInput[2];

            if(start == end) continue;
            ComputerConnection computerConnection = new ComputerConnection(start, end, price);
            computerConnections.get(start).add(computerConnection);
            priceAscPriorityQueue.add(computerConnection);
        }

        // 유니온 파인드 초기화
        int[] parents = new int[computerCount + 1];
        for(int i = 0; i < parents.length; i++){
            parents[i] = i;
        }

        int totalConnectionCount = 0;
        int minPrice = 0;

        while(!priceAscPriorityQueue.isEmpty()){
            ComputerConnection computerConnection = priceAscPriorityQueue.poll();
            int start = computerConnection.start;
            int end = computerConnection.end;

            if(isNotSameParents(parents, start, end)){
                union(parents, start, end);
                totalConnectionCount++;
                minPrice += computerConnection.price;
            }

            if(totalConnectionCount == computerCount) {
                break;
            }
        }
        System.out.print(minPrice);
    }

    public static boolean isNotSameParents(int[] parents, int start, int end){
        int startParents = getParent(parents, start);
        int endParents = getParent(parents, end);
        return startParents != endParents;
    }

    public static void union(int[] parents, int start, int end){
        int startParents = getParent(parents, start);
        int endParents = getParent(parents, end);

        if(startParents > endParents){
            parents[startParents] = endParents;
        }
        else{
            parents[endParents] = startParents;
        }
    }

    public static int getParent(int[] parents, int x){
        if(parents[x] == x) return x;
        return parents[x] = getParent(parents, parents[x]);
    }

    static class ComputerConnection{
        int start;
        int end;
        int price;

        public ComputerConnection(int start, int end, int price){
            this.start = start;
            this.end = end;
            this.price = price;
        }

    }
}