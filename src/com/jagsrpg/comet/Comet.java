package com.jagsrpg.comet;

import java.io.Serializable;

public class Comet<T, G> implements Serializable {

	private static final long serialVersionUID = -2904190904186344178L;

	private static final long ONE_MINUTE = 1000 * 60;

	private T content;
	private G cache;
	private long version;
	private long timestamp;

	public Comet(T content) {
		this.content = content;
		this.version = 1;
		this.cache = null;
		timestamp = System.currentTimeMillis();
	}

	public synchronized <R> R read(CometAction<T, R> action) {
		updateTimestamp();
		return action.perform(content, version);
	}

	public synchronized <R> R modify(CometAction<T, R> action) {
		updateTimestamp();
		version += 1;
		if (version < 0) {
			throw new RuntimeException("Exhausted version");
		}
		cache = null;
		R result = null;
		try {
			result = action.perform(content, version);
		} finally {
			this.notifyAll();
		}
		return result;
	}

	public synchronized G wait(CometAction<T, G> action, long version) {
		updateTimestamp();
		if (version >= this.version) {
			try {
				this.wait(ONE_MINUTE);
			} catch (InterruptedException e) {
			}
		}
		if (cache == null) {
			cache = action.perform(content, this.version);
		}
		return cache;
	}

	public long getAge() {
		return System.currentTimeMillis() - timestamp;
	}

	public void updateTimestamp() {
		timestamp = System.currentTimeMillis();
	}

}
