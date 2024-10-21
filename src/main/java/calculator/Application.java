package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        System.out.print("덧셈할 문자열을 입력해 주세요: ");
        String input = Console.readLine();

        try {
            int result = add(input);
            // 출력 형식 일치: "결과 : " (공백 포함)
            System.out.println("결과 : " + result);
        } catch (IllegalArgumentException e) {
            // 예외 발생 시 예외 메시지 출력
            System.out.println(e.getMessage());
            throw e;  // 예외 발생 시 프로그램 종료가 아니라 테스트에서 예외를 인식하게 던집니다.
        }
    }

    /**
     * 입력된 문자열을 파싱하여 숫자를 합산
     */
    public static int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiter = ",|:"; // 기본 구분자는 쉼표와 콜론
        String numbers = input;

        // 커스텀 구분자 확인
        if (input.startsWith("//")) {
            // 정규 표현식에서 \\n 처리
            Matcher matcher = Pattern.compile("//(.)\\\\n(.*)").matcher(input);
            if (matcher.matches()) {
                delimiter = Pattern.quote(matcher.group(1)); // 구분자 특수문자 처리
                numbers = matcher.group(2);
            } else {
                throw new IllegalArgumentException("커스텀 구분자 형식이 잘못되었습니다.");
            }
        }

        // 숫자를 구분자로 분리
        String[] tokens = numbers.split(delimiter);
        int sum = 0;

        for (String token : tokens) {
            int number;
            try {
                number = toInt(token);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + token);
            }

            if (number < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다: " + number);
            }
            sum += number;
        }

        return sum;
    }

    /**
     * 문자열을 정수로 변환
     */
    private static int toInt(String token) {
        return Integer.parseInt(token);
    }
}
