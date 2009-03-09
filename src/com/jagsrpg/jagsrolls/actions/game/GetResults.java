package com.jagsrpg.jagsrolls.actions.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.jagsrpg.comet.Comet;
import com.jagsrpg.comet.CometAction;
import com.jagsrpg.jagsrolls.interceptors.GameAware;
import com.jagsrpg.jagsrolls.model.Game;
import com.jagsrpg.jagsrolls.model.InitRoll;
import com.jagsrpg.jagsrolls.model.RollListElement;
import com.opensymphony.xwork2.ActionSupport;

public class GetResults extends ActionSupport implements GameAware,
		CometAction<Game, String> {

	private static final long serialVersionUID = -7712355419933938149L;

	private Comet<Game, String> game;

	public void setGame(Comet<Game, String> game) {
		this.game = game;
	}

	private long version;
	private String text;

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getText() {
		return text;
	}

	public String perform(Game content, long version) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> rolls = new ArrayList<Object>();
		List<Object> initRolls = new ArrayList<Object>();

		for (RollListElement el : content.getRolls()) {
			rolls.add(el.toMap());
		}
		for (InitRoll el : content.getInitRolls()) {
			initRolls.add(el.toMap());
		}

		results.put("rolls", rolls);
		results.put("initRolls", initRolls);
		results.put("version", version);

		JSONObject json = new JSONObject(results);
		return json.toString();
	}

	public String execute() throws Exception {
		text = game.wait(this, version);
		return SUCCESS;
	}

}
