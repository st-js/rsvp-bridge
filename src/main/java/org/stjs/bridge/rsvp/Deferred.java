package org.stjs.bridge.rsvp;

/**
 * Deferred
 * <p/>
 * The RSVP.Promise constructor is generally a better, less error-prone choice than <tt>RSVP.defer()</tt>.
 * Promises are recommended unless the specific properties of deferred are needed.
 * <p/>
 * Sometimes one needs to create a deferred object, without immediately specifying how it will be resolved.
 * These deferred objects are essentially a wrapper around a promise, whilst providing late access to the
 * <tt>resolve()</tt> and <tt>reject()</tt> methods.
 * <pre>
 * Deferred&lt;String, Error&gt; deferred = RSVP.defer();
 * // ...
 * deferred.promise // access the promise
 * // ...
 * deferred.resolve("Hello");
 * </pre>
 */
public class Deferred<Result, FailResponse> {

	/**
	 * Hidden constructor to prevent direct construction of a Deferred. Use RSVP.defer() instead.
	 */
	private Deferred() {
	}

	/**
	 * Returns the promise that is wrapped by this Deferred object. Deferred objects are not promise instances
	 * and you can therefore not call any of the flavors of <tt>then()</tt> on them directly. You can do so
	 * using the <tt>promise</tt> field.
	 */
	public Promise<Result, FailResponse> promise;

	/**
	 * Resolves this Deferred's underlying promise, passing the specified value to all then thenables chained to
	 * the promise
	 * @param value the value to be propagated to all chained thenables
	 */
	public native void resolve(Result value);

	/**
	 * Rejects this Deferred's underlying promise, passing the specified value to the reject handler of all
	 * thenables chained to the promise
	 * @param failValue the value to be propagated to the reject handlers of all chained thenables
	 */
	public native void reject(FailResponse failValue);
}
