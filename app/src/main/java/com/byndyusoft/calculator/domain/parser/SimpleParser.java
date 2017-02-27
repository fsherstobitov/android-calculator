package com.byndyusoft.calculator.domain.parser;

import com.byndyusoft.calculator.domain.eval.AbstractNode;
import com.byndyusoft.calculator.domain.eval.DivideNode;
import com.byndyusoft.calculator.domain.eval.MinusNode;
import com.byndyusoft.calculator.domain.eval.MultiplyNode;
import com.byndyusoft.calculator.domain.eval.NumberNode;
import com.byndyusoft.calculator.domain.eval.PlusNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * This is simple implementation of ExpressionParser.
 * [0-9].+-\*\/() are the only allowed symbols, otherwise
 * the behavior is undefined. The caller
 * code is responsible for input validation.
 */
public class SimpleParser implements ExpressionParser {

    private final Stack<AbstractNode> s = new Stack<>();
    private final Map<String, Integer> opPriority = new HashMap<>();

    public SimpleParser() {
        opPriority.put("+", 1);
        opPriority.put("-", 1);
        opPriority.put("*", 2);
        opPriority.put("/", 2);
    }

    @Override
    public AbstractNode parse(String expr) {
        String[] tokens = expr.split(" ");
        return buildTree(tokens);
    }

    private AbstractNode buildTree(String[] tokens) {
        int inflectionPoint = findInflectionPoint(tokens);
        String[] leftTokens = Arrays.copyOfRange(tokens, 0, inflectionPoint);
        String[] rightTokens = Arrays.copyOfRange(tokens, inflectionPoint + 1, tokens.length);
        AbstractNode current = null;
        String operator = tokens[inflectionPoint];
        if (isPlus(operator)) {
            current = new PlusNode();
        } else if (isMultiply(operator)) {
            current = new MultiplyNode();
        } else if (isMinus(operator)) {
            current = new MinusNode();
        } else if (isDivide(operator)) {
            current = new DivideNode();
        } else {
            throw new IllegalArgumentException("Unsupported operator found");
        }
        if (leftTokens.length > 1) {
            if (containsOperators(leftTokens)) {
                current.setLeft(buildTree(leftTokens));
            } else {
                current.setLeft(new NumberNode(Double.parseDouble(findOperand(leftTokens))));
            }
        } else {
            current.setLeft(new NumberNode(Double.parseDouble(leftTokens[0])));
        }
        if (rightTokens.length > 2) {
            current.setRight(buildTree(rightTokens));
        } else {
            current.setRight(new NumberNode(Double.parseDouble(rightTokens[0])));
        }
        return current;
    }

    private String findOperand(String[] tokens) {
        for (String token : tokens) {
            if (isNumber(token)) {
                return token;
            }
        }
        throw new IllegalArgumentException("Numbers not found in tokens");
    }

    private boolean containsOperators(String[] tokens) {
        for (String token : tokens) {
            if (isOperator(token)) {
                return true;
            }
        }
        return false;
    }

    private int findInflectionPoint(String[] tokens) {
        int inflPoint = 0;
        int currentOpPriority = 0;
        int priorityMultiplier = 1;
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (isOperator(token)) {
                if (currentOpPriority == 0 || currentOpPriority >= opPriority.get(token) * priorityMultiplier) {
                    inflPoint = i;
                    currentOpPriority = opPriority.get(token) * priorityMultiplier;
                }
            } else if (isLeftBrace(token)) {
                priorityMultiplier *= 10;
            } else if (isRightBrace(token)) {
                priorityMultiplier /= 10;
            }
        }
        return inflPoint;
    }

    private boolean isLeftBrace(String token) {
        return "(".equals(token);
    }

    private boolean isRightBrace(String token) {
        return ")".equals(token);
    }

    private boolean isOperator(String token) {
        return "+-*/".indexOf(token) > -1;
    }

    private boolean isDivide(String token) {
        return token.equals("/");
    }

    private boolean isMultiply(String token) {
        return token.equals("*");
    }

    private boolean isMinus(String token) {
        return token.equals("-");
    }

    private boolean isPlus(String token) {
        return token.equals("+");
    }

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
