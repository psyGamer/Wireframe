package dev.psygamer.wireframe.item;

import net.minecraft.util.ActionResultType;

public enum ClickResult {
	ACCEPTED(ActionResultType.SUCCESS), REJECTED(ActionResultType.FAIL), PASS(ActionResultType.PASS);
	
	private final ActionResultType internal;
	
	ClickResult(final ActionResultType internal) {
		this.internal = internal;
	}
	
	public ActionResultType getInternal() {
		return this.internal;
	}
}
