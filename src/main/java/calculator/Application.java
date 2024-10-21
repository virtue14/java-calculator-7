package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요: ");
        String input = Console.readLine();

        int result = add(input);
        System.out.println("결과: " + result);
    }

    public static int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiter = ",|:";
        String numbers = input;

        // 커스텀 구분자 확인
        if (input.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(.)\\n(.*)").matcher(input);
            if (matcher.matches()) {
                delimiter = Pattern.quote(matcher.group(1)); // 구분자 특수문자 처리
                numbers = matcher.group(2);
            } else {
                throw new IllegalArgumentException("커스텀 구분자 형식이 잘못되었습니다.");
            }
        }

        String[] tokens = numbers.split(delimiter);
        int sum = 0;

        for (String token : tokens) {
            sum += toInt(token);
        }

        return sum;
    }

    private static int toInt(String token) {
        return Integer.parseInt(token);
    }
}
