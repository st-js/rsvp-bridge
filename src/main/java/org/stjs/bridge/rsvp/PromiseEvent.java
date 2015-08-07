package org.stjs.bridge.rsvp;

public final class PromiseEvent {

	/**
	 * Hidden constructor to prevent direct construction
	 */
	private PromiseEvent() {
	}

	/**
	 * guid of promise. Must be globally unique, not just within the implementation
 	 */
	public String guid;

	/**
	 * child of child promise (for chained via `then`)
	 */
	public String childGuid;

	/**
	 * one of ['created', 'chained', 'fulfilled', 'rejected']
	 */
	public String eventName;

	/**
	 * fulfillment value or rejection reason, if applicable
	 */
	public Object detail;

	/**
	 * label passed to promise's constructor
	 */
	public String label;

	/**
	 * milliseconds elapsed since 1 January 1970 00:00:00 UTC up until now
	 */
	public long timeStamp;

	/**
	 * stack at the time of the event. (if  'instrument-with-stack' is true)
	 */
	public Object stack;
}
