package de.teamlapen.vampirism.client.render;

import de.teamlapen.vampirism.util.REFERENCE;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import de.teamlapen.vampirism.entity.EntityPortalGuard;

/**
 * Renderer for the @{@link EntityPortalGuard}
 */
public class RendererPortalGuard extends RenderBiped {

	private static final ResourceLocation texture=new ResourceLocation(REFERENCE.MODID+":textures/entity/portal_guard.png");
	public RendererPortalGuard(float shadow) {
		super(new ModelBiped(), shadow);
	}

	@Override protected ResourceLocation getEntityTexture(EntityLiving p_110775_1_) {
		return texture;
	}
}
