package org.stjs.bridge.rsvp;

public class Deferred<Result, FailResponse> {

	/**
	 * Hidden constructor to prevent direct construction of a Deferred. Use RSVP.defer() instead.
	 */
	private Deferred() {
	}

	public Promise<Result, FailResponse> promise;

	public native void resolve(Result value);

	public native void reject(FailResponse failValue);
}
