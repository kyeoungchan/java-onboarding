package onboarding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 애플리케이션 기능(책임)
 *  - 지갑이 최대한 가볍도록 큰 금액의 화폐 위주로 받을 책임
 */
public class Problem5 {

    public static List<Integer> moneyKind = new ArrayList<>(Arrays.asList(50000, 10000, 5000, 1000, 500, 100, 50, 10, 1));

    public static List<Integer> solution(int money) {
        List<Integer> answer = new ArrayList<>();

        int tempMoney = money;
        for (Integer kind : moneyKind) {
            answer.add(tempMoney / kind);
            tempMoney %= kind;
        }

        return answer;
    }
}
