package com.jagsrpg.jagsrolls.actions.admin;

import java.util.Collections;
import java.util.List;

import com.jagsrpg.jagsrolls.beans.GameHolder;
import com.jagsrpg.jagsrolls.beans.GamesContainer;
import com.jagsrpg.jagsrolls.interceptors.AdminOnly;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

public class ShowGames extends ActionSupport implements AdminOnly {

	private static final long serialVersionUID = -1141911461850547270L;

	private GamesContainer gamesContainer;

	@Inject
	public void setGamesContainer(GamesContainer gamesContainer) {
		this.gamesContainer = gamesContainer;
	}

	public GameHolder getGameHolder(String name) {
		return gamesContainer.get(name);
	}

	private List<String> games;

	public List<String> getGames() {
		return games;
	}

	public void setGames(List<String> games) {
		this.games = games;
	}

	@Override
	public String execute() throws Exception {
		List<String> games = gamesContainer.gameNames();
		Collections.sort(games);
		setGames(games);
		return SUCCESS;
	}
}
