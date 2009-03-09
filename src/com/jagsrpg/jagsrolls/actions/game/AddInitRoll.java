package com.jagsrpg.jagsrolls.actions.game;

import com.jagsrpg.jagsrolls.model.Game;

public class AddInitRoll extends AbstractModifier {

	private static final long serialVersionUID = 9207061518588134392L;

	private String name;
	private int init;
	private int rea;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInit() {
		return init;
	}

	public void setInit(int init) {
		this.init = init;
	}

	public int getRea() {
		return rea;
	}

	public void setRea(int rea) {
		this.rea = rea;
	}

	public String perform(Game content, long version) {
		content.addInitRoll(getName(), getInit(), getRea());
		return SUCCESS;
	}
}
