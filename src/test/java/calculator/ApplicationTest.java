package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 빈_문자열_입력시_0을_반환한다() {
        assertSimpleTest(() -> {
            run("");
            assertThat(output()).contains("결과: 0");
        });
    }

    @Test
    void 쉼표_구분자로_숫자_합산() {
        assertSimpleTest(() -> {
            run("1,2,3");
            assertThat(output()).contains("결과: 6");
        });
    }

    @Test
    void 쉼표와_콜론_구분자로_숫자_합산() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과: 6");
        });
    }

    @Test
    void 커스텀_구분자_지원() {
        assertSimpleTest(() -> {
            run("//;\n1;2;3");
            assertThat(output()).contains("결과: 6");
        });
    }

    @Test
    void 음수_입력시_예외발생() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("음수는 허용되지 않습니다: -1")
        );
    }

    @Test
    void 잘못된_입력_형식_예외발생() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,2,a"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("숫자가 아닌 값이 포함되어 있습니다: a")
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
