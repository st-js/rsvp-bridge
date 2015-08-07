package org.stjs.bridge.rsvp;

import org.stjs.javascript.annotation.Namespace;
import org.stjs.javascript.annotation.Template;
import org.stjs.javascript.functions.Callback1;
import org.stjs.javascript.functions.Callback2;
import org.stjs.javascript.functions.Function1;

@Namespace("RSVP")
public final class Promise<Result, FailResult> {

	/**
	 * Constructs a new Promise instance where the resolution or rejection state is controlled by the specified
	 * handler function.
	 * <p/>
	 * <pre>
	 * Promise<String, Error> promise = new RSVP.Promise((resolve, reject) {
	 *   // succeed
	 *   resolve(value);
	 *   // or reject
	 *   reject(error);
	 * });
	 *
	 * promise.then(resolveValue -> {
	 *     // success
	 *   },
	 *   rejectValue -> {
	 *     // failure
	 *   }
	 * );
	 * </pre>
	 * <p/>
	 * Once a promise has been resolved or rejected, it cannot be resolved or rejected again.
	 * <p/>
	 * Here is an example of a simple XHR2 wrapper written using RSVP.js:
	 *
	 * <pre>
	 * Function1<String, String> getJSON = url -> {
	 *   Promise<String, XmlHttpRequest> promise = new RSVP.Promise(resolve, reject -> {
	 *     XMLHttpRequest client = new XMLHttpRequest();
	 *     client.open("GET", url);
	 *     client.onreadystatechange = handler;
	 *     client.responseType = "json";
	 *     client.setRequestHeader("Accept", "application/json");
	 *     client.send();
	 *
	 *     Callback0 handler = () -> {
	 *       if (client.readyState === this.DONE) {
	 *         if (client.status === 200) {
	 *           resolve(client.response);
	 *         } else {
	 *           reject(client);
	 *         }
	 *       }
	 *     };
	 *   });
	 *   return promise;
	 * };
	 *
	 * getJSON.$invoke("/posts.json").then(
	 *   json -> {
	 *     // continue
	 *   },
	 *   error -> {
	 *     // handle errors
	 *   }
	 * );
	 * </pre>
	 */
	public Promise(Callback2<Callback1<Result>, Callback1<FailResult>> handler) {
	}

	/**
	 * Calls the specified callback when the promise is resolved. The specified callback receives the resolution value
	 * as parameter.
	 * <p/>
	 * Then next chained callback is called immediately after the specified callback finishes.
	 * <p/>
	 * The callback does not transform the type of the value passed to the next chained resolution or rejection callbacks.
	 *
	 * @param success a callback executed when the promise is resolved.
	 * @return a promise that can be chained with more callbacks
	 */
	public native Promise<Result, FailResult> then(Callback1<? super Result> success);

	/**
	 * Calls one of the specified callbacks when the promise is resolved or rejected respectively. The specified callbacks
	 * receives the resolution/rejection values as parameter.
	 * <p/>
	 * Then next chained callback is called immediately after the specified callback finishes.
	 * <p/>
	 * The callbacks does not transform the type of the value passed to the next chained resolution or rejection callbacks.
	 *
	 * @param success a callback executed when the promise is resolved.
	 * @param failure a callback executed when the promise is rejected.
	 * @return a promise that can be chained with more callbacks
	 */
	public native Promise<Result, FailResult> then(Callback1<? super Result> success, Callback1<? super FailResult> failure);

	/**
	 * Calls the specified success callback with the resolution value as argument when the promise is resolved. The specified
	 * success callback must return a value that can either be the same object (eg: to allow further chaining) or any other
	 * object of any other type.
	 * <p/>
	 * Then next chained success callback is called with the value returned by this success callback immediately after it
	 * returns.
	 *
	 * @param success a callback executed when the promise is resolved.
	 * @return a promise that can be chained with more callbacks
	 */
	@Template("suffix(Return)")
	public native <NewResult> Promise<NewResult, FailResult> thenReturn(Function1<? super Result, NewResult> success);

	/**
	 * Calls the specified callbacks when the promise is resolved or rejected respectively. The specified callbacks
	 * receives the resolution/rejection values as parameter. The specified callbacks must return a value that can either
	 * be the same object (eg: to allow further chaining) or any other object of any other type.
	 * <p/>
	 * Then next chained success or failure callbacks is called with the value returned by the respective callback
	 * immediately after it returns.
	 *
	 * @param success a callback executed when the promise is resolved.
	 * @param failure a callback executed when the promise is rejected.
	 * @return a promise that can be chained with more callbacks
	 */
	@Template("suffix(Return)")
	public native <NewResult, NewFailResult> Promise<NewResult, NewFailResult> thenReturn(Function1<? super Result, NewResult> success,
			Function1<? super FailResult, NewFailResult> failure);

	/**
	 * Calls the specified success callback with the resolution value as argument when the promise is resolved. The specified
	 * success callback returns a new Promise with a potentially different return type.
	 * <p/>
	 * Then next chained success callback is called when the returned promise is eventually resolved or rejected.
	 *
	 * @param success a callback executed when the promise is resolved.
	 * @return a promise that can be chained with more callbacks
	 */
	@Template("suffix(Promise)")
	public native <NewResult, NewFailResult> Promise<NewResult, NewFailResult> thenPromise(
			Function1<? super Result, Promise<NewResult, NewFailResult>> success);

	/**
	 * Calls the specified callbacks when the promise is resolved or rejected respectively. The specified callbacks
	 * receives the resolution/rejection values as parameter. The specified callbacks return a new Promise with a
	 * potentially different return type.
	 * <p/>
	 * Then next chained success or failure callbacks is called when the returned promise is eventually resolved or rejected.
	 *
	 * @param success a callback executed when the promise is resolved.
	 * @param failure a callback executed when the promise is rejected.
	 * @return a promise that can be chained with more callbacks
	 */
	@Template("suffix(Promise)")
	public native <NewResult, NewFailResult> Promise<NewResult, NewFailResult> thenPromise(
			Function1<? super Result, Promise<NewResult, NewFailResult>> success,
			Function1<? super FailResult, Promise<NewResult, NewFailResult>> failure);

}
