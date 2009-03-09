package com.jagsrpg.jagsrolls.model;

import java.util.Random;

public class BaseRoll {

	private int[] rolls;
	private int roll;
	private String name;

	private static class SafeRandom {
		private Random generator = new Random();

		public synchronized int next() {
			return generator.nextInt(6);
		}
	}

	private final static SafeRandom RANDOM = new SafeRandom();

	protected BaseRoll(String name) {
		this.name = name;
		this.rolls = new int[4];
		roll = 0;
		for (int i = 0; i < 4; ++i) {
			rolls[i] = RANDOM.next();
			roll += rolls[i];
		}
	}

	public int[] getRolls() {
		return rolls;
	}

	public int getRoll() {
		return roll;
	}

	public String getName() {
		return name;
	}

}
