package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.model.ASTNode;
import com.nt.service.RuleService;

@Controller
@RequestMapping("/api/rules")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    // Add this method to handle GET requests to the root URL
    @GetMapping
    public String showForm(Model model) {
        return "rule-form"; // Display the form
    }

    // Create a rule from form input
    @PostMapping("/createRuleForm")
    public String createRuleForm(@RequestParam("ruleString") String ruleString, Model model) {
        ASTNode ast = ruleService.createRule(ruleString);
        model.addAttribute("result", ast.toString()); // Convert AST to string representation
        return "rule-form";
    }

    // Combine rules from form input
    @PostMapping("/combineRulesForm")
    public String combineRulesForm(@RequestParam("rule1") String rule1,
                                   @RequestParam("rule2") String rule2, Model model) {
        ASTNode ast1 = ruleService.createRule(rule1);
        ASTNode ast2 = ruleService.createRule(rule2);
        ASTNode combinedAst = ruleService.combineRules(ast1, ast2);
        model.addAttribute("result", combinedAst.toString()); // Convert combined AST to string
        return "rule-form";
    }

    // Evaluate rule from form input
    @PostMapping("/evaluateRuleForm")
    public String evaluateRuleForm(@RequestParam("rule") String ruleString,
                                   @RequestParam("age") int age,
                                   @RequestParam("income") int income, Model model) throws JsonMappingException, JsonProcessingException {
        ASTNode rule = ruleService.createRule(ruleString);
        String jsonData = "{\"age\":" + age + ", \"income\":" + income + "}";
        JsonNode data = new ObjectMapper().readTree(jsonData); // Convert to JsonNode
        boolean result = ruleService.evaluateRule(rule, data);
        model.addAttribute("result", result ? "True" : "False");
        return "rule-form";
    }
}
