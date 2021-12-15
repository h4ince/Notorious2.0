// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.player.EntityPlayer;
import me.gavin.notorious.stuff.IMinecraft;

public class MathUtil implements IMinecraft
{
    public static float normalize(final float value, final float min, final float max) {
        return 1.0f - (value - min) / (max - min);
    }
    
    public static float[] calculateLookAt(final double x, final double y, final double z, final EntityPlayer me) {
        double dirx = lerp(MathUtil.mc.func_184121_ak(), me.field_70142_S, me.field_70165_t) - x;
        double diry = lerp(MathUtil.mc.func_184121_ak(), me.field_70137_T, me.field_70163_u) + me.func_70047_e() - y;
        double dirz = lerp(MathUtil.mc.func_184121_ak(), me.field_70136_U, me.field_70161_v) - z;
        final double distance = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);
        dirx /= distance;
        diry /= distance;
        dirz /= distance;
        float pitch = (float)Math.asin(diry);
        float yaw = (float)Math.atan2(dirz, dirx);
        pitch = (float)(pitch * 180.0f / 3.141592653589793);
        yaw = (float)(yaw * 180.0f / 3.141592653589793);
        yaw += 90.0f;
        return new float[] { yaw, pitch };
    }
    
    private static float[] getLegitRotations(final Vec3d vec) {
        final Vec3d eyesPos = getEyesPos();
        final double diffX = vec.field_72450_a - eyesPos.field_72450_a;
        final double diffY = vec.field_72448_b - eyesPos.field_72448_b;
        final double diffZ = vec.field_72449_c - eyesPos.field_72449_c;
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        final float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        return new float[] { MathUtil.mc.field_71439_g.field_70177_z + MathHelper.func_76142_g(yaw - MathUtil.mc.field_71439_g.field_70177_z), MathUtil.mc.field_71439_g.field_70125_A + MathHelper.func_76142_g(pitch - MathUtil.mc.field_71439_g.field_70125_A) };
    }
    
    public static double roundValueToCenter(final double inputVal) {
        double roundVal = (double)Math.round(inputVal);
        if (roundVal > inputVal) {
            roundVal -= 0.5;
        }
        else if (roundVal <= inputVal) {
            roundVal += 0.5;
        }
        return roundVal;
    }
    
    private static Vec3d getEyesPos() {
        return new Vec3d(MathUtil.mc.field_71439_g.field_70165_t, MathUtil.mc.field_71439_g.field_70163_u + MathUtil.mc.field_71439_g.func_70047_e(), MathUtil.mc.field_71439_g.field_70161_v);
    }
    
    public static float[] calcAngle(final Vec3d from, final Vec3d to) {
        final double difX = to.field_72450_a - from.field_72450_a;
        final double difY = (to.field_72448_b - from.field_72448_b) * -1.0;
        final double difZ = to.field_72449_c - from.field_72449_c;
        final double dist = MathHelper.func_76133_a(difX * difX + difZ * difZ);
        return new float[] { (float)MathHelper.func_76138_g(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0), (float)MathHelper.func_76138_g(Math.toDegrees(Math.atan2(difY, dist))) };
    }
    
    public static float lerp(final float delta, final float start, final float end) {
        return start + delta * (end - start);
    }
    
    public static double lerp(final double delta, final double start, final double end) {
        return start + delta * (end - start);
    }
    
    public static double square(final double input) {
        return input * input;
    }
    
    public static int clamp(final int num, final int min, final int max) {
        return (num < min) ? min : Math.min(num, max);
    }
}
