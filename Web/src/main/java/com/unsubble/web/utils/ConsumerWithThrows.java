package com.unsubble.web.utils;

import java.util.function.Consumer;

@FunctionalInterface
public interface ConsumerWithThrows<T> {
	void accept(T obj) throws Throwable;
	
	default ConsumerWithThrows<T> andThen(ConsumerWithThrows<T> then) throws Throwable {
		if (then == null)
			return this;
		return (obj) -> {
			accept(obj);
			then.accept(obj);
		};
	}
	
	default ConsumerWithThrows<T> andThen(Consumer<T> then) throws Throwable {
		if (then == null)
			return this;
		return (obj) -> {
			accept(obj);
			then.accept(obj);
		};
	}
	
	default ConsumerWithThrows<T> andThen(VoidConsumer then) throws Throwable {
		if (then == null)
			return this;
		return (obj) -> {
			accept(obj);
			then.accept();
		};
	}
}
