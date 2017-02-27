package com.byndyusoft.calculator.domain.parser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fedor on 27.02.17.
 */
public class ExpressionValidatorTest {

    @Test
    public void testNull() {
        ExpressionValidator validator = new ExpressionValidator();
        boolean valid = validator.validate(null);

        assertFalse(valid);
    }

    @Test
    public void testEmpty() {
        ExpressionValidator validator = new ExpressionValidator();
        boolean valid = validator.validate("");

        assertFalse(valid);
    }

    @Test
    public void testPairBracesFail() {
        ExpressionValidator validator = new ExpressionValidator();
        boolean valid = validator.validate("( 3 + 2");

        assertFalse(valid);
    }

    @Test
    public void testPairBracePass() {
        ExpressionValidator validator = new ExpressionValidator();
        boolean valid = validator.validate("( 3 + 2 )");

        assertTrue(valid);
    }

    @Test
    public void testAllOperatorsBinary() {
        ExpressionValidator validator = new ExpressionValidator();
        boolean valid = validator.validate("2 +");

        assertFalse(valid);
    }

}