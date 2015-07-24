package org.stjs.bridge.rsvp;

import org.stjs.javascript.annotation.Namespace;
import org.stjs.javascript.annotation.Template;
import org.stjs.javascript.functions.Callback1;
import org.stjs.javascript.functions.Callback2;
import org.stjs.javascript.functions.Function1;

@Namespace("RSVP")
public class Promise<Result, FailResult> {
	public Promise(Callback2<Callback1<Result>, Callback1<FailResult>> handler) {
	}

	public native Promise<Result, FailResult> then(Callback1<? super Result> success);

	public native Promise<Result, FailResult> then(Callback1<? super Result> success, Callback1<? super FailResult> failure);

	@Template("suffix(Return)")
	public native <NewResult> Promise<NewResult, FailResult> thenReturn(Function1<? super Result, NewResult> success);

	@Template("suffix(Return)")
	public native <NewResult, NewFailResult> Promise<NewResult, NewFailResult> thenReturn(Function1<? super Result, NewResult> success,
			Function1<? super FailResult, NewFailResult> failure);

	@Template("suffix(Promise)")
	public native <NewResult, NewFailResult> Promise<NewResult, NewFailResult> thenPromise(
			Function1<? super Result, Promise<NewResult, NewFailResult>> success);

	@Template("suffix(Promise)")
	public native <NewResult, NewFailResult> Promise<NewResult, NewFailResult> thenPromise(
			Function1<? super Result, Promise<NewResult, NewFailResult>> success,
			Function1<? super FailResult, Promise<NewResult, NewFailResult>> failure);

}
