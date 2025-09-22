package algorithm.programmers.enterprise.kakao;

public class KeyPad {
    public String solution(int[] numbers, String hand) {
        int leftRow = 3;
        int leftCol = 0;

        int rightRow = 3;
        int rightCol = 2;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];

            if (number == 1 || number == 4 || number == 7) {
                leftRow = number / 3;
                leftCol = 0;
                sb.append("L");
                continue;
            }

            if (number == 3 || number == 6 || number == 9) {
                rightRow = number / 3 - 1;
                rightCol = 2;
                sb.append("R");
                continue;
            }

            int nextRow = number / 3;
            int nextCol = 1;

            if (number == 0) {
                nextRow = 3;
            }

            int leftDist = Math.abs(nextRow - leftRow) + Math.abs(nextCol - leftCol);
            int rightDist = Math.abs(nextRow - rightRow) + Math.abs(nextCol - rightCol);

            if (leftDist == rightDist) {
                String next = String.valueOf(hand.charAt(0)).toUpperCase();
                sb.append(next);

                if (next.equals("L")) {
                    leftRow = nextRow;
                    leftCol = nextCol;
                } else {
                    rightRow = nextRow;
                    rightCol = nextCol;
                }

            } else if (leftDist > rightDist) {
                sb.append("R");
                rightRow = nextRow;
                rightCol = nextCol;
            } else {
                sb.append("L");
                leftRow = nextRow;
                leftCol = nextCol;
            }
        }
        return sb.toString();
    }
}
