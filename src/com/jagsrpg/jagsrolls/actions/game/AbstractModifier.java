package com.jagsrpg.jagsrolls.actions.game;

import com.jagsrpg.comet.Comet;
import com.jagsrpg.comet.CometAction;
import com.jagsrpg.jagsrolls.interceptors.GameAware;
import com.jagsrpg.jagsrolls.model.Game;
import com.opensymphony.xwork2.ActionSupport;

public abstract class AbstractModifier extends ActionSupport implements
		GameAware, CometAction<Game, String> {

	private static final long serialVersionUID = 1772948276350391230L;

	private Comet<Game, String> game;

	public String execute() {
		return game.modify(this);
	}

	public abstract String perform(Game content, long version);

	public void setGame(Comet<Game, String> game) {
		this.game = game;
	}

}
