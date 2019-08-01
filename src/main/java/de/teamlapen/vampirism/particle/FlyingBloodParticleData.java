package de.teamlapen.vampirism.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class FlyingBloodParticleData implements IParticleData {
    public static final IParticleData.IDeserializer<FlyingBloodParticleData> DESERIALIZER = new IParticleData.IDeserializer<FlyingBloodParticleData>() {
        public FlyingBloodParticleData deserialize(ParticleType<FlyingBloodParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            return new FlyingBloodParticleData(particleTypeIn, reader.readInt(), reader.readBoolean(), ResourceLocation.read(reader));
        }

        public FlyingBloodParticleData read(ParticleType<FlyingBloodParticleData> particleTypeIn, PacketBuffer buffer) {
            return new FlyingBloodParticleData(particleTypeIn, buffer.readVarInt(), buffer.readBoolean(), buffer.readResourceLocation());
        }
    };

    private ParticleType<FlyingBloodParticleData> particleType;
    private final int maxAge;
    private final ResourceLocation texture;
    private final boolean direct;

    public FlyingBloodParticleData(ParticleType<FlyingBloodParticleData> particleTypeIn, int maxAgeIn, boolean direct, ResourceLocation textureIn) {
        this.particleType = particleTypeIn;
        this.maxAge = maxAgeIn;
        this.texture = textureIn;
        this.direct = direct;
    }

    public FlyingBloodParticleData(ParticleType<FlyingBloodParticleData> particleTypeIn, int maxAgeIn, boolean direct) {
        this(particleTypeIn, maxAgeIn, direct, new ResourceLocation("minecraft", "critical_hit"));
    }

    @Override
    public ParticleType<?> getType() {
        return particleType;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isDirect() {
        return direct;
    }

    @Override
    public String getParameters() {
        return ForgeRegistries.PARTICLE_TYPES.getKey(this.getType()) + " " + maxAge + " " + texture;
    }

    @OnlyIn(Dist.CLIENT)
    public int getMaxAge() {
        return maxAge;
    }

    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getTexturePos() {
        return texture;
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeVarInt(maxAge);
        buffer.writeBoolean(direct);
        buffer.writeResourceLocation(texture);
    }
}
