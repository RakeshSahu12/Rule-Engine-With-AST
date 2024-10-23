package com.nt.model;


public class ASTNode {
    private String type;       // "operator" or "operand"
    private ASTNode left;      // Left child for operators
    private ASTNode right;     // Right child for operators
    private String value;      // Value like "age" for operands
    private String operator;   // Operator like ">", "<", "=="
    private int threshold;     // Threshold value for comparisons

    // Constructor for operands
    public ASTNode(String type, ASTNode left, ASTNode right, String value) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.value = value;
    }

    // Getters and setters for all fields
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ASTNode getLeft() {
        return left;
    }

    public void setLeft(ASTNode left) {
        this.left = left;
    }

    public ASTNode getRight() {
        return right;
    }

    public void setRight(ASTNode right) {
        this.right = right;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
