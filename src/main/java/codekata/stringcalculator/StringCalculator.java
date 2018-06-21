package codekata.stringcalculator;

import static java.util.Arrays.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import codekata.stringcalculator.extractors.Extractor1;
import codekata.stringcalculator.extractors.MultivaluedDelimiterExtractor;
import codekata.stringcalculator.extractors.NillExtractor;
import codekata.stringcalculator.extractors.NoConfigExtractor;
import codekata.stringcalculator.extractors.NumberExtractor;

public class StringCalculator {

    private List<NumberExtractor> extractors = new ArrayList<>();

    public StringCalculator() {
	extractors.add(new NillExtractor());
	extractors.add(new NoConfigExtractor());
	extractors.add(new Extractor1());
	extractors.add(new MultivaluedDelimiterExtractor());
    }

    public int add(String tallsekvens) {

	int[] numbers = extractNumbers(tallsekvens);

	validateExtractionSucceeded(tallsekvens, numbers);

	validateThatOnlyPositiveNumbers(numbers);

	return stream(numbers).sum();
    }

    private int[] extractNumbers(String tallsekvens) {
	int[] numbers = {};
	for (NumberExtractor numberExtractor : extractors) {
	    if (numberExtractor.accept(tallsekvens)) {
		numbers = numberExtractor.extract(tallsekvens);
		break;
	    }
	}
	return numbers;
    }

    private void validateThatOnlyPositiveNumbers(int[] numbers) {
	int[] negativeNumbers = stream(numbers).filter((int a) -> a < 0).toArray();
	if (negativeNumbers.length > 0) {
	    throw new RuntimeException("negatives not allowed " + Arrays.toString(negativeNumbers));
	}
    }

    private void validateExtractionSucceeded(String tallsekvens, int[] numbers) {
	if (numbers.length == 0)
	    throw new IllegalArgumentException("No extractor defined for string" + tallsekvens);
    }

}
