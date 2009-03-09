package com.jagsrpg.jagsrolls.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jagsrpg.comet.Comet;
import com.jagsrpg.jagsrolls.model.Game;

public class GamesContainerImpl implements GamesContainer {

	private static final long ONE_MINUTE = 1000 * 60;
	private static final int MAX_GAMES = 200;

	private Map<String, GameHolder> gameHolders = new HashMap<String, GameHolder>();

	private double lastGameTime = Long.MIN_VALUE;

	@Override
	public synchronized GameHolder get(String name) {
		return gameHolders.get(name);
	}

	public synchronized void addGame(String name, String userPassword,
			String gmPassword) throws DuplicateGameException,
			AddGameFrequencyException, LimitExceededException {
		cleanup();
		if (gameHolders.size() > MAX_GAMES) {
			throw new LimitExceededException();
		}
		if (doesGameExist(name)) {
			throw new DuplicateGameException(name);
		}
		checkLastGameTime();

		GameHolder holder = new GameHolder();
		holder.setGame(new Comet<Game, String>(new Game()));
		holder.setUserPassword(userPassword);
		holder.setGmPassword(gmPassword);
		gameHolders.put(name, holder);
	}

	public synchronized boolean doesGameExist(String name) {
		return gameHolders.containsKey(name);
	}

	public synchronized Comet<Game, String> logon(String name, String password) {
		cleanup();
		GameHolder candidate = get(name);
		if (candidate != null
				&& (candidate.getGmPassword().equals(password) || candidate
						.getUserPassword().equals(password))) {
			return candidate.getGame();
		}
		return null;
	}

	public synchronized boolean isGm(String name, String password) {
		GameHolder candidate = get(name);
		if (candidate != null && candidate.getGmPassword().equals(password)) {
			return true;
		}
		return false;
	}

	public synchronized void deleteGame(String name) {
		gameHolders.remove(name);
	}

	@Override
	public synchronized List<String> gameNames() {
		cleanup();
		return new ArrayList<String>(gameHolders.keySet());
	}

	public synchronized boolean isGamesEmpty() {
		return gameHolders.isEmpty();
	}

	private void cleanup() {
		List<String> toBeDeleted = new ArrayList<String>();
		for (Map.Entry<String, GameHolder> el : gameHolders.entrySet()) {
			if (el.getValue().getGame().getAge() > ONE_MINUTE * 30) {
				toBeDeleted.add(el.getKey());
			}
		}
		for (String el : toBeDeleted) {
			deleteGame(el);
		}
	}

	private void checkLastGameTime() throws AddGameFrequencyException {
		long now = System.currentTimeMillis();
		if (now - lastGameTime < ONE_MINUTE / 2) {
			throw new AddGameFrequencyException();
		}
		lastGameTime = now;
	}
}
