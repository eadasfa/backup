/*    */ package mickkay.scenter.client;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.particle.EffectRenderer;
/*    */ import net.minecraft.client.particle.EntityAuraFX;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class ParticleTrailEffects
/*    */ {
/* 16 */   private Minecraft minecraft = FMLClientHandler.instance().getClient();
/* 17 */   private float[] colorComponents = new float[3];
/*    */ 
/*    */   public void spawnParticleTrail(World worldObj, double fromX, double fromY, double fromZ, double toX, double toY, double toZ, Color color)
/*    */   {
/* 21 */     spawnSingleParticle(worldObj, 0.5D + toX, 0.5D + toY, 0.5D + toZ, 1.0F, color, 0.0D, 0.0D, 0.0D);
/* 22 */     intSpawnParticleTrail(worldObj, fromX, fromY, fromZ, toX + 0.5D, toY + 0.5D, toZ + 0.5D, color);
/*    */   }
/*    */ 
/*    */   private void intSpawnParticleTrail(World theWorld, double fromX, double fromY, double fromZ, double toX, double toY, double toZ, Color color)
/*    */   {
/* 27 */     double dx = toX - fromX;
/* 28 */     double dy = toY - fromY;
/* 29 */     double dz = toZ - fromZ;
/* 30 */     double steps = Math.max(Math.abs(dx), Math.max(Math.abs(dy), Math.abs(dz))) * 3.0D;
/* 31 */     double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
/* 32 */     double strength = 0.5D;
/* 33 */     double ds = strength / 30.0D;
/* 34 */     double vx = dx / dist * 0.015D;
/* 35 */     double vy = dy / dist * 0.015D;
/* 36 */     double vz = dz / dist * 0.015D;
/* 37 */     for (int i = 0; i < steps; i++) {
/* 38 */       double x = fromX + dx / steps * i;
/* 39 */       double y = fromY + dy / steps * i;
/* 40 */       double z = fromZ + dz / steps * i;
/* 41 */       strength -= ds;
/* 42 */       if (strength < 0.2D) {
/* 43 */         strength = 0.2D;
/*    */       }
/* 45 */       spawnSingleParticle(theWorld, x, y, z, (float)strength, color, vx, vy, vz);
/*    */     }
/*    */   }
/*    */ 
/*    */   private void spawnSingleParticle(World theWorld, double x, double y, double z, float alpha, Color color, double vx, double vy, double vz)
/*    */   {
/* 51 */     EntityAuraFX effect = new EntityAuraFX(theWorld, x, y, z, vx, vy, vz);
/* 52 */     effect.func_70536_a(147);
/* 53 */     color.getColorComponents(this.colorComponents);
/* 54 */     effect.func_70538_b(this.colorComponents[0], this.colorComponents[1], this.colorComponents[2]);
/* 55 */     effect.func_82338_g(alpha);
/* 56 */     effect.func_70016_h(vx, vy, vz);
/* 57 */     this.minecraft.field_71452_i.func_78873_a(effect);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.client.ParticleTrailEffects
 * JD-Core Version:    0.6.0
 */