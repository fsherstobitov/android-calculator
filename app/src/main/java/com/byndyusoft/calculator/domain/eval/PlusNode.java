package com.byndyusoft.calculator.domain.eval;

public class PlusNode extends AbstractNode {

    public PlusNode(AbstractNode left, AbstractNode right) {
        super(left, right);
    }

    @Override
    public double eval() {
        if (getLeft() != null && getRight() != null) {
            return getLeft().eval() + getRight().eval();
        } else if (getLeft() != null && getRight() == null) {
            return getLeft().eval();
        } else if (getLeft() == null && getRight() != null) {
            return getRight().eval();
        } else {
            return 0.0;
        }
    }
}
