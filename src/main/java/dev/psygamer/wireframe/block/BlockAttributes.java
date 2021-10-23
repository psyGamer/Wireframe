package dev.psygamer.wireframe.block;

import dev.psygamer.wireframe.block.attributes.HarvestLevel;
import dev.psygamer.wireframe.block.attributes.Material;
import dev.psygamer.wireframe.internal.block.InternalBlockAttributes;
import dev.psygamer.wireframe.util.helper.ICloneable;

import net.minecraft.block.SoundType;

import net.minecraftforge.common.ToolType;

/** A simple way of setting a blocks attributes. */
public class BlockAttributes implements ICloneable<BlockAttributes> {
	
	private final InternalBlockAttributes internal;
	
	private Material material;
	
	private SoundType sound;
	
	private float hardness;
	private float blastResistance;
	
	private ToolType correctTool;
	private int harvestLevel;
	
	private boolean toolRequired;
	private boolean fullBlock = true;
	private boolean opaque = false;
	
	private boolean hasItem = true;
	
	/**
	 * Creates a new set of {@link BlockAttributes}.
	 */
	public BlockAttributes() {
		this(null);
	}
	
	/**
	 * Creates a new set of {@link BlockAttributes}.
	 *
	 * @param material The {@link Material} which determines default values, such as hardness and blast resistance.
	 */
	public BlockAttributes(final Material material) {
		this.material = material;
		
		this.internal = new InternalBlockAttributes(this);
		
		setupFromMaterial(material);
	}
	
	/** @param material The {@link Material} which determines default values, such as hardness and blast resistance. */
	public BlockAttributes material(final Material material) {
		this.material = material;
		
		return this;
	}
	
	/** @param hardness Determines how hard it is to break the {@link Block}. */
	public BlockAttributes hardness(final float hardness) {
		this.hardness = hardness;
		
		return this;
	}
	
	/** @param blastResistance Determines how resistant the {@link Block} is to explosions. */
	public BlockAttributes blastResistance(final float blastResistance) {
		this.blastResistance = blastResistance;
		
		return this;
	}
	
	public BlockAttributes sound(final SoundType sound) {
		this.sound = sound;
		
		return this;
	}
	
	public BlockAttributes correctTool(final ToolType tool) {
		this.correctTool = tool;
		
		return this;
	}
	
	/** @param harvestLevel Which tool level is at least required to break the {@link Block}. */
	public BlockAttributes harvestLevel(final int harvestLevel) {
		this.harvestLevel = harvestLevel;
		
		return this;
	}
	
	/** @param harvestLevel Which tool level is at least required to break the {@link Block}. */
	public BlockAttributes harvestLevel(final HarvestLevel harvestLevel) {
		this.harvestLevel = harvestLevel.getHarvestLevel();
		
		return this;
	}
	
	/** @param toolRequired Weather the correct tool is required to get drops. */
	public BlockAttributes toolRequired(final boolean toolRequired) {
		this.toolRequired = toolRequired;
		
		return this;
	}
	
	/** @param fullBlock Weather the {@link Block} is a full, solid cube. */
	public BlockAttributes fullBlock(final boolean fullBlock) {
		this.fullBlock = fullBlock;
		
		return this;
	}
	
	/** @param opaque Weather the {@link Block} is transparent. */
	public BlockAttributes opaque(final boolean opaque) {
		this.opaque = opaque;
		
		return this;
	}
	
	/** @param hasItem Weather the {@link Block} should have an {@link dev.psygamer.wireframe.item.Item Item}. */
	public BlockAttributes hasItem(final boolean hasItem) {
		this.hasItem = hasItem;
		
		return this;
	}
	
	/** Determines that the {@link Block} should <strong>not</strong> have an {@link dev.psygamer.wireframe.item.Item Item}. */
	public BlockAttributes noItem() {
		return hasItem(false);
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	public SoundType getSound() {
		return this.sound;
	}
	
	public float getHardness() {
		return this.hardness;
	}
	
	public float getBlastResistance() {
		return this.blastResistance;
	}
	
	public ToolType getCorrectTool() {
		return this.correctTool;
	}
	
	public int getHarvestLevel() {
		return this.harvestLevel;
	}
	
	public boolean isToolRequired() {
		return this.toolRequired;
	}
	
	public boolean isFullBlock() {
		return this.fullBlock;
	}
	
	public boolean isOpaque() {
		return this.opaque;
	}
	
	public boolean hasItem() {
		return this.hasItem;
	}
	
	/** Internal, do not use! */
	public InternalBlockAttributes getInternal() {
		return this.internal;
	}
	
	@Override
	public BlockAttributes copy() {
		return new BlockAttributes()
				.material(this.material)
				.hardness(this.hardness)
				.blastResistance(this.blastResistance)
				.correctTool(this.correctTool)
				.harvestLevel(this.harvestLevel)
				.toolRequired(this.toolRequired)
				.fullBlock(this.fullBlock);
	}
	
	private void setupFromMaterial(final Material material) {
		hardness(material.getHardness());
		blastResistance(material.getBlastResistance());
		
		toolRequired(material.getHardness() > 0);
		correctTool(material.getCorrectTool());
	}
	
	//	TODO BOUNDING BOX
}
