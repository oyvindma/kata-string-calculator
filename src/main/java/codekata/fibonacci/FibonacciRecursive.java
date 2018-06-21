package codekata.fibonacci;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FibonacciRecursive {
	static Map<Integer, BigInteger> cache = new HashMap();
	
	public static BigInteger fib(int n) {
		if (cache.get(n) != null) {
			return cache.get(n);
		}
		if (n < 2) {
			return new BigInteger(n+"");
		} else {
			BigInteger val = fib(n - 1).add(fib(n - 2));
			cache.put(n, val);
			return val;
		}
	}
}

