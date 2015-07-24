package org.stjs.bridge.rsvp;

import org.stjs.javascript.Array;
import org.stjs.javascript.Map;
import org.stjs.javascript.functions.Callback1;

public class RSVP {

	public static native <Result, FailResponse> Deferred<Result, FailResponse> defer();

	public static native <Result, FailResponse> Promise<Array<Result>, FailResponse> all(Array<Promise<Result, FailResponse>> promises);

	public static native <FailResponse> Promise<Map<String, Object>, FailResponse> hash(Map<String, Promise<Object, FailResponse>> promises);

	public static native <Result, FailResponse> Promise<Array<SettleResult<Result, FailResponse>>, Void> allSettled(
			Array<Promise<Result, FailResponse>> promises);

	public static native <Result, FailResponse> Promise<Map<String, SettleResult<Result, FailResponse>>, Void> hashSettled(
			Map<String, Promise<Result, FailResponse>> promises);

	public static native void on(String eventType, Callback1<?> callback1);

}
