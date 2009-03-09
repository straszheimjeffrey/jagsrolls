package com.jagsrpg.comet;

public interface CometAction<T, R> {
	public R perform(T content, long version);
}
