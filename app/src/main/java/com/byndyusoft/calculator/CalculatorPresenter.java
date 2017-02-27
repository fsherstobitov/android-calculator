package com.byndyusoft.calculator;

import com.byndyusoft.calculator.domain.eval.AbstractNode;
import com.byndyusoft.calculator.domain.parser.ExpressionParser;

/**
 * Created by fedor on 27.02.17.
 */
public class CalculatorPresenter {

    private static final String OPERATORS = "+-*/";
    private static final String FIGURE_SYMBOLS = "0123456789.";
    private static final String RESULT_FORMAT = "%s = %f";

    private StringBuilder content;

    private CalculatorView view;
    private ExpressionParser parser;

    public CalculatorPresenter(CalculatorView view, ExpressionParser parser) {
        this.view = view;
        this.parser = parser;
    }

    public void onDestroy() {
        this.view = null;
    }

    public void onButtonClick(String symbol) {
        if (content == null) {
            content = new StringBuilder();
        }
        if (FIGURE_SYMBOLS.indexOf(symbol) > -1) {
            if (content.length() == 0 || FIGURE_SYMBOLS.indexOf(content.charAt(content.length() - 1)) > -1) {
                content.append(symbol);
            } else {
                content.append(" ");
                content.append(symbol);
            }
        } else if (OPERATORS.indexOf(symbol) > -1) {
            if (FIGURE_SYMBOLS.indexOf(content.charAt(content.length() - 1)) > -1) {
                content.append(" ");
                content.append(symbol);
            }
        }
//        content.append(symbol);
        view.updateScreen(content.toString());
    }

    public void onEquals() {
        AbstractNode expression = parser.parse(content.toString());
        double result = expression.eval();

        view.updateScreen(String.format(RESULT_FORMAT, content.toString(), result));
        content = null;
    }
}
