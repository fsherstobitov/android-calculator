package com.byndyusoft.calculator.domain.parser;

/**
 * Interface defines contract for expression validator
 * Created by fedor on 27.02.17.
 */

public interface ExpressionValidator {

    boolean validate(String expr);
}
