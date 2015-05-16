package de.teamlapen.vampirism.client.render;

import org.lwjgl.opengl.GL11;

import de.teamlapen.vampirism.client.model.ModelCoffin;
import de.teamlapen.vampirism.tileEntity.TileEntityCoffin;
import de.teamlapen.vampirism.util.Logger;
import de.teamlapen.vampirism.util.REFERENCE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RendererCoffin extends TileEntitySpecialRenderer {
	private ModelCoffin model;
	private ResourceLocation texture;

	public RendererCoffin() {
		model = new ModelCoffin();
		model.setLid(true);
		texture = new ResourceLocation(REFERENCE.MODID
				+ ":textures/blocks/coffin.png");
	}

	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		GL11.glRotatef(meta * (-90), 0.0F, 1.0F, 0.0F);
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z,
			float scale) {
		if (te instanceof TileEntityCoffin)
			if ((te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) & (-8)) == 0) {
				// Logger.i("RendererCoffin",
				// String.format("Not rendering coffin at x=%d, y=%d, z=%d",
				// te.xCoord, te.yCoord, te.zCoord));
				return;
			}
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		bindTexture(texture);
		GL11.glPushMatrix();
		adjustRotatePivotViaMeta(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
