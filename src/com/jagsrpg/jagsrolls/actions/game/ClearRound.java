package com.jagsrpg.jagsrolls.actions.game;

import com.jagsrpg.jagsrolls.interceptors.GMOnly;
import com.jagsrpg.jagsrolls.model.Game;

public class ClearRound extends AbstractModifier implements GMOnly {

	private static final long serialVersionUID = -7078764360811335993L;

	public String perform(Game content, long version) {
		content.clearRound();
		return SUCCESS;
	}

}
