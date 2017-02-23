package com.byndyusoft.calculator.domain.eval;

public class MinusNode extends AbstractNode {

    public MinusNode(AbstractNode left, AbstractNode right) {
        super(left, right);
    }

    @Override
    public double eval() {
        if (getLeft() != null && getRight() != null) {
            return getLeft().eval() - getRight().eval();
        } else if (getLeft() != null && getRight() == null) {
            return getLeft().eval();
        } else if (getLeft() == null && getRight() != null) {
            return 0.0 - getRight().eval();
        } else {
            return 0.0;
        }
    }
}
