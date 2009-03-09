package com.jagsrpg.jagsrolls.model;

import java.util.HashMap;
import java.util.Map;

public class Roll extends BaseRoll implements RollListElement {

	private boolean thisRound;
	private String rollType;
	private int reaCost;

	public Roll(String name, String rollType) {
		super(name);
		this.rollType = rollType;
		this.thisRound = true;
		this.reaCost = 0;
	}

	public boolean isThisRound() {
		return thisRound;
	}

	public void setThisRound(boolean thisRound) {
		this.thisRound = thisRound;
	}

	public String getRollType() {
		return rollType;
	}

	public int getReaCost() {
		return reaCost;
	}

	public void setReaCost(int reaCost) {
		this.reaCost = reaCost;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("name", getName());
		result.put("type", getRollType());
		result.put("roll", getRoll());
		result.put("rolls", getRolls());
		result.put("thisRound", isThisRound());
		result.put("reaCost", getReaCost());

		return result;
	}
}
