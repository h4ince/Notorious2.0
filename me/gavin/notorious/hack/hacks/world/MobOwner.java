// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.world;

import net.minecraft.client.renderer.GlStateManager;
import me.gavin.notorious.util.ProjectionUtil;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import me.gavin.notorious.util.UUIDResolver;
import net.minecraft.entity.Entity;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import net.minecraft.entity.passive.EntityTameable;
import java.util.HashMap;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "MobOwner", description = "Shows you who owns the mob", category = Category.World)
public class MobOwner extends Hack
{
    private final HashMap<EntityTameable, String> resolvedEntities;
    
    public MobOwner() {
        this.resolvedEntities = new HashMap<EntityTameable, String>();
    }
    
    @SubscribeEvent
    public void onTick(final PlayerLivingUpdateEvent event) {
        for (final Entity entity : MobOwner.mc.field_71441_e.field_72996_f) {
            if (entity instanceof EntityTameable) {
                final EntityTameable tameable = (EntityTameable)entity;
                if (tameable.func_184753_b() == null) {
                    continue;
                }
                final String ownerUUID = tameable.func_184753_b().toString();
                if (this.resolvedEntities.containsKey(tameable)) {
                    continue;
                }
                this.resolvedEntities.put(tameable, null);
                final String s;
                new Thread(() -> s = this.resolvedEntities.put(tameable, UUIDResolver.resolveName(ownerUUID))).start();
            }
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        for (final Entity entity : MobOwner.mc.field_71441_e.field_72996_f) {
            if (entity instanceof EntityTameable) {
                final EntityTameable entityTameable = (EntityTameable)entity;
                if (entityTameable.func_184753_b() == null || !this.resolvedEntities.containsKey(entityTameable)) {
                    continue;
                }
                String s;
                if (this.resolvedEntities.get(entityTameable) != null) {
                    s = this.resolvedEntities.get(entityTameable);
                }
                else {
                    s = entityTameable.func_184753_b().toString();
                }
                final double lerpX = MathHelper.func_151238_b(entityTameable.field_70142_S, entityTameable.field_70165_t, (double)event.getPartialTicks());
                final double lerpY = MathHelper.func_151238_b(entityTameable.field_70137_T, entityTameable.field_70163_u, (double)event.getPartialTicks());
                final double lerpZ = MathHelper.func_151238_b(entityTameable.field_70136_U, entityTameable.field_70161_v, (double)event.getPartialTicks());
                final Vec3d projection = ProjectionUtil.toScaledScreenPos(new Vec3d(lerpX, lerpY + entityTameable.field_70131_O, lerpZ));
                GlStateManager.func_179094_E();
                GlStateManager.func_179137_b(projection.field_72450_a, projection.field_72448_b, 0.0);
                GlStateManager.func_179152_a(0.85f, 0.85f, 0.0f);
                MobOwner.mc.field_71466_p.func_175063_a(s, -(MobOwner.mc.field_71466_p.func_78256_a(s) / 2.0f), (float)(-MobOwner.mc.field_71466_p.field_78288_b), -1);
                GlStateManager.func_179121_F();
            }
        }
    }
}
