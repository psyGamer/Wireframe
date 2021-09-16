package dev.psygamer.wireframe.item.util;

public enum ClickResult {
	ACCEPTED(net.minecraft.util.ActionResultType.SUCCESS),
	REJECTED(net.minecraft.util.ActionResultType.FAIL),
	PASS(net.minecraft.util.ActionResultType.PASS);
	
	private final net.minecraft.util.ActionResultType internal;
	
	ClickResult(final net.minecraft.util.ActionResultType internal) {
		this.internal = internal;
	}
	
	public static ClickResult get(final net.minecraft.util.ActionResultType internal) {
		switch (internal) {
			default:
			case SUCCESS:
			case CONSUME:
				return ACCEPTED;
			case FAIL:
				return REJECTED;
			case PASS:
				return PASS;
		}
	}
	
	public net.minecraft.util.ActionResultType getInternal() {
		return this.internal;
	}
}
