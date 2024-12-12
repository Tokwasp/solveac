package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Diet19942 {
    static int min = Integer.MAX_VALUE;
    static List<Ingredient> ingredientList, myIngredientList = new ArrayList<>();
    static Ingredient target;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ingredientList = new ArrayList<>();

        int[] targetInput = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        target = new Ingredient(targetInput[0], targetInput[1], targetInput[2], targetInput[3], 0, -1);

        for (int i = 0; i < N; i++) {
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Ingredient ingredient = new Ingredient(input[0], input[1], input[2], input[3], input[4], i + 1);
            ingredientList.add(ingredient);
        }

        dfs(0, 0);
        if(min == Integer.MAX_VALUE) {
            sb = new StringBuilder();
            sb.append(-1);
        }
        System.out.println(sb);
    }

    private static void dfs(int depth, int sumCost) {
        if (sumCost >= min) return;
        if (depth == ingredientList.size()) {
            if (isSatisfied(myIngredientList)) {
                min = sumCost;
                sb = new StringBuilder();
                myIngredientList.sort(Comparator.comparingInt(Ingredient::getNumber));
                sb.append(min).append("\n");
                for (Ingredient ingredient : myIngredientList) {
                    if(ingredient.isAllZero()) continue;
                    sb.append(ingredient.number).append(" ");
                }
            }
            return;
        }

        // 더 한것
        Ingredient present = ingredientList.get(depth);
        myIngredientList.add(present);
        dfs(depth + 1, sumCost + present.cost);

        // 더 하지 않은것
        myIngredientList.remove(myIngredientList.size() - 1);
        dfs(depth + 1, sumCost);
    }

    private static boolean isSatisfied(List<Ingredient> myIngredientList) {
        int protein = 0, province = 0, carbohydrate = 0, vitamin = 0;

        for (Ingredient ingredient : myIngredientList) {
            protein += ingredient.protein;
            province += ingredient.province;
            carbohydrate += ingredient.carbohydrate;
            vitamin += ingredient.vitamin;
        }
        return protein >= target.protein && province >= target.province &&
                carbohydrate >= target.carbohydrate && vitamin >= target.vitamin;
    }

    static class Ingredient {
        private int protein;
        private int province;
        private int carbohydrate;
        private int vitamin;
        private int cost;
        private int number;

        public Ingredient(int protein, int province, int carbohydrate, int vitamin, int cost, int number) {
            this.protein = protein;
            this.province = province;
            this.carbohydrate = carbohydrate;
            this.vitamin = vitamin;
            this.cost = cost;
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public boolean isAllZero() {
            return protein == 0 && province == 0 && carbohydrate == 0 && vitamin == 0;
        }
    }
}