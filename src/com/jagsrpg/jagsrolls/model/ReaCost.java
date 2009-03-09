package com.jagsrpg.jagsrolls.model;

import java.util.HashMap;
import java.util.Map;

public class ReaCost implements RollListElement {

	private String name;
	private boolean thisRound;
	private int reaCost;

	public ReaCost(String name) {
		this.name = name;
		this.thisRound = true;
		this.reaCost = 0;
	}

	public String getRollType() {
		return "REA Cost";
	}

	public boolean isThisRound() {
		return thisRound;
	}

	public void setThisRound(boolean thisRound) {
		this.thisRound = thisRound;
	}

	public int getReaCost() {
		return reaCost;
	}

	public void setReaCost(int reaCost) {
		this.reaCost = reaCost;
	}

	public String getName() {
		return name;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("name", getName());
		result.put("type", getRollType());
		result.put("thisRound", isThisRound());
		result.put("reaCost", getReaCost());

		return result;
	}

}
