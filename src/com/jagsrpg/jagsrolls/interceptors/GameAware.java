package com.jagsrpg.jagsrolls.interceptors;

import com.jagsrpg.comet.Comet;
import com.jagsrpg.jagsrolls.model.Game;

public interface GameAware {
	public void setGame(Comet<Game, String> game);
}
