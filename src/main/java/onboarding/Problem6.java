package onboarding;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 애플리케이션 기능
 *  역할 : 비슷한 닉네임 제한
 *  책임
 *      - 같은 글자가 연속적으로 포함된 닉네임 추출
 *          - 닉네임 쪼개기
 *          - 쪼갠 닉네임이 contains된지 비교
 *      - 제한된 지원자의 이메일 목록 생성
 *      - 이메일을 오름차순으로 정렬
 *      - 이메일 중복은 제거
 */
public class Problem6 {
    public static List<String> solution(List<List<String>> forms) {

        List<Integer> limitIdxes = extractLimitNickname(forms);

        List<String> answer = forms.stream()
                .filter(f -> {
                    if (limitIdxes.contains(forms.indexOf(f))) {
                        return true;
                    }
                    return false;
                })
                .map(f -> f.get(0))
                .sorted(String::compareTo)
                .distinct()
                .collect(Collectors.toList());


        return answer;
    }

    /**
     * 제한되는 지원자의 인덱스를 반환한다.
     */
    private static List<Integer> extractLimitNickname(List<List<String>> forms) {

        List<StringBuffer> nickNames = generateNickNameList(forms);

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nickNames.size(); i++) {

            StringBuffer currentNickName = nickNames.get(i);

            if (currentNickName.length() == 1) continue;
            for (int j = 2; j < currentNickName.length() + 1; j++) {
                // parse 할 사이즈는 최소 2부터 닉네임 길이 끝까지다.
                List<String> parsedNickNames = parsedFixedLenNickNames(currentNickName, j);

                for (StringBuffer comparingNickName : nickNames) {

                    int comparingIdx = nickNames.indexOf(comparingNickName);

                    // 이미 result에 비교하는 닉네임에 대한 인덱스에 담겨 있거나, 비교하려는 닉네임이 분해한 닉네임과 같다면 반복문 넘기기
                    if (isUnnecessary(result, currentNickName, comparingNickName, comparingIdx)) continue;

                    for (String parsedNickName : parsedNickNames) {
                        if (comparingNickName.indexOf(parsedNickName) > -1) {
                            // 문자열을 포함하는 개념으로 사용된 분기점이다.

                            // 현재 분해된 닉네임에 대한 인덱스 정보도 담고(없다면)
                            if (!result.contains(i)) result.add(i);
                            // 비교 대상 닉네임에 대한 인덱스 정보도 담는다.
                            result.add(comparingIdx);
                        }
                    }
                }
            }
        }

        return result
                .stream()
                .distinct() // 중복 제거
                .collect(Collectors.toList());
    }

    private static List<StringBuffer> generateNickNameList(List<List<String>> forms) {
/*
        List<StringBuffer> nickNames = new ArrayList<>();

        for (List<String> form : forms) {
            nickNames.add(new StringBuffer(form.get(1)));
        }
*/
        List<StringBuffer> nickNames = forms.stream()
                .map(f -> new StringBuffer(f.get(1)))
                .collect(Collectors.toList());
        return nickNames;
    }

    private static boolean isUnnecessary(List<Integer> result, StringBuffer currentNickName, StringBuffer comparingNickName, int comparingIdx) {
        return comparingNickName.equals(currentNickName) || result.contains(comparingIdx);
    }

    private static List<String> parsedFixedLenNickNames(StringBuffer nickName, int len) {
        // 길이가 5 -> 3글자면 3번. nickName.length - len + 1
        List<String> result = new ArrayList<>();
        for (int i = 0; i < nickName.length() - len + 1; i++) {
            result.add(nickName.substring(i, i + len));
        }
        return result;
    }

}
