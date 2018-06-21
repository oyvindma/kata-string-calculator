package codekata.stringcalculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class StringCalculatorTest {

    private String input;
    private int expected;

    public StringCalculatorTest(String input, int expected) {
	this.input = input;
	this.expected = expected;
    }

    @Parameters(name = "{index}: add[{0}]={1}")
    public static Iterable<Object[]> data() {
	return Arrays.asList(
		new Object[][] {
			{ null, 0 },
			{ "", 0 },
			{ "1", 1 },
			{ "1,2", 3 },
			{ "1\n2,3", 6 },
			{ "//;\n1;2;3", 6 },
			{ "//\n\n1\n2\n3", 6 },
			{ "//[***]\n1***2***3", 6 },
			{ "//[***][xxx]\n1***2xxx3", 6 }
		});
    }

    @Test
    public void testAdd() throws Exception {
	StringCalculator stringCalculator = new StringCalculator();

	assertThat(stringCalculator.add(input)).isEqualTo(expected);
    }

    @Test
    public void testAdd_exceptionHandling() throws Exception {
	StringCalculator stringCalculator = new StringCalculator();
	assertThatThrownBy(
		() -> stringCalculator.add("//\n\n1\n-2\n-33"))
			.hasMessage("negatives not allowed [-2, -33]");

    }
}
