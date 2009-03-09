package com.jagsrpg.jagsrolls.actions.game;

import com.jagsrpg.jagsrolls.model.Game;

public class AddRoll extends AbstractModifier {

	private static final long serialVersionUID = -2720496041480667102L;

	private String name;
	private String rollType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRollType() {
		return rollType;
	}

	public void setRollType(String rollType) {
		this.rollType = rollType;
	}

	public String perform(Game content, long version) {
		content.addRoll(getName(), getRollType());
		return SUCCESS;
	}

}
