package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class ReSolvedDiet19942 {
    static int minPrice = Integer.MAX_VALUE;
    static List<Ingredient> inputIngredientList;
    static List<Integer> memoryIngredientNumberList;
    static StringBuilder resultSb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] targetInput = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Ingredient target = createIngredientWithoutPrice(targetInput);
        inputIngredientList = new ArrayList<>();
        memoryIngredientNumberList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Ingredient ingredient = createIngredient(input);
            inputIngredientList.add(ingredient);
        }

        dfs(target, createIngredient(allZeroInput()), N, 1);
        if(minPrice == Integer.MAX_VALUE){
            System.out.print("-1");
        }
        else {
            System.out.println(minPrice);
            System.out.print(resultSb);
        }
    }

    private static void dfs(Ingredient target, Ingredient prior, int N, int depth) {
        // 갱신 조건
        if(isSatisfied(target,prior)){
            if(minPrice > prior.price) {
                minPrice = prior.price;
                resultSb = new StringBuilder();
                copyIngredient(resultSb);
            }
        }

        // 탈출 조건
        if(depth == N + 1){
            return;
        }

        Ingredient present = inputIngredientList.get(depth - 1);
        Ingredient next = Ingredient.createEachSumIngredient(prior, present);

        // 선택한 경우
        memoryIngredientNumberList.add(depth);
        dfs(target, next, N, depth + 1);
        memoryIngredientNumberList.remove(Integer.valueOf(depth));

        // 선택x
        dfs(target, prior, N, depth + 1);

    }

    static class Ingredient {
        private int protein;
        private int fat;
        private int carbohydrate;
        private int vitamins;
        private int price;

        public Ingredient(int protein, int fat, int carbohydrate, int vitamins, int price) {
            this.protein = protein;
            this.fat = fat;
            this.carbohydrate = carbohydrate;
            this.vitamins = vitamins;
            this.price = price;
        }

        public Ingredient(int protein, int fat, int carbohydrate, int vitamins) {
            this.protein = protein;
            this.fat = fat;
            this.carbohydrate = carbohydrate;
            this.vitamins = vitamins;
        }

        static Ingredient createEachSumIngredient(Ingredient first, Ingredient second){
            int nextProtein = first.protein + second.protein;
            int nextFat = first.fat + second.fat;
            int nextCarbohydrate = first.carbohydrate + second.carbohydrate;
            int nextVitamins = first.vitamins + second.vitamins;
            int nextPrice = first.price + second.price;
            return new Ingredient(nextProtein, nextFat, nextCarbohydrate, nextVitamins, nextPrice);
        }
    }

    private static int[] allZeroInput() {
        return new int[]{0, 0, 0, 0, 0};
    }

    private static Ingredient createIngredient(int[] input) {
        return new Ingredient(input[0], input[1], input[2], input[3], input[4]);
    }

    private static Ingredient createIngredientWithoutPrice(int[] input) {
        return new Ingredient(input[0], input[1], input[2], input[3]);
    }

    private static boolean isSatisfied(Ingredient target, Ingredient present){
        return target.protein <= present.protein && target.fat <= present.fat
                && target.carbohydrate <= present.carbohydrate && target.vitamins <= present.vitamins;
    }

    private static void copyIngredient(StringBuilder resultSb) {
        for(int number : memoryIngredientNumberList){
            resultSb.append(number).append(" ");
        }
    }
}