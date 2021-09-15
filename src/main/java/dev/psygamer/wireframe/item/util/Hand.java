package dev.psygamer.wireframe.item.util;

public enum Hand {
	MAIN_HAND(net.minecraft.util.Hand.MAIN_HAND), OFF_HAND(net.minecraft.util.Hand.OFF_HAND);
	
	private final net.minecraft.util.Hand internal;
	
	Hand(final net.minecraft.util.Hand internal) {
		this.internal = internal;
	}
	
	public static Hand fromInternal(final net.minecraft.util.Hand internal) {
		switch (internal) {
			default:
			case MAIN_HAND:
				return MAIN_HAND;
			case OFF_HAND:
				return OFF_HAND;
		}
	}
	
	public net.minecraft.util.Hand getInternal() {
		return this.internal;
	}
}
