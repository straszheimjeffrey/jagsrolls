package com.jagsrpg.jagsrolls.beans;

public class DuplicateGameException extends Exception {

	private static final long serialVersionUID = -4053460809113711420L;

	public DuplicateGameException(String text) {
		super(text);
	}
}
