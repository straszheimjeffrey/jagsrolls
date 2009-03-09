package com.jagsrpg.jagsrolls.actions;

import com.jagsrpg.comet.Comet;
import com.jagsrpg.jagsrolls.interceptors.GameAware;
import com.jagsrpg.jagsrolls.model.Game;
import com.opensymphony.xwork2.ActionSupport;

public class Main extends ActionSupport implements GameAware {

	private static final long serialVersionUID = -656300765367274139L;

	@Override
	public void setGame(Comet<Game, String> game) {
		// This is here to force an error if the game is absent
	}

	public String execute() throws Exception {
		return SUCCESS;
	}
}
