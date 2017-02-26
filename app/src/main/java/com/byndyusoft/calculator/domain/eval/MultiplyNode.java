package com.byndyusoft.calculator.domain.eval;

public class MultiplyNode extends AbstractNode {

    public MultiplyNode() {}

    public MultiplyNode(AbstractNode left, AbstractNode right) {
        super(left, right);
    }

    @Override
    public double eval() {
        if (getLeft() != null && getRight() != null) {
            return getLeft().eval() * getRight().eval();
        } else {
            return 0.0;
        }
    }
}
