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

        problem7.calculateCommon(friends);
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
            if (element.contains(user)) {
                // 사용자가 존재하는 리스트라면, 그 리스트는 추천 목록에서는 배제된다.
                continue;
            }
            for (int i = 0; i < 2; i++) {
                String insertingFriend = element.get(i);
                if (!userFriends.contains(insertingFriend))
                    scoreMap.putIfAbsent(insertingFriend, 0);
            }
        }

        visitors.forEach(v -> {
                    if (!userFriends.contains(v) && !user.equals(v)) {
                        scoreMap.putIfAbsent(v, 0);
                    }
                });
    }

    private void calculateCommon(List<List<String>> friends) {
        for (List<String> f : friends) {
            String f1 = f.get(0);
            String f2 = f.get(1);

            if (userFriends.contains(f1) && scoreMap.get(f2) != null) {
                scoreMap.put(f2, scoreMap.get(f2) + 10);
            }
            if (userFriends.contains(f2) && scoreMap.get(f1) != null) {
                scoreMap.put(f1, scoreMap.get(f1) + 10);
            }
        }
    }

    private void calculateVisit(List<String> visitors) {
        visitors.forEach(v -> {
            if (scoreMap.get(v) != null) {
                scoreMap.put(v, scoreMap.get(v) + 1);
            }
        });
    }

    private List<String> sortMap() {
        List<String> recommendList = scoreMap
                .entrySet()
                .stream()
                .filter(e -> e.getValue() != 0)
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
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
