package dev.psygamer.wireframe.item.util;

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
	
	public net.minecraft.util.ActionResult<T> toInternal() {
		return new net.minecraft.util.ActionResult<>(this.result.getInternal(), this.object);
	}
	
	public net.minecraft.util.ActionResult<T> toInternal(final boolean clientSide) {
		return new net.minecraft.util.ActionResult<>(this.result.getInternal(clientSide), this.object);
	}
	
}
