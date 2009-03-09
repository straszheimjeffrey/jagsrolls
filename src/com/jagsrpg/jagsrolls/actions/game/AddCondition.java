package com.jagsrpg.jagsrolls.actions.game;

import com.jagsrpg.jagsrolls.model.Game;

public class AddCondition extends AbstractModifier {

	private static final long serialVersionUID = -6267885723821498975L;

	private String condition;
	private String name;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String perform(Game content, long version) {
		content.addCondition(getName(), getCondition());
		return SUCCESS;
	}

}
