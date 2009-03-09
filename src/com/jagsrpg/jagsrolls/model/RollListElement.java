package com.jagsrpg.jagsrolls.model;

import java.util.Map;

public interface RollListElement {

	public String getName();

	public boolean isThisRound();

	public void setThisRound(boolean thisRound);

	public String getRollType();

	public int getReaCost();

	public void setReaCost(int reaCost);

	public Map<String, Object> toMap();
}
