package com.jagsrpg.jagsrolls.model;

import java.util.HashMap;
import java.util.Map;

public class InitRoll extends BaseRoll {

	private static final long serialVersionUID = 2605345563115404528L;

	private int init;
	private int rea;
	private Map<String, Boolean> conditions;

	public InitRoll(String name, int init, int rea) {
		super(name);
		this.init = init;
		this.rea = rea;
		this.conditions = new HashMap<String, Boolean>();
	}

	public int getRea() {
		return rea;
	}

	public void setRea(int rea) {
		this.rea = rea;
	}

	public int getInit() {
		return init;
	}

	public void setInit(int init) {
		this.init = init;
	}

	public int getValue() {
		return getInit() - getRoll();
	}

	public boolean getConditions(String condition) {
		return conditions.get(condition);
	}

	public void setCondition(String condition, boolean state) {
		conditions.put(condition, state);
	}

	public Map<String, Object> toMap() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("name", getName());
		result.put("rea", getRea());
		result.put("init", getInit());
		result.put("value", getValue());
		result.put("roll", getRoll());
		result.put("rolls", getRolls());
		result.put("stunned", getConditions(Game.STUNNED));
		result.put("dazed", getConditions(Game.DAZED));

		return result;
	}

	public static class Comparator implements java.util.Comparator<InitRoll> {

		public int compare(InitRoll o1, InitRoll o2) {
			int v1 = o1.getValue();
			int v2 = o2.getValue();
			if (v1 != v2) {
				return -1 * new Integer(v1).compareTo(v2);
			}
			return -1 * new Integer(o1.getInit()).compareTo(o2.getInit());
		}
	}

	public static Comparator COMPARATOR = new Comparator();
}
