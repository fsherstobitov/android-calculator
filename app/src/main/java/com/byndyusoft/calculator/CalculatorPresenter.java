package com.byndyusoft.calculator;

import com.byndyusoft.calculator.domain.eval.AbstractNode;
import com.byndyusoft.calculator.domain.parser.ExpressionParser;
import com.byndyusoft.calculator.domain.parser.ExpressionValidator;

/**
 * Created by fedor on 27.02.17.
 */
public class CalculatorPresenter {

    private static final String OPERATORS = "+-*/";
    private static final String FIGURE_SYMBOLS = "0123456789.";
    private static final String PARANTHESIS = "()";
    private static final String RESULT_FORMAT = "%s = %f";

    private StringBuilder content;

    private CalculatorView view;
    private ExpressionParser parser;
    private ExpressionValidator validator;

    public CalculatorPresenter(CalculatorView view, ExpressionParser parser, ExpressionValidator validator) {
        this.view = view;
        this.parser = parser;
        this.validator = validator;
    }

    public void onDestroy() {
        this.view = null;
    }

    public void onButtonClick(String symbol) {
        if (content == null) {
            content = new StringBuilder();
        }
        if (FIGURE_SYMBOLS.indexOf(symbol) > -1) {
            if (content.length() == 0 || FIGURE_SYMBOLS.indexOf(lastSymbol()) > -1) {
                content.append(symbol);
            } else {
                content.append(" ");
                content.append(symbol);
            }
        } else if (OPERATORS.indexOf(symbol) > -1) {
            if (content.length() > 0 &&
                    (FIGURE_SYMBOLS.indexOf(lastSymbol()) > -1 ||
                    PARANTHESIS.indexOf(lastSymbol()) > -1)) {
                content.append(" ");
                content.append(symbol);
            }
        } else if (PARANTHESIS.indexOf(symbol) > -1) {
            if (content.length() == 0) {
                content.append(symbol);
            } else {
                content.append(" ");
                content.append(symbol);
            }
        }
        view.updateScreen(content.toString());
        view.setValid(true);
    }

    public void onEquals() {
        String expr = content.toString();
        if (validator.isValid(expr)) {
            AbstractNode expression = parser.parse(expr);
            double result = expression.eval();

            view.updateScreen(String.format(RESULT_FORMAT, expr, result));
            content = null;
        } else {
            view.setValid(false);
        }
    }

    private char lastSymbol() {
        return content.charAt(content.length() - 1);
    }
}
