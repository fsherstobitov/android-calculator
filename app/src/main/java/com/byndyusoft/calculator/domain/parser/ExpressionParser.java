package com.byndyusoft.calculator.domain.parser;

import com.byndyusoft.calculator.domain.eval.AbstractNode;

/**
 * This interface defines expression parser contract.
 * You'll have implement this interface if you want
 * to allow evaluation of specific types of expressions.
 * Created by jcdenton on 23.02.17.
 */
public interface ExpressionParser {

    AbstractNode parse(String expr);
}
