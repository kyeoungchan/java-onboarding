package onboarding;

/**
 * 애플리케이션에 필요한 협력
 *  - 1. 문자열의 중복된 부분을 찾아주는 역할
 *  - 2. 중복된 부분을 제거하는 역할
 *  - 3. 중복된 부분이 없어질 때까지 계속 1를 호출해주는 역할
 */
public class Problem2 {
    public static String solution(String cryptogram) {
        return callRepetition(cryptogram);
    }

    public static String callRepetition(String cryptogram) {
        StringBuffer buffer = new StringBuffer(cryptogram);

        while (removeRepetition(buffer)) {
//            System.out.println("buffer = " + buffer);
        }

        return buffer.toString();
    }

    public static boolean removeRepetition(StringBuffer buffer) {

        int startIdx = -1;
        int endIdx = -1;
        boolean anyRemoved = false;

        for (int i = 0; i < buffer.length() - 1; i++) {

            if (buffer.charAt(i) == buffer.charAt(i + 1)) {
                if (startIdx == -1) {
                    startIdx = i;
                }
                if (i == buffer.length() - 2) {
                    // 다음이 마지막 원소라면 더이상 탐색이 안되므로 바로 endIdx를 설정한다.
                    endIdx = i + 1;
                }
            } else {
                if (startIdx != -1) {
                    // 더이상 중복이 없을 때 만약 startIdx가 열려있다면 endIdx 설정
                    endIdx = i;
                }
            }
            if (endIdx != -1) {
                buffer.delete(startIdx, endIdx + 1);
                startIdx = -1;
                endIdx = -1;
                anyRemoved = true;
            }
        }

        return anyRemoved;
    }

}
