package com.byndyusoft.calculator.domain.parser;

/**
 * The function of this is to insure that the
 * expression is well formed: not empty or null,
 * all braces have pairs and all operators are
 * binary
 * Created by fedor on 27.02.17.
 */

public class SimpleExpressionValidator implements ExpressionValidator {

    @Override
    public boolean isValid(String expr) {
        return notNullOrEmpty(expr) &&
                bracesArePaired(expr) &&
                allOperatorsAreBinary(expr);
    }

    private boolean allOperatorsAreBinary(String expr) {
        boolean valid = false;
        String[] tokens = expr.split(" ");
        for (int i = 0; i < tokens.length; i++) {
            if ("+-*/".indexOf(tokens[i]) > -1) {
                if (i == tokens.length - 1) {
                    valid = false;
                } else {
                    valid = isNumber(tokens[i - 1]) && isNumber(tokens[i + 1]);
                }
            }
        }
        return valid;
    }

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean notNullOrEmpty(String expr) {
        return expr != null && !expr.isEmpty();
    }

    private boolean bracesArePaired(String expr) {
        int leftBraces = 0;
        int rightBraces = 0;
        for (char c : expr.toCharArray()) {
            if ('(' == c) {
                leftBraces++;
            }
            if (')' == c) {
                rightBraces++;
            }
        }
        return leftBraces == rightBraces;
    }
}
