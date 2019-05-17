/*    */ package mickkay.scenter.client;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.RenderHelper;
/*    */ import net.minecraft.client.renderer.entity.RenderItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class ItemOverlayRenderer
/*    */ {
/* 16 */   private RenderItem itemRenderer = new RenderItem();
/*    */   private Minecraft minecraft;
/*    */ 
/*    */   public ItemOverlayRenderer()
/*    */   {
/* 20 */     this.minecraft = FMLClientHandler.instance().getClient();
/*    */   }
/*    */ 
/*    */   public void renderItem(ItemStack itemStack, float x, float y) {
/* 24 */     GL11.glEnable(2929);
/* 25 */     GL11.glPushMatrix();
/* 26 */     GL11.glRotatef(120.0F, 1.0F, 0.0F, 0.0F);
/* 27 */     RenderHelper.func_74519_b();
/* 28 */     GL11.glPopMatrix();
/* 29 */     GL11.glPushMatrix();
/* 30 */     GL11.glTranslatef(x, y, 0.0F);
/* 31 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 32 */     GL11.glEnable(32826);
/* 33 */     this.itemRenderer.field_77023_b = 100.0F;
/*    */     try
/*    */     {
/* 36 */       this.itemRenderer.func_82406_b(this.minecraft.field_71466_p, this.minecraft.field_71446_o, itemStack, 0, 0);
/*    */     } catch (RuntimeException e) {
/*    */     }
/*    */     finally {
/* 40 */       this.itemRenderer.field_77023_b = 0.0F;
/* 41 */       GL11.glDisable(32826);
/* 42 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 43 */       RenderHelper.func_74518_a();
/* 44 */       GL11.glDisable(2929);
/* 45 */       GL11.glDisable(2896);
/* 46 */       GL11.glPopMatrix();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.client.ItemOverlayRenderer
 * JD-Core Version:    0.6.0
 */