package onboarding;

/**
 * 애플리케이션의 역할 : 문자열을 받으면 다르게 반환해주는 역할
 * 책임
 * - 어느 문자로 반환해줘야하는지 계산해주는 책임
 * - 문자열을 문자로 쪼개줄 수 있는 책임
 * - 대문자인지 확인해줄 수 있는 책임
 * - 소문자인지 확인해줄 수 있는 책임
 *  => 대문자인지 소문자인지 메시지 전달 => 문자 코드 메서드가 달라진다.
 */
public class Problem4 {
    public static String solution(String word) {

        char[] charArray = word.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            generateCode(charArray, i);
        }

        return new String(charArray);
    }

    private static void generateCode(char[] charArray, int i) {
        char target = charArray[i];
        if (isUpperChar(target)) {
            charArray[i] = (char) (155 - (byte) target);
        } else if (isLowerChar(target)) {
            charArray[i] = (char) (219 - (byte) target);
        }
    }

    private static boolean isLowerChar(char c) {
        return (c >= 97 && c <= 122);
    }

    private static boolean isUpperChar(char c) {
        return (c >= 65 && c <= 90);
    }

}
