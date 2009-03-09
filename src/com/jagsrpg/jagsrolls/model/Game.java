package com.jagsrpg.jagsrolls.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game {

	private static final int ROLLS_TO_KEEP = 25;
	private static final int MAX_INIT_ROLLS = 100;

	public static final String STUNNED = "stunned";
	public static final String DAZED = "dazed";

	private LinkedList<RollListElement> rolls;
	private List<InitRoll> initRolls;
	private Map<String, Set<String>> conditions;

	public Game() {
		rolls = new LinkedList<RollListElement>();
		initRolls = new ArrayList<InitRoll>();
		conditions = new HashMap<String, Set<String>>();
		conditions.put(STUNNED, new HashSet<String>());
		conditions.put(DAZED, new HashSet<String>());
	}

	public void addRoll(String name, String rollType) {
		addRoll(new Roll(name, rollType));
	}

	private void addRoll(RollListElement roll) {
		rolls.add(roll);
		while (rolls.size() > ROLLS_TO_KEEP) {
			rolls.remove(0);
		}
	}

	public void addInitRoll(String name, int init, int rea) {
		if (initRolls.size() > MAX_INIT_ROLLS) {
			return;
		}
		if (getInitRoll(name) == null) {
			InitRoll initRoll = new InitRoll(name, init, rea);
			setConditions(initRoll);
			initRolls.add(initRoll);
		}
		// else duplicate init roll
	}

	public void deleteInitRoll(String name) {
		InitRoll target = getInitRoll(name);
		if (target != null) {
			initRolls.remove(target);
		}
	}

	public void clearRound() {
		Set<String> initNames = getInitNames();
		initRolls.clear();
		for (RollListElement e : rolls) {
			e.setThisRound(false);
		}
		for (String cond : conditions.keySet()) {
			intersectCondition(initNames, cond);
		}
	}

	public void addReaCost(String name, int cost) {
		RollListElement targetRoll = getTargetRoll(name);
		if (targetRoll == null) {
			targetRoll = new ReaCost(name);
			addRoll(targetRoll);
		}
		targetRoll.setReaCost(cost);
		chargeInitRoll(name, cost);
	}

	public void undoLastReaCost(String name) {
		RollListElement targetRoll = getUndoRoll(name);
		if (targetRoll != null) {
			int oldCost = targetRoll.getReaCost();
			targetRoll.setReaCost(0);
			chargeInitRoll(name, -oldCost);
		}
		// Else no target roll.
	}

	public void addCondition(String name, String condition) {
		Set<String> list = conditions.get(condition);
		if (list.size() > MAX_INIT_ROLLS) {
			return;
		}
		list.add(name);
		InitRoll roll = getInitRoll(name);
		if (roll != null) {
			roll.setCondition(condition, true);
		}
	}

	public void removeCondition(String name, String condition) {
		Set<String> list = conditions.get(condition);
		list.remove(name);
		InitRoll roll = getInitRoll(name);
		if (roll != null) {
			roll.setCondition(condition, false);
		}
	}

	public List<RollListElement> getRolls() {
		return Collections.unmodifiableList(rolls);
	}

	public List<InitRoll> getInitRolls() {
		List<InitRoll> result = new ArrayList<InitRoll>(initRolls);
		Collections.sort(result, InitRoll.COMPARATOR);
		return result;
	}

	/*
	 * Privates
	 */

	private InitRoll getInitRoll(String name) {
		for (InitRoll roll : initRolls) {
			if (name.equals(roll.getName())) {
				return roll;
			}
		}
		return null;
	}

	private void chargeInitRoll(String name, int cost) {
		InitRoll initRoll = getInitRoll(name);
		if (initRoll != null) {
			initRoll.setRea(initRoll.getRea() - cost);
		}
	}

	private Set<String> getInitNames() {
		Set<String> result = new HashSet<String>();
		for (InitRoll roll : initRolls) {
			result.add(roll.getName());
		}
		return result;
	}

	/*
	 * For a new round, we should retain only those conditions that had a user
	 * this round.
	 */
	private void intersectCondition(Set<String> names, String condition) {
		Set<String> cond = conditions.get(condition);
		cond.retainAll(names);
	}

	/*
	 * For a new init roll, ensure to set any existing conditions
	 */
	private void setConditions(InitRoll roll) {
		for (Map.Entry<String, Set<String>> el : conditions.entrySet()) {
			roll.setCondition(el.getKey(), el.getValue().contains(
					roll.getName()));
		}
	}

	/*
	 * This implements the private complex logic to discover a target roll for
	 * the REA cost. It searches the last 3 rolls from this round for one that
	 * matches the user and has a current cost of 0.
	 */
	private RollListElement getTargetRoll(String name) {
		Iterator<RollListElement> iter = rolls.descendingIterator();
		int count = 0;
		while (iter.hasNext()) {
			RollListElement el = iter.next();
			if (!el.isThisRound()) {
				return null;
			}
			if (++count > 3) {
				return null;
			}
			if (el.getName().equals(name)) {
				if (el.getReaCost() != 0) {
					return null;
				}
				return el;
			}
		}
		return null;
	}

	/*
	 * This finds the last valid REA cost this round.
	 */
	private RollListElement getUndoRoll(String name) {
		Iterator<RollListElement> iter = rolls.descendingIterator();
		while (iter.hasNext()) {
			RollListElement el = iter.next();
			if (!el.isThisRound()) {
				return null;
			}
			if (el.getName().equals(name) && el.getReaCost() != 0) {
				return el;
			}
		}
		return null;
	}

}
