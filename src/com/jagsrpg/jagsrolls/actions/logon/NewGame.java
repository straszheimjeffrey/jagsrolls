package com.jagsrpg.jagsrolls.actions.logon;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.jagsrpg.comet.Comet;
import com.jagsrpg.jagsrolls.beans.AddGameFrequencyException;
import com.jagsrpg.jagsrolls.beans.DuplicateGameException;
import com.jagsrpg.jagsrolls.beans.GamesContainer;
import com.jagsrpg.jagsrolls.beans.LimitExceededException;
import com.jagsrpg.jagsrolls.model.Game;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

public class NewGame extends ActionSupport implements SessionAware {

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
	private String gmPassword;
	private String userPassword;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGmPassword() {
		return gmPassword;
	}

	public void setGmPassword(String gmPassword) {
		this.gmPassword = gmPassword;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String execute() throws Exception {
		try {
			gamesContainer.addGame(getName(), getUserPassword(),
					getGmPassword());
		} catch (DuplicateGameException e) {
			addFieldError("name", "A game with that name already exists");
			return INPUT;
		} catch (AddGameFrequencyException e) {
			addActionError("Server busy.  Please try again in 30 seconds.");
			return INPUT;
		} catch (LimitExceededException e) {
			addActionError("Game count exceeded.  Please try again later.");
			return INPUT;
		}
		Comet<Game, String> game = gamesContainer.logon(getName(),
				getGmPassword());

		session.put("game", game);
		session.put("isGm", true);
		session.put("isAdmin", false);

		return SUCCESS;
	}

}
