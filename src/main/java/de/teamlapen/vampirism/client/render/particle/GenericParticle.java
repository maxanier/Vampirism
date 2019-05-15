package de.teamlapen.vampirism.client.render.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GenericParticle extends Particle {
    public GenericParticle(World worldIn, double posXIn, double posYIn, double posZIn, int particleId, int maxAge, int color) {
        super(worldIn, posXIn, posYIn, posZIn, 0, 0, 0);
        this.setParticleTextureIndex(particleId);
        this.maxAge = maxAge;
        this.particleRed = ((color >> 16) & 0xFF) / 256f;
        this.particleGreen = ((color >> 8) & 0xFF) / 256f;
        this.particleBlue = (color & 0xFF) / 256f;
    }

    public void scaleSpeed(double f) {
        this.motionX *= f;
        this.motionY *= f;
        this.motionZ *= f;
    }

}
