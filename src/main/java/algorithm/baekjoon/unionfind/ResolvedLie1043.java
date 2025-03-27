package algorithm.baekjoon.unionfind;

import java.io.*;
import java.util.*;

public class ResolvedLie1043 {
    public static final int MAX_PERSON_COUNT = 50;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int personCount = Integer.parseInt(st.nextToken());
        int partyCount = Integer.parseInt(st.nextToken());

        parents = new int[MAX_PERSON_COUNT + 1];

        // 부모 배열 초기화
        for (int index = 1; index <= MAX_PERSON_COUNT; index++) {
            parents[index] = index;
        }

        // 진실을 아는 사람 입력 받기
        st = new StringTokenizer(br.readLine(), " ");
        int personCountOfKnowingTruth = Integer.parseInt(st.nextToken());

        if (personCountOfKnowingTruth == 0) {
            System.out.println(partyCount);
            return;
        }

        int[] knowingTruthArr = new int[personCountOfKnowingTruth];
        for (int i = 0; i < knowingTruthArr.length; i++) {
            knowingTruthArr[i] = Integer.parseInt(st.nextToken());
        }

        // 진실 아는 사람 이어주기
        for (int i = 1; i < knowingTruthArr.length; i++) {
            union(knowingTruthArr[i], knowingTruthArr[i - 1]);
        }

        // 파티 담기
        int[][] partyPersonNum = new int[partyCount][];

        // 파티별 사람 입력 받기
        for (int row = 0; row < partyCount; row++) {
            st = new StringTokenizer(br.readLine(), " ");
            int partyPersonCount = Integer.parseInt(st.nextToken());
            partyPersonNum[row] = new int[partyPersonCount];

            for (int col = 0; col < partyPersonCount; col++) {
                partyPersonNum[row][col] = Integer.parseInt(st.nextToken());
            }

            // 파티 하는 사람들을 연결
            for (int col = 1; col < partyPersonCount; col++) {
                union(partyPersonNum[row][col], partyPersonNum[row][col-1]);
            }

        }

        int truthPersonParents = getParents(knowingTruthArr[0]);
        int maxLieParty = partyCount;
        for (int row = 0; row < partyPersonNum.length; row++) {
            for (int col = 0; col < partyPersonNum[row].length; col++) {
                if (getParents(partyPersonNum[row][col]) == truthPersonParents) {
                    maxLieParty--;
                    break;
                }
            }
        }
        System.out.print(maxLieParty);
    }

    static int getParents(int x) {
        if (parents[x] == x) return x;
        return parents[x] = getParents(parents[x]);
    }

    static void union(int a, int b) {
        int aParents = getParents(a);
        int bParents = getParents(b);

        if (aParents < bParents) {
            parents[bParents] = aParents;
        } else {
            parents[aParents] = bParents;
        }
    }
}