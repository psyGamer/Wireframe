package dev.psygamer.wireframe.item.util;

import net.minecraft.util.ActionResultType;

public enum ClickResult {
	ACCEPTED(ActionResultType.SUCCESS), REJECTED(ActionResultType.FAIL), PASS(ActionResultType.PASS);
	
	private final ActionResultType internal;
	
	ClickResult(final ActionResultType internal) {
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
	
	public ActionResultType getInternal() {
		return this.internal;
	}
}
