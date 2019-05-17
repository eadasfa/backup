/*    */ package mickkay.scenter.client;
/*    */ 
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.common.gameevent.TickEvent;
/*    */ import cpw.mods.fml.common.gameevent.TickEvent.Phase;
/*    */ import cpw.mods.fml.common.gameevent.TickEvent.Type;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.logging.Logger;
/*    */ import mickkay.scenter.ScenterMod;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TickEventHandler
/*    */ {
/* 15 */   private final Logger logger = Logger.getLogger(ScenterMod.class.getName());
/* 16 */   private ScenterHud hud = new ScenterHud();
/*    */ 
/*    */   public TickEventHandler()
/*    */   {
/* 20 */     this.logger.info(String.format("Creating %s", new Object[] { getClass().getSimpleName() }));
/*    */   }
/*    */   @SubscribeEvent
/*    */   public void tickEnd(TickEvent evt) {
/* 25 */     if ((evt.type == TickEvent.Type.RENDER) && (evt.phase == TickEvent.Phase.END)) {
/* 26 */       onRender();
/* 27 */       GuiScreen guiscreen = Minecraft.func_71410_x().field_71462_r;
/* 28 */       if (guiscreen != null)
/* 29 */         onRenderInGUI(guiscreen);
/*    */       else
/* 31 */         onRenderInGame(); 
/*    */     }
/*    */   }
/*    */ 
/*    */   private void onRender() {
/*    */   }
/*    */ 
/*    */   private void onRenderInGUI(GuiScreen guiscreen) {
/*    */   }
/*    */ 
/*    */   private void onRenderInGame() {
/* 41 */     if (!ScenterMod.instance.getClientProxy().isActive()) {
/* 42 */       return;
/*    */     }
/* 44 */     if (ScenterMod.instance.getClientProxy().lastUpdate + 2000L > System.currentTimeMillis())
/* 45 */       this.hud.draw();
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.client.TickEventHandler
 * JD-Core Version:    0.6.0
 */