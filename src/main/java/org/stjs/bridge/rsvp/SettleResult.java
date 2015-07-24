package org.stjs.bridge.rsvp;

public class SettleResult<Result, FailResult> {
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
