package dev.psygamer.wireframe.item.util;

import net.minecraft.util.ActionResult;

public class ClickResultContainer <T> {
	
	private final T object;
	private final ClickResult result;
	
	public ClickResultContainer(final T object, final ClickResult result) {
		this.object = object;
		this.result = result;
	}
	
	public static <T> ClickResultContainer<T> accept(final T object) {
		return new ClickResultContainer<>(object, ClickResult.ACCEPTED);
	}
	
	public static <T> ClickResultContainer<T> reject(final T object) {
		return new ClickResultContainer<>(object, ClickResult.REJECTED);
	}
	
	public static <T> ClickResultContainer<T> pass(final T object) {
		return new ClickResultContainer<>(object, ClickResult.PASS);
	}
	
	public T getObject() {
		return this.object;
	}
	
	public ClickResult getResult() {
		return this.result;
	}
	
	public ActionResult<T> toInternal() {
		return new ActionResult<>(this.result.getInternal(), this.object);
	}
	
}
