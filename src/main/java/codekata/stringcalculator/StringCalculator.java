package codekata.stringcalculator;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    static int add(String tallsekvens) {
	int result = 0;
	String delimiter = "[\n,]";

	if (tallsekvens == null || tallsekvens.trim().equals("")) {
	    result = 0;
	} else {
	    if (tallsekvens.startsWith("//")) {
		if (tallsekvens.matches("(?s)^//.\\R.*")) {

		    Matcher matcher = Pattern.compile("^//(\\R|.+)(?=\\R)").matcher(tallsekvens);
		    matcher.find();
		    delimiter = matcher.group(1);

		    tallsekvens = tallsekvens.substring(matcher.end() + 1);

		} else if (tallsekvens.startsWith("//[")) {
		    Matcher matcher = Pattern.compile("(?s)(?:\\[)([^\\]]+)*(?:\\])").matcher(tallsekvens);
		    List<String> delimiters = new ArrayList<>();
		    while (matcher.find()) {
			delimiters.add(matcher.group(1));
		    }
		    String delimiterOptions = delimiters.stream()
			    .map(s -> Pattern.quote(s))
			    .collect(joining("|"));

		    delimiter = "(?:" + delimiterOptions + ")";
		    tallsekvens = tallsekvens.substring(tallsekvens.indexOf("\n") + 1);

		} else {
		    throw new RuntimeException("not implemented");
		}

	    }

	    int[] numbers = stream(tallsekvens.split(delimiter)).mapToInt(Integer::valueOf).toArray();
	    int[] negativeNumbers = stream(numbers).filter((int a) -> a < 0).toArray();

	    if (negativeNumbers.length > 0) {
		throw new RuntimeException("negatives not allowed " + Arrays.toString(negativeNumbers));
	    }

	    result = stream(numbers).sum();
	}
	return result;
    }

}

interface NumberExtractor {

    public boolean accept(String numberLIst);

    public int[] extract();
}
