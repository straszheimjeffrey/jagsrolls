package com.jagsrpg.jagsrolls.actions.logon;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class AdminLogon extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -8138638988100575088L;

	private Map<String, Object> session;

	@Override
	public void setSession(Map session) {
		this.session = session;
	}

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		if (getPassword().equals("!jsdfeees%%e$")) {
			session.put("isGm", true);
			session.put("isAdmin", true);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

}
