package com.jagsrpg.jagsrolls.actions.game;

import com.jagsrpg.jagsrolls.model.Game;

public class UndoReaCost extends AbstractModifier {

	private static final long serialVersionUID = 1053280976254482125L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String perform(Game content, long version) {
		content.undoLastReaCost(getName());
		return SUCCESS;
	}

}
