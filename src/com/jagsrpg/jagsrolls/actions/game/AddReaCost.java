package com.jagsrpg.jagsrolls.actions.game;

import com.jagsrpg.jagsrolls.model.Game;

public class AddReaCost extends AbstractModifier {

	private static final long serialVersionUID = 4844358140886147927L;

	private String name;
	private int cost;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String perform(Game content, long version) {
		content.addReaCost(name, cost);
		return SUCCESS;
	}
}
