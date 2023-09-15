package onboarding;

import java.util.List;

class Problem1 {

    static int exceptionFlag = 0;

    public static int solution(List<Integer> pobi, List<Integer> crong) {
//        int answer = Integer.MAX_VALUE;
        int[] pobiArray = pobi.stream().mapToInt(n -> n.intValue()).toArray();
        int[] crongArray = crong.stream().mapToInt(n -> n.intValue()).toArray();

        int pobiScore = calculateScore(pobiArray);
        int crongScore = calculateScore(crongArray);
        if (exceptionFlag == 1) {
            exceptionFlag = 0;
            return -1;
        }
        return pobiScore > crongScore ? 1 : (pobiScore < crongScore ? 2 : 0);

//        return answer;
    }

    private static int calculateScore(int[] pages) {
        int left = pages[0];
        int right = pages[1];
        boolean isValidated = validate(left, right);
        if (!isValidated) {
            exceptionFlag = 1;
            return 0;
        }
        int leftScore = calculateMaximumAddOrMultiply(left);
        int rightScore = calculateMaximumAddOrMultiply(right);
        return leftScore > rightScore ? leftScore : rightScore;
    }

    private static boolean validate(int left, int right) {
        if (left + 1 != right) {
            return false;
        }
        if (left <= 0 || left >= 400) {
            return false;
        }
        if (right <= 0 || right > 400) {
            return false;
        }
        if (left % 2 == 0 || right % 2 != 0) {
            return false;
        }
        return true;
    }

    private static int calculateMaximumAddOrMultiply(int page) {
        int[] digits = splitDigits(page);
        int add = 0;
        int multi = 1;

        for (int i = 0; i < digits.length; i++) {
            add += digits[i];
            multi *= digits[i];
        }
        return add > multi ? add : multi;
    }

    private static int[] splitDigits(int page) {

        int[] digits = null;
        if (page / 100 >= 1) {
            digits = new int[3];
        } else if (page / 10 >= 1) {
            digits = new int[2];
        } else {
            digits = new int[1];
        }

        int len = digits.length;
        int rest = page;
        for (int i = 0; i < len; i++) {
            int by = (int) Math.pow(10, (len - i - 1));
            digits[i] = rest / by;
            rest %= by;
        }

        return digits;
    }

    private static int solution(int[] pobi, int[] crong) {
        int pobiScore = calculateScore(pobi);
        int crongScore = calculateScore(crong);
        if (exceptionFlag == 1) {
            exceptionFlag = 0;
            return -1;
        }
        return pobiScore > crongScore ? 1 : (pobiScore < crongScore ? 2 : 0);
    }


}