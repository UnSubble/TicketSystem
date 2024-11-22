package com.unsubble.web.utils;

import java.util.function.Consumer;

public final class ObjectsUtil {

	private ObjectsUtil() {
		throw new AssertionError();
	}

	public static <T> void ifNotNullThen(T obj, Consumer<T> action, Consumer<T> ifnull) {
		if (obj == null) {
			if (ifnull != null) {
				ifnull.accept(obj);
			}
		} else {
			action.accept(obj);
		}
	}

	public static <T> void ifNotNullThenCatched(T obj, VoidConsumer action, VoidConsumer ifnull) {
		try {
			if (obj == null) {
				if (ifnull != null) {
					ifnull.accept();
				}
			} else {
				action.accept();
			}
		} catch (Throwable e) {
			throw new RuntimeException();
		}
	}

	public static <T> void ifNotNullThenCatched(T obj, ConsumerWithThrows<T> action, ConsumerWithThrows<T> ifnull) {
		try {
			if (obj == null) {
				if (ifnull != null) {
					ifnull.accept(obj);
				}
			} else {
				action.accept(obj);
			}
		} catch (Throwable e) {
			throw new RuntimeException();
		}
	}
}
