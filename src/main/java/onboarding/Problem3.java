package onboarding;

/**
 * 369 게임
 * number가 주어지면 손뼉을 몇 번 쳐야하는지 횟수를 리턴
 *
 * 애플리케이션에 협력
 *  1. 숫자를 하나하나 호출하는 역할
 *  2. 1번이 호출한 숫자에 숫자 3, 6, 9가 들어가는지 판단해주는 역할
 *  3. 2번의 판단을 보고, 손뼉을 몇 번 쳐야하는지 알려주는 역할
 *  4. 총 손뼉을 몇 번 쳤는지 기록해주는 역할
 */
public class Problem3 {
    public static int solution(int number) {
        int answer = 0;

        for (int i = 1; i < number + 1; i++) {
/*
            if (is369(i)) {
                answer += getClapNum(i);
                System.out.println("i = " + i);
                System.out.println("answer = " + answer);
            }
*/
            String numStr = String.valueOf(i);
            answer += is369(numStr) ? getClapNum(numStr) : 0;
        }

        return answer;
    }

    public static boolean is369(String numberStr) {
        return numberStr.contains("3") || numberStr.contains("6") || numberStr.contains("9");
    }

    public static int getClapNum(String numberStr) {
        int result = 0;
        for (int i = 0; i < numberStr.length(); i++) {
            result = generateCount(result, numberStr, i);
        }
        return result;
    }

    private static int generateCount(int result, String numberStr, int i) {
        if (numberStr.charAt(i) == '3' || numberStr.charAt(i)=='6'|| numberStr.charAt(i)=='9')
            result++;
        return result;
    }
}
