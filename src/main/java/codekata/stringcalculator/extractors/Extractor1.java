package codekata.stringcalculator.extractors;

import static java.util.Arrays.stream;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractor1 implements NumberExtractor {

    @Override
    public boolean accept(String tallsekvens) {
	return tallsekvens.matches("(?s)^//.\\R.*");
    }

    @Override
    public int[] extract(String tallsekvens) {
	Matcher matcher = Pattern.compile("^//(\\R|.+)(?=\\R)").matcher(tallsekvens);
	matcher.find();

	tallsekvens = tallsekvens.substring(matcher.end() + 1);

	String delimiter = matcher.group(1);
	return stream(tallsekvens.split(delimiter)).mapToInt(Integer::valueOf).toArray();
    }

}