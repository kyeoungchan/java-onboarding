package onboarding;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 애플리케이션 기능 : 친구 추천에 따른 정렬 후 5명 리턴
 * 책임
 * - 이미 친구인 사람들 리스트를 생성한다.
 * - 이미 친구인 사람들은 목록에서 제거를 하고, 점수를 책정시작한다.
 *  - 사용자와 함께 아는 친구의 수를 구한다 * 10
 *  - 타임라인에 방문한 횟수 * 1
 * - 점수가 0이면 아예 추천 목록에서 뺀다.
 * - 추천 점수가 같은지 확인 후 이름순으로 정렬한다.
 */
public class Problem7 {

    private final Map<String, Integer> scoreMap = new HashMap<>();
    private final List<String> userFriends = new ArrayList<>();

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        Problem7 problem7 = new Problem7();
        problem7.getFriendsList(user, friends);
        problem7.getScoreMap(user, friends, visitors);

        problem7.calculateVisit(visitors);


        List<String> answer = problem7.sortMap();


        return answer;
    }

    private void getFriendsList(String user, List<List<String>> friends) {
        friends.stream()
                .forEach(l -> {
                    if (l.contains(user)) {
                        String addFriend = l.get(0).equals(user) ? l.get(1) : l.get(0);
                        if (!userFriends.contains(addFriend)) {
                            userFriends.add(addFriend);
                        }
                    }
                });
    }

    private void getScoreMap(String user, List<List<String>> friends, List<String> visitors) {

        for (List<String> element : friends) {
            for (int i = 0; i < 2; i++) {
                scoreMap.putIfAbsent(element.get(i), 0);
            }
        }
    }

    private void calculateVisit(List<String> visitors) {
        visitors.forEach(v -> scoreMap.put(v, scoreMap.getOrDefault(v, 0) + 1));
    }

    private List<String> sortMap() {
        List<Map.Entry<String, Integer>> collect = scoreMap
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
        scoreMap.entrySet().forEach(e -> System.out.println("e = " + e));
    }

}
