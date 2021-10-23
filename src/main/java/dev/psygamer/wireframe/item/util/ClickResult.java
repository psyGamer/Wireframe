package dev.psygamer.wireframe.item.util;

public enum ClickResult {
	SUCCESS, FAIL, IGNORE;
	
	public static ClickResult get(final net.minecraft.util.ActionResultType internal) {
		switch (internal) {
			default:
			case SUCCESS:
			case CONSUME:
				return SUCCESS;
			case FAIL:
				return FAIL;
			case PASS:
				return IGNORE;
		}
	}
	
	public net.minecraft.util.ActionResultType getInternal() {
		return getInternal(true);
	}
	
	public net.minecraft.util.ActionResultType getInternal(final boolean clientSide) {
		switch (this) {
			case SUCCESS:
				return clientSide
						? net.minecraft.util.ActionResultType.SUCCESS
						: net.minecraft.util.ActionResultType.CONSUME;
			case FAIL:
				return net.minecraft.util.ActionResultType.FAIL;
			case IGNORE:
				return net.minecraft.util.ActionResultType.PASS;
		}
		
		throw new IllegalStateException();
	}
}
