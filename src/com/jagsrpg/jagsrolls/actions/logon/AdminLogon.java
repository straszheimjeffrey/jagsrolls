package com.jagsrpg.jagsrolls.actions.logon;

import java.util.Arrays;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.jagsrpg.jagsrolls.beans.GameHolder;
import com.opensymphony.xwork2.ActionSupport;

public class AdminLogon extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -8138638988100575088L;

	private byte[] admin_digest = { -74, -85, -44, 8, 20, 0, 108, -66, 85, -78,
			-115, -71, -122, 1, -107, 111 };

	private Map<String, Object> session;

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

		byte[] provided_digest = GameHolder.computeDigest(getPassword());

		if (Arrays.equals(admin_digest, provided_digest)) {
			session.put("isGm", true);
			session.put("isAdmin", true);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

}
