package com.nt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.nt.model.ASTNode;
import com.nt.model.Rule;
import com.nt.repo.RuleRepository;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    // Create AST from rule string
    public ASTNode createRule(String ruleString) {
        // Here you would convert the ruleString to an ASTNode
        // For simplicity, assume parsing logic exists and returns the root node
        return parseRule(ruleString);
    }

    // Combine multiple rules into one AST
    public ASTNode combineRules(ASTNode... rules) {
        // Logic to combine ASTs, e.g., use an "AND" or "OR" node to combine
        ASTNode combined = new ASTNode("operator", rules[0], rules[1], "AND");
        return combined;
    }

    // Evaluate rule against the data
    public boolean evaluateRule(ASTNode node, JsonNode data) {
        // Example evaluation logic based on node type
        if ("operator".equals(node.getType())) {
            boolean leftResult = evaluateRule(node.getLeft(), data);
            boolean rightResult = evaluateRule(node.getRight(), data);
            return "AND".equals(node.getValue()) ? leftResult && rightResult : leftResult || rightResult;
        } else {
            // Operand logic, e.g., age > 30
            return evaluateOperand(node, data);
        }
    }

    // Evaluate operand (dynamic conditions)
    private boolean evaluateOperand(ASTNode node, JsonNode data) {
        String key = node.getValue(); // Extracting the key from the node
        String operator = node.getOperator(); // Assuming you store operator in ASTNode
        int threshold = node.getThreshold(); // Assuming threshold is stored in ASTNode

        // Check if the key exists and the value is an integer
        if (data.has(key) && data.get(key).isInt()) {
            int userValue = data.get(key).asInt();

            // Evaluate based on the operator
            switch (operator) {
                case ">":
                    return userValue > threshold;
                case "<":
                    return userValue < threshold;
                case "==":
                    return userValue == threshold;
                default:
                    throw new IllegalArgumentException("Unsupported operator: " + operator);
            }
        } else {
            // Log or handle case where the key does not exist or value is not an integer
            System.out.println("Key '" + key + "' not found or not an integer in the input data");
            return false; // Or handle as needed
        }
    }

    // Parse the rule string into an ASTNode (Example logic, customize as per your needs)
    private ASTNode parseRule(String ruleString) {
        // Example rule parsing, replace this with actual parser logic
        // Assume ruleString like "age > 30" becomes an ASTNode with operator and threshold
        ASTNode node = new ASTNode("operand", null, null, "age");
        node.setOperator(">");  // Assuming ruleString contains ">"
        node.setThreshold(30);  // Assuming ruleString contains "30"
        return node;
    }

    // Persist rule in database
    public Rule saveRule(String ruleString) {
        Rule rule = new Rule(ruleString);
        return ruleRepository.save(rule);
    }
}
