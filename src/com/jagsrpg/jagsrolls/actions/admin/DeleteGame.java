package com.jagsrpg.jagsrolls.actions.admin;

import com.jagsrpg.jagsrolls.beans.GamesContainer;
import com.jagsrpg.jagsrolls.interceptors.AdminOnly;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

public class DeleteGame extends ActionSupport implements AdminOnly {

	private static final long serialVersionUID = -3116476408217544796L;

	private GamesContainer gamesContainer;

	@Inject
	public void setGamesContainer(GamesContainer gamesContainer) {
		this.gamesContainer = gamesContainer;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String execute() throws Exception {
		gamesContainer.deleteGame(getName());
		return SUCCESS;
	}

}
