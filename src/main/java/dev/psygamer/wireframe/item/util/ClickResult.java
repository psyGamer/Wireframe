package dev.psygamer.wireframe.item.util;

public enum ClickResult {
	ACCEPTED, REJECTED, PASS;
	
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
		return getInternal(true);
	}
	
	public net.minecraft.util.ActionResultType getInternal(final boolean clientSide) {
		switch (this) {
			case ACCEPTED:
				return clientSide
						? net.minecraft.util.ActionResultType.SUCCESS
						: net.minecraft.util.ActionResultType.CONSUME;
			case REJECTED:
				return net.minecraft.util.ActionResultType.FAIL;
			case PASS:
				return net.minecraft.util.ActionResultType.PASS;
		}
		
		throw new IllegalStateException();
	}
}
