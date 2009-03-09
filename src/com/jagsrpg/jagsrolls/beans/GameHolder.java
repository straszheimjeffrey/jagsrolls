/**
 * 
 */
package com.jagsrpg.jagsrolls.beans;

import com.jagsrpg.comet.Comet;
import com.jagsrpg.jagsrolls.model.Game;

public class GameHolder {
	private String userPassword;
	private String gmPassword;
	private Comet<Game, String> game;

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getGmPassword() {
		return gmPassword;
	}

	public void setGmPassword(String gmPassword) {
		this.gmPassword = gmPassword;
	}

	public Comet<Game, String> getGame() {
		return game;
	}

	public void setGame(Comet<Game, String> game) {
		this.game = game;
	}
}