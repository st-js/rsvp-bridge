package org.stjs.bridge.rsvp;

public final class SettleResult<Result, FailResult> {

	/**
	 * Hidden constructor to prevent direct construction
	 */
	private SettleResult() {
	}

	/**
	 * Either "fulfilled" or "rejected"
	 */
	public String state;

	/**
	 * The result of a fulfilled promise, or null if rejected
	 */
	public Result value;

	/**
	 * The reason for a rejected promise, or null if fulfilled
	 */
	public FailResult reason;
}
