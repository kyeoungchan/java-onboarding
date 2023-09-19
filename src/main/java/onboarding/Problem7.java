package onboarding;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 애플리케이션 기능 : 친구 추천에 따른 정렬 후 5명 리턴
 * 책임
 * - 단순히 친구들의 리스트를 구한다.
 * - 사용자와 함께 아는 친구의 수를 구한다 * 10
 * - 타임라인에 방문한 횟수 * 1
 * - 점수가 0이면 아예 추천 목록에서 뺀다.
 * - 추천 점수가 같은지 확인 후 이름순으로 정렬한다.
 */
public class Problem7 {

    private final Map<String, Integer> map = new HashMap<>();

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        Problem7 problem7 = new Problem7();
        problem7.getFriendsScoreMap(friends);

        problem7.calculateCommonFriends(friends);
        problem7.calculateVisit(visitors);


        List<String> answer = problem7.sortMap();


        return answer;
    }

    private void calculateVisit(List<String> visitors) {
        visitors
                .stream()
                .forEach(v -> map.put(v, map.getOrDefault(v, 0) + 1));
    }

    private void calculateCommonFriends(List<List<String>> friends) {
        for (List<String> extracted : friends) {
            String ex1 = extracted.get(0);
            String ex2 = extracted.get(1);

            map.put(ex1, map.get(ex1) + 1);
            map.put(ex2, map.get(ex2) + 1);
        }

        map.entrySet().forEach(f -> f.setValue(f.getValue() * 10));

    }

    private void getFriendsScoreMap(List<List<String>> friends) {
        for (List<String> element : friends) {

            for (int i = 0; i < 2; i++) {
                map.putIfAbsent(element.get(i), 0);
            }
        }
    }

    private List<String> sortMap() {
        List<Map.Entry<String, Integer>> collect = map
                .entrySet()
                .stream()
                .filter(e -> e.getValue() != 0)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        List<String> recommendList = collect.stream().sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (recommendList.size() > 5) {
            for (int i = 5; i < recommendList.size(); i++) {
                recommendList.remove(i);
            }
        }
        return recommendList;
    }

    private void printMap() {
        map.entrySet().forEach(e -> System.out.println("e = " + e));
    }

}
