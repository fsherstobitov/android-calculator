package com.byndyusoft.calculator.domain.eval;

public class DivideNode extends AbstractNode {

    public DivideNode(AbstractNode left, AbstractNode right) {
        super(left, right);
    }

    @Override
    public double eval() {
        if (getLeft() != null && getRight() != null) {
            return getLeft().eval() / getRight().eval();
        } else if (getLeft() != null && getRight() == null) {
            return getLeft().eval() / 0.0;
        } else if (getRight() != null && getLeft() == null) {
            return 0.0 / getRight().eval();
        } else {
            return 0.0 / 0.0;
        }
    }
}
