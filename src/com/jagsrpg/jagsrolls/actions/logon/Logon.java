package com.jagsrpg.jagsrolls.actions.logon;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.jagsrpg.comet.Comet;
import com.jagsrpg.jagsrolls.beans.GamesContainer;
import com.jagsrpg.jagsrolls.model.Game;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

public class Logon extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 3524414236609600245L;

	private GamesContainer gamesContainer;
	private Map<String, Object> session;

	@Inject
	public void setGamesContainer(GamesContainer gamesContainer) {
		this.gamesContainer = gamesContainer;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	private String name;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void validate() {
		if (!gamesContainer.doesGameExist(getName())) {
			missingGame();
		}
	}

	private void missingGame() {
		addFieldError("name", "No such game exists");
	}

	@Override
	public String execute() throws Exception {

		Comet<Game, String> game = gamesContainer.logon(getName(),
				getPassword());

		if (game == null) {
			missingGame();
			return INPUT;
		}

		session.put("game", game);
		session.put("isGm", gamesContainer.isGm(getName(), getPassword()));
		session.put("isAdmin", false);

		return SUCCESS;
	}
}
