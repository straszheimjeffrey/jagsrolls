package com.jagsrpg.jagsrolls.interceptors;

import java.util.Map;

import com.jagsrpg.comet.Comet;
import com.jagsrpg.jagsrolls.model.Game;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PrepareGame extends AbstractInterceptor {

	private static final long serialVersionUID = 2162296662274407608L;

	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext()
				.getSession();
		Object action = invocation.getAction();
		if (action instanceof GameAware) {
			GameAware gameAware = (GameAware) action;
			Comet<Game, String> game = (Comet<Game, String>) session
					.get("game");
			if (game == null) {
				return ActionSupport.ERROR;
			}
			gameAware.setGame(game);
		}
		if (action instanceof GMOnly) {
			Boolean bool = (Boolean) session.get("isGm");
			if (bool == null || !bool) {
				return ActionSupport.ERROR;
			}
		}
		if (action instanceof AdminOnly) {
			Boolean bool = (Boolean) session.get("isAdmin");
			if (bool == null || !bool) {
				return ActionSupport.ERROR;
			}
		}
		return invocation.invoke();
	}
}
