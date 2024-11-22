package com.unsubble.web.utils;

@FunctionalInterface
public interface VoidConsumer {
	void accept() throws Exception;

	default VoidConsumer andThen(VoidConsumer then) throws Exception {
		if (then == null)
			return this;
		return () -> {
			accept();
			then.accept();
		};
	}
}
