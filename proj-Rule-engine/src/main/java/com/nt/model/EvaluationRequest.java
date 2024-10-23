package com.nt.model;

import com.fasterxml.jackson.databind.JsonNode;

public class EvaluationRequest {
	private ASTNode rule;
	private JsonNode data;

	// Getters and Setters
	public ASTNode getRule() {
		return rule;
	}

	public void setRule(ASTNode rule) {
		this.rule = rule;
	}

	public JsonNode getData() {
		return data;
	}

	public void setData(JsonNode data) {
		this.data = data;
	}
}
