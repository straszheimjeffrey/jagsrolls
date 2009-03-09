package com.jagsrpg.jagsrolls.beans;

import java.util.List;

import com.jagsrpg.comet.Comet;
import com.jagsrpg.jagsrolls.model.Game;

public interface GamesContainer {

	public void addGame(String name, String userPassword, String gmPassword)
			throws DuplicateGameException, AddGameFrequencyException,
			LimitExceededException;

	public boolean doesGameExist(String name);

	public Comet<Game, String> logon(String name, String password);

	public boolean isGm(String name, String password);

	public void deleteGame(String name);

	public List<String> gameNames();

	public GameHolder get(String name);

}
