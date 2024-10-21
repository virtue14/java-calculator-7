package calculator;

import camp.nextstep.edu.missionutils.Console;

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

        String delimiter = ",";
        String[] tokens = input.split(delimiter);
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
