package com.byndyusoft.calculator.domain.eval;

public class NumberNode extends AbstractNode {

    private final double value;

    public NumberNode(double value) {
        super();
        this.value = value;
    }

    public NumberNode(AbstractNode left, AbstractNode right, double value) {
        super(left, right);
        this.value = value;
    }

    @Override
    public double eval() {
        return value;
    }
}
