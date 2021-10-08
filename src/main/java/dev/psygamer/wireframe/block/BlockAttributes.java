package dev.psygamer.wireframe.block;

import dev.psygamer.wireframe.block.attributes.HarvestLevel;
import dev.psygamer.wireframe.block.attributes.Material;
import dev.psygamer.wireframe.internal.block.InternalBlockAttributes;
import dev.psygamer.wireframe.util.helper.ICloneable;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;

/**
 * <p>A factory class for easily creating attributes related to blocks.</p>
 *
 * @author psyGamer
 * @version 1.0 | Minecraft 1.16 +
 * @see Block
 * @since 1.0 | Minecraft 1.16
 */
public class BlockAttributes implements ICloneable<BlockAttributes> {
	
	protected InternalBlockAttributes internal;
	
	protected Material material;
	
	protected SoundType sound;
	protected ItemGroup group;
	
	protected float hardness;
	protected float blastResistance;
	
	protected ToolType correctTool;
	protected int harvestLevel;
	
	protected boolean toolRequired;
	protected boolean fullBlock;
	
	/**
	 * @author psyGamer
	 * @version 1.0 | Minecraft 1.16 +
	 * @since 1.0 | Minecraft 1.16
	 */
	public BlockAttributes() {
		this(null);
	}
	
	/**
	 * @param material For sound, hardness, etc.
	 *
	 * @author psyGamer
	 * @version 1.0 | Minecraft 1.16 +
	 * @since 1.0 | Minecraft 1.16
	 */
	public BlockAttributes(final Material material) {
		this(material, null);
	}
	
	/**
	 * @param material For sound, hardness, etc.
	 * @param group    Creative Tab for the Block
	 *
	 * @author psyGamer
	 * @version 1.0 | Minecraft 1.16 +
	 * @since 1.0 | Minecraft 1.16
	 */
	public BlockAttributes(final Material material, final ItemGroup group) {
		this.material = material;
		this.group = group;
		
		this.internal = new InternalBlockAttributes(this);
		
		setupFromMaterial(material);
	}
	
	public BlockAttributes material(final Material material) {
		this.material = material;
		
		return this;
	}
	
	public BlockAttributes group(final ItemGroup group) {
		this.group = group;
		
		return this;
	}
	
	public BlockAttributes hardness(final float hardness) {
		this.hardness = hardness;
		
		return this;
	}
	
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
	
	public BlockAttributes harvestLevel(final int level) {
		this.harvestLevel = level;
		
		return this;
	}
	
	public BlockAttributes harvestLevel(final HarvestLevel harvestLevel) {
		this.harvestLevel = harvestLevel.getHarvestLevel();
		
		return this;
	}
	
	public BlockAttributes toolRequired(final boolean toolRequired) {
		this.toolRequired = toolRequired;
		
		return this;
	}
	
	public BlockAttributes fullBlock(final boolean fullBlock) {
		this.fullBlock = fullBlock;
		
		return this;
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	public SoundType getSound() {
		return this.sound;
	}
	
	public ItemGroup getGroup() {
		return this.group;
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
	
	public InternalBlockAttributes getInternal() {
		return this.internal;
	}
	
	@Override
	public BlockAttributes copy() {
		return new BlockAttributes()
				.material(this.material)
				.group(this.group)
				.hardness(this.hardness)
				.blastResistance(this.blastResistance)
				.correctTool(this.correctTool)
				.harvestLevel(this.harvestLevel)
				.toolRequired(this.toolRequired)
				.fullBlock(this.fullBlock);
	}
	
	protected void setupFromMaterial(final Material material) {
		hardness(material.getHardness());
		blastResistance(material.getBlastResistance());
		
		toolRequired(material.getHardness() > 0);
		correctTool(material.getCorrectTool());
	}
	
	//	TODO BOUNDING BOX
}
