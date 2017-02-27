package com.byndyusoft.calculator;

import com.byndyusoft.calculator.domain.eval.AbstractNode;
import com.byndyusoft.calculator.domain.eval.NumberNode;
import com.byndyusoft.calculator.domain.eval.PlusNode;
import com.byndyusoft.calculator.domain.parser.ExpressionParser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by fedor on 27.02.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculatorPresenterTest {

    @Mock
    private CalculatorView view;
    @Mock
    private ExpressionParser parser;

    @Test
    public void testOnButtonClick() throws Exception {
        CalculatorPresenter presenter = new CalculatorPresenter(view, parser);
        presenter.onButtonClick("2");
        presenter.onButtonClick("+");
        presenter.onButtonClick("3");

        verify(view, times(1)).updateScreen("2");
        verify(view, times(1)).updateScreen("2 +");
        verify(view, times(1)).updateScreen("2 + 3");
    }

    @Test
    public void testOnEquals() throws Exception {
        AbstractNode plus = new PlusNode(new NumberNode(2), new NumberNode(3));
        when(parser.parse("2 + 3")).thenReturn(plus);

        CalculatorPresenter presenter = new CalculatorPresenter(view, parser);
        presenter.onButtonClick("2");
        presenter.onButtonClick("+");
        presenter.onButtonClick("3");
        presenter.onEquals();

        verify(parser, times(1)).parse("2 + 3");
        verify(view, times(4)).updateScreen(Matchers.any(String.class));
    }

    @Test
    public void testOnButtonClickWithParanthesis() {
        CalculatorPresenter presenter = new CalculatorPresenter(view, parser);
        presenter.onButtonClick("(");
        presenter.onButtonClick("2");
        presenter.onButtonClick("+");
        presenter.onButtonClick("3");
        presenter.onButtonClick(")");

        verify(view, times(1)).updateScreen("(");
        verify(view, times(1)).updateScreen("( 2");
        verify(view, times(1)).updateScreen("( 2 +");
        verify(view, times(1)).updateScreen("( 2 + 3");
        verify(view, times(1)).updateScreen("( 2 + 3 )");
    }

    @Test
    public void testOnButtonClickWithNumberAfterClosingParanthesis() {
        CalculatorPresenter presenter = new CalculatorPresenter(view, parser);
        presenter.onButtonClick("(");
        presenter.onButtonClick("2");
        presenter.onButtonClick("+");
        presenter.onButtonClick("3");
        presenter.onButtonClick(")");
        presenter.onButtonClick("5");

        verify(view, times(1)).updateScreen("(");
        verify(view, times(1)).updateScreen("( 2");
        verify(view, times(1)).updateScreen("( 2 +");
        verify(view, times(1)).updateScreen("( 2 + 3");
        verify(view, times(1)).updateScreen("( 2 + 3 )");
        verify(view, times(1)).updateScreen("( 2 + 3 ) 5");
    }

    @Test
    public void testOnButtonClickWithOperatorAfterClosingParanthesis() {
        CalculatorPresenter presenter = new CalculatorPresenter(view, parser);
        presenter.onButtonClick("(");
        presenter.onButtonClick("2");
        presenter.onButtonClick("+");
        presenter.onButtonClick("3");
        presenter.onButtonClick(")");
        presenter.onButtonClick("+");

        verify(view, times(1)).updateScreen("(");
        verify(view, times(1)).updateScreen("( 2");
        verify(view, times(1)).updateScreen("( 2 +");
        verify(view, times(1)).updateScreen("( 2 + 3");
        verify(view, times(1)).updateScreen("( 2 + 3 )");
        verify(view, times(1)).updateScreen("( 2 + 3 ) +");
    }

    @Test
    public void testOperatorAsFirstSymbol() {
        CalculatorPresenter presenter = new CalculatorPresenter(view, parser);
        presenter.onButtonClick("+");

        verify(view, times(1)).updateScreen("");
    }

}