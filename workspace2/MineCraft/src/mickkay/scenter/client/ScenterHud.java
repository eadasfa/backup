/*    */ package mickkay.scenter.client;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mickkay.scenter.ScenterMod;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import net.minecraft.client.renderer.EntityRenderer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class ScenterHud
/*    */ {
/* 18 */   private ItemOverlayRenderer itemRenderer = new ItemOverlayRenderer();
/*    */ 
/*    */   public double getHudX() {
/* 21 */     return ScenterMod.instance.getClientProxy().getHudX();
/*    */   }
/*    */ 
/*    */   public double getHudY() {
/* 25 */     return ScenterMod.instance.getClientProxy().getHudY();
/*    */   }
/*    */ 
/*    */   public void draw() {
/* 29 */     Minecraft mc = FMLClientHandler.instance().getClient();
/* 30 */     ScaledResolution scaledresolution = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
/*    */ 
/* 32 */     ItemStack itemStack = ScenterMod.instance.getClientProxy().getTargetItemStack();
/* 33 */     String name = ScenterMod.instance.getClientProxy().getTargetDisplayName();
/* 34 */     String label = String.format("%s: ---", new Object[] { name });
/* 35 */     Double distance = ScenterMod.instance.getClientProxy().distance;
/* 36 */     if (distance != null) {
/* 37 */       Double localDouble1 = distance; Double localDouble2 = distance = Double.valueOf(distance.doubleValue() - 1.0D);
/*    */ 
/* 39 */       label = String.format("%s: %.2f", new Object[] { name, distance });
/*    */     }
/*    */ 
/* 42 */     int width = scaledresolution.func_78326_a();
/* 43 */     int height = scaledresolution.func_78328_b();
/* 44 */     int iconHeight = 20;
/* 45 */     int iconWidth = 20;
/* 46 */     int lbHeight = 10;
/* 47 */     FontRenderer fontrenderer = mc.field_71466_p;
/* 48 */     int lbWidth = fontrenderer.func_78256_a(label + " ");
/* 49 */     int x = (int)(getHudX() * (width - iconWidth));
/* 50 */     int y = (int)(getHudY() * (height - iconHeight - lbHeight));
/*    */ 
/* 52 */     mc.field_71460_t.func_78478_c();
/*    */ 
/* 54 */     drawRect(x - 2, y - 2, 20, 20, 1342177280);
/* 55 */     this.itemRenderer.renderItem(itemStack, x, y);
/*    */ 
/* 57 */     int maxX = width - lbWidth;
/* 58 */     int lbX = Math.max(0, Math.min(x, maxX));
/* 59 */     int lbY = y + iconHeight;
/* 60 */     fontrenderer.func_78276_b(label, lbX, lbY, 16777215);
/*    */   }
/*    */ 
/*    */   private void drawRect(int x, int y, int width, int height, int color) {
/* 64 */     GL11.glDisable(2929);
/* 65 */     GL11.glDisable(3553);
/* 66 */     GL11.glEnable(3042);
/* 67 */     GL11.glBlendFunc(770, 771);
/*    */ 
/* 69 */     double red = (color >> 16 & 0xFF) / 255.0D;
/* 70 */     double green = (color >> 8 & 0xFF) / 255.0D;
/* 71 */     double blue = (color & 0xFF) / 255.0D;
/* 72 */     double alpha = (color >> 24 & 0xFF) / 255.0D;
/*    */ 
/* 74 */     GL11.glColor4d(red, green, blue, alpha);
/*    */ 
/* 76 */     GL11.glBegin(7);
/* 77 */     GL11.glVertex2i(x, y);
/* 78 */     GL11.glVertex2i(x, y + height);
/* 79 */     GL11.glVertex2i(x + width, y + height);
/* 80 */     GL11.glVertex2i(x + width, y);
/* 81 */     GL11.glEnd();
/*    */ 
/* 83 */     GL11.glDisable(3042);
/* 84 */     GL11.glEnable(3553);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.client.ScenterHud
 * JD-Core Version:    0.6.0
 */