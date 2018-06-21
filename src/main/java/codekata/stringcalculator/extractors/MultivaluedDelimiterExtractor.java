package codekata.stringcalculator.extractors;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultivaluedDelimiterExtractor implements NumberExtractor {

    @Override
    public boolean accept(String tallsekvens) {
	return tallsekvens.startsWith("//[");
    }

    @Override
    public int[] extract(String tallsekvens) {
	Matcher matcher = Pattern.compile("(?s)(?:\\[)([^\\]]+)*(?:\\])").matcher(tallsekvens);
	List<String> delimiters = new ArrayList<>();
	while (matcher.find()) {
	    delimiters.add(matcher.group(1));
	}
	String delimiterOptions = delimiters.stream()
		.map(s -> Pattern.quote(s))
		.collect(joining("|"));

	String delimiter = "(?:" + delimiterOptions + ")";
	tallsekvens = tallsekvens.substring(tallsekvens.indexOf("\n") + 1);
	return stream(tallsekvens.split(delimiter)).mapToInt(Integer::valueOf).toArray();
    }

}