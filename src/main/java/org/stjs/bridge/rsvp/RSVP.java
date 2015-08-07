package org.stjs.bridge.rsvp;

import org.stjs.javascript.Array;
import org.stjs.javascript.Map;
import org.stjs.javascript.functions.Callback1;

public class RSVP {

	/**
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
	 * <p/>
	 * The RSVP.Promise constructor is generally a better, less error-prone choice than <tt>RSVP.defer()</tt>.
	 * Promises are recommended unless the specific properties of deferred are needed.
	 */
	public static native <Result, FailResponse> Deferred<Result, FailResponse> defer();

	/**
	 * Sometimes you might want to work with many promises at once. If you pass an array of promises to the <tt>all()</tt> method
	 * it will return a new promise that will be fulfilled when all of the promises in the array have been fulfilled; or
	 * rejected immediately if any promise in the array is rejected.
	 * <pre>
	 * Array<Promise<String, String>> promises = $array(2, 3, 5, 7, 11, 13).map(id -> {
	 *   return getJSON("/post/" + id + ".json");
	 * });
	 *
	 * RSVP.all(promises).then(posts -> {
	 *     // posts contains an array of results for the given promises
	 *   },
	 *   error -> {
	 *     // if any of the promises fails.
	 *   }
	 * );
	 * </pre>
	 */
	public static native <Result, FailResponse> Promise<Array<Result>, FailResponse> all(Array<Promise<Result, FailResponse>> promises);

	/**
	 * If you need to reference many promises at once (like <tt>all()</tt>), but would like to avoid encoding the actual promise
	 * order you can use <tt>hash()</tt>. The <tt>hash()</tt> method will return a new promise that will be fulfilled
	 * when all of the promises have been fulfilled; or rejected immediately if any promise is rejected.
	 * <p/>
	 * The key difference to the <tt>all()</tt> function is that both the fulfillment value and the argument to the <tt>hash()</tt>
	 * function are object literals (ie: org.stjs.Map). This allows you to simply reference the results directly off the returned
	 * object without having to remember the initial order like you would with <tt>all()</tt>.
	 * <pre>
	 * Map<String, Promise<String, String>> promises = $map (
	 *   "posts", getJSON("/posts.json"),
	 *   "users", getJSON("/users.json")
	 * );
	 *
	 * RSVP.hash(promises).then(results -> {
	 *   console.log(results.$get("users")); // print the users.json results
	 *   console.log(results.$get("posts")); // print the posts.json results
	 * });
	 * </pre>
	 */
	public static native <FailResponse> Promise<Map<String, Object>, FailResponse> hash(Map<String, Promise<Object, FailResponse>> promises);

	/**
	 * Creates a new Promise that is fulfilled when all of the specified promises are either fulfilled or rejected. The returned promise
	 * is never rejected. The returned promise fulfills with an Array of the constituent promises' result states.
	 * Each state object (ie: SettleResult) will either indicate fulfillment or rejection, and provide the corresponding value or reason.
	 */
	public static native <Result, FailResponse> Promise<Array<SettleResult<Result, FailResponse>>, Void> allSettled(
			Array<Promise<Result, FailResponse>> promises);

	/**
	 * Creates a new Promise that is fulfilled when all of the specified promises are either fulfilled or rejected. The returned promise
	 * is never rejected. The returned promise fulfills with a Map of the constituent promises' result states.
	 * Each state object (ie: SettleResult) will either indicate fulfillment or rejection, and provide the corresponding value or reason.
	 */
	public static native <Result, FailResponse> Promise<Map<String, SettleResult<Result, FailResponse>>, Void> hashSettled(
			Map<String, Promise<Result, FailResponse>> promises);

	/**
	 * Add a global listener for all promises on a specific type of event. The valid event types are ['created', 'chained', 'fulfilled',
	 * 'rejected']
	 * <p/>
	 * Events are only triggered when <tt>RSVP.configure('instrument')</tt> is true, although listeners can be registered at any time.
	 *
	 * @param eventType one of ['created', 'chained', 'fulfilled', 'rejected']
	 * @param callback1 a callback that is called whenever the specified event is triggered on any promise
	 */
	public static native void on(String eventType, Callback1<PromiseEvent> callback1);

	/**
	 * Configures an option of RSVP.
	 * <pre>
	 * RSVP.configure('instrument', true | false);
	 * // capturing the stacks is slow, so you also have to opt in
	 * RSVP.configure('instrument-with-stack', true | false);
	 * </pre>
	 *
	 * @param optionName the name of the option to configure
	 * @param value      the value of the option
	 */
	public static native void configure(String optionName, Object value);

	/**
	 * Reads the value of an option of RSVP
	 * @param optionName the option to read
	 * @return the value of the option
	 */
	public static native Object configure(String optionName);

}
