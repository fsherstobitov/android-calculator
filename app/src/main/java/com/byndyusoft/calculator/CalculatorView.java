package com.byndyusoft.calculator;

/**
 * Created by fedor on 27.02.17.
 */

public interface CalculatorView {

    void updateScreen(String content);

    void setValid(boolean isValid);
}
