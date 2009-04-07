/**
 * 
 */
package com.jagsrpg.jagsrolls.beans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.jagsrpg.comet.Comet;
import com.jagsrpg.jagsrolls.model.Game;

public class GameHolder {
	private byte[] userPassword;
	private byte[] gmPassword;
	private Comet<Game, String> game;

	public byte[] getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = computeDigest(userPassword);
	}

	public byte[] getGmPassword() {
		return gmPassword;
	}

	public void setGmPassword(String gmPassword) {
		this.gmPassword = computeDigest(gmPassword);
	}

	public Comet<Game, String> getGame() {
		return game;
	}

	public void setGame(Comet<Game, String> game) {
		this.game = game;
	}

	public static byte[] computeDigest(String text) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		md.reset();
		md.update(text.getBytes());
		return md.digest();
	}

}