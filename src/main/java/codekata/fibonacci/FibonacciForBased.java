package codekata.fibonacci;

public class FibonacciForBased {
	public static int fib(int n) {
		int a = 1;
		int b = 1;
	
		int next = 0;
		for (int i = 0; i < n - 2; i++) {
			next = a + b;
			if (a > b) {
				b = next;
			} else {
				a = next;
			}
		}
	
		return next;
	}
	
}

