/*    */ package mickkay.scenter.client;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ClientRegistry;
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.logging.Logger;
/*    */ import mickkay.scenter.ScenterMod;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.settings.KeyBinding;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class KeyEventHandler
/*    */ {
/* 25 */   private final Logger logger = Logger.getLogger(ScenterMod.class.getName());
/*    */ 
/* 27 */   static KeyBinding ENABLE = new KeyBinding("Enable Scenter / Next Block", 24, "key.categories.gameplay");
/* 28 */   static KeyBinding DISABLE = new KeyBinding("Disable Scenter", 38, "key.categories.gameplay");
/*    */ 
/*    */   public KeyEventHandler() {
/* 31 */     this.logger.info(String.format("Creating %s", new Object[] { getClass().getSimpleName() }));
/* 32 */     ClientRegistry.registerKeyBinding(ENABLE);
/* 33 */     ClientRegistry.registerKeyBinding(DISABLE);
/*    */   }
/*    */   @SubscribeEvent
/*    */   public void onKey(InputEvent.KeyInputEvent event) {
/* 38 */     onKey();
/*    */   }
/*    */ 
/*    */   private void onKey() {
/* 42 */     GuiScreen guiscreen = Minecraft.func_71410_x().field_71462_r;
/* 43 */     if (guiscreen != null) {
/* 44 */       return;
/*    */     }
/* 46 */     if (DISABLE.func_151468_f())
/* 47 */       ScenterMod.instance.getClientProxy().setActive(false);
/* 48 */     else if (ENABLE.func_151468_f())
/* 49 */       if (!ScenterMod.instance.getClientProxy().isActive())
/* 50 */         ScenterMod.instance.getClientProxy().setActive(true);
/*    */       else
/* 52 */         ScenterMod.instance.getClientProxy().nextBlockType();
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.client.KeyEventHandler
 * JD-Core Version:    0.6.0
 */