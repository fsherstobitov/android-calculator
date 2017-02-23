package com.byndyusoft.calculator.domain;

public abstract class AbstractNode {

    private AbstractNode left;
    private AbstractNode right;

    public AbstractNode() {
    }

    public AbstractNode(AbstractNode left, AbstractNode right) {
        this.left = left;
        this.right = right;
    }

    public abstract double eval();

    public AbstractNode getLeft() {
        return left;
    }

    public void setLeft(AbstractNode left) {
        this.left = left;
    }

    public AbstractNode getRight() {
        return right;
    }

    public void setRight(AbstractNode right) {
        this.right = right;
    }
}
