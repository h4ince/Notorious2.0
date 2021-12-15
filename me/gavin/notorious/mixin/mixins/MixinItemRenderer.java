// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.mixin.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.renderer.GlStateManager;
import me.gavin.notorious.hack.hacks.render.ViewModel;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.renderer.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ ItemRenderer.class })
public class MixinItemRenderer
{
    @Inject(method = { "renderItemSide" }, at = { @At("HEAD") })
    public void renderItemSide(final EntityLivingBase entitylivingbaseIn, final ItemStack heldStack, final ItemCameraTransforms.TransformType transform, final boolean leftHanded, final CallbackInfo ci) {
        if (ViewModel.INSTANCE.isEnabled()) {
            GlStateManager.func_179152_a(ViewModel.INSTANCE.scaleX.getValue() / 100.0f, ViewModel.INSTANCE.scaleY.getValue() / 100.0f, ViewModel.INSTANCE.scaleZ.getValue() / 100.0f);
            if (transform == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
                GlStateManager.func_179109_b(ViewModel.INSTANCE.translateX.getValue() / 200.0f, ViewModel.INSTANCE.translateY.getValue() / 200.0f, ViewModel.INSTANCE.translateZ.getValue() / 200.0f);
                GlStateManager.func_179114_b(ViewModel.INSTANCE.rotateX.getValue(), 1.0f, 0.0f, 0.0f);
                GlStateManager.func_179114_b(ViewModel.INSTANCE.rotateY.getValue(), 0.0f, 1.0f, 0.0f);
                GlStateManager.func_179114_b(ViewModel.INSTANCE.rotateZ.getValue(), 0.0f, 0.0f, 1.0f);
            }
            else if (transform == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND) {
                GlStateManager.func_179109_b(-ViewModel.INSTANCE.translateX.getValue() / 200.0f, ViewModel.INSTANCE.translateY.getValue() / 200.0f, ViewModel.INSTANCE.translateZ.getValue() / 200.0f);
                GlStateManager.func_179114_b(-ViewModel.INSTANCE.rotateX.getValue(), 1.0f, 0.0f, 0.0f);
                GlStateManager.func_179114_b(ViewModel.INSTANCE.rotateY.getValue(), 0.0f, 1.0f, 0.0f);
                GlStateManager.func_179114_b(ViewModel.INSTANCE.rotateZ.getValue(), 0.0f, 0.0f, 1.0f);
            }
        }
    }
}
