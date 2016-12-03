/**
 * Project: Infix -> Postfix conversion
 * 
 * @author Chase Fugett
 *
 */

public class PostfixConverter {

	/**
	 * Convert an infix expression to a postfix expression. Assumes the
	 * expression uses only integers, parentheses, and the operator set {+, -,
	 * *, /, ^}.
	 * 
	 * @param formula
	 *            The infix expression.
	 * @return The postfix expression.
	 * @exception Throw Illegal Argument exception if the parenthesis don't match
	 *            up correctly.
	 */
	public static String infix2postfix(String formula)
			throws IllegalArgumentException {
		String r = "";
		CharStack S = new CharStack(20);
		Tokenizer s = new Tokenizer(formula);
		int numParen = 0;

		// Starts at the first token
		String t = " ";
		while (t != "") {
			t = s.next();
			// If t is an operand, add it to r
			if (Tokenizer.isNumber(t)) {
				r = r + t + " ";
			} else if (t.equals("^")) {
				S.push(t.charAt(0));
			} else if (Tokenizer.isOperator(t)) {
				while (!S.isEmpty()
						&& S.peek() != '('
						&& Tokenizer.operatorPriority(S.peek()) >= Tokenizer
								.operatorPriority(t.charAt(0))) {
					r = r + S.peek() + " ";
					S.pop();
				}
				S.push(t.charAt(0));
			} 
			// Push parentheses on the stack
			else if (t.equals("(")) {
				S.push(t.charAt(0));
				numParen++;
			} else if (t.equals(")")) {
				numParen++;
				while (!S.isEmpty() && S.peek() != '(') {
					r = r + S.peek() + " ";
					S.pop();
				}
				if (!S.isEmpty()) {
					S.pop();
				}
			}
		}

		while (!S.isEmpty()) {
			r = r + S.peek() + " ";
			S.pop();
		}

		if (S.isEmpty()) {
			r = r.substring(0, r.length());
		}

		if (numParen % 2 != 0) {
			throw new IllegalArgumentException();
		}

		return r;
	}

	/**
	 * Given postfix expression, calculate the value.
	 * 
	 * @param s
	 *            The postfix expression.
	 * @return double: the calculated value of the expression
	 */
	public static double evaluatePostfix(String s) throws IllegalArgumentException {
		DoubleStack S = new DoubleStack(20);
		Tokenizer t = new Tokenizer(s);

		// Starts at the first token
		String token = t.next();
		while (token != "") {
			// If the token is '(' or ')', then throw an exception
			if (token.equals("(") || token.equals(")")) {
				throw new IllegalArgumentException();
			}
			
			// If token is an operand, push it on to S
			if (Tokenizer.isNumber(token)) {
				double num = Double.parseDouble(token);
				S.push(num);
			}
			
			// If the token is an operator, create a mini-expression
			// with the two operands in S and compute the expression
			if (Tokenizer.isOperator(token)) {
				double d2, d1;
				// The next two if-else statements ensure that a number is not
				// popped from an empty stack
				if (!S.isEmpty()) {
					d2 = S.pop();
				}
				else {
					throw new IllegalArgumentException();
				}
				if (!S.isEmpty()) {
					d1 = S.pop();
				}
				else {
					throw new IllegalArgumentException();
				}
				
				// Computes the expression
				if (token.equals("-")) {
					S.push(d1 - d2);
				}
				if (token.equals("+")) {
					S.push(d1 + d2);
				}
				if (token.equals("*")) {
					S.push(d1 * d2);
				}
				if (token.equals("/")) {
					S.push(d1 / d2);
				}
				if (token.equals("^")) {
					S.push(Math.pow(d1, d2));
				}
			}
			token = t.next();
		}
		
		// If the stack has more than one number remaining, an operator 
		// is missing. So throw an IllegalArgumentException
		if (S.size() >= 2) {
			throw new IllegalArgumentException();
		}
		
		// Finally returns the result, which is the only item in S
		return S.pop();
	}

}
