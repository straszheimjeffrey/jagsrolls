package com.jagsrpg.jagsrolls.actions.game;

import com.jagsrpg.jagsrolls.interceptors.GMOnly;
import com.jagsrpg.jagsrolls.model.Game;

public class DeleteInitRoll extends AbstractModifier implements GMOnly {

	private static final long serialVersionUID = -589471234625112879L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String perform(Game content, long version) {
		content.deleteInitRoll(getName());
		return SUCCESS;
	}
}
