/*    */ package mickkay.scenter.client;
/*    */ 
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.logging.Logger;
/*    */ import mickkay.scenter.CommonProxy;
/*    */ import mickkay.scenter.ScenterMod;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class PlayerEventHandler
/*    */ {
/*    */   private static final long PAUSE_TIME_MILIS = 500L;
/* 18 */   private final Logger logger = Logger.getLogger(ScenterMod.class.getName());
/*    */ 
/* 20 */   private int eventCount = 0;
/* 21 */   private long lastCall = 0L;
/*    */ 
/*    */   public PlayerEventHandler()
/*    */   {
/* 25 */     this.logger.info(String.format("Creating %s", new Object[] { getClass().getSimpleName() }));
/*    */   }
/*    */ 
/*    */   @SubscribeEvent
/*    */   public void handle(PlayerInteractEvent evt) {
/* 31 */     if (!ScenterMod.proxy.isEnabled()) {
/* 32 */       return;
/*    */     }
/* 34 */     if (this.lastCall + 500L < System.currentTimeMillis()) {
/* 35 */       this.lastCall = System.currentTimeMillis();
/* 36 */       boolean fixYPos = evt.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK;
/* 37 */       ScenterMod.instance.getClientProxy().findOres(evt.entityPlayer, fixYPos);
/*    */     }
/*    */   }
/*    */ 
/*    */   @SubscribeEvent
/*    */   public void handle(PlayerEvent.BreakSpeed evt) {
/* 43 */     if (!ScenterMod.proxy.isEnabled()) {
/* 44 */       return;
/*    */     }
/* 46 */     if (this.lastCall + 500L < System.currentTimeMillis()) {
/* 47 */       this.lastCall = System.currentTimeMillis();
/* 48 */       ScenterMod.instance.getClientProxy().findOres(evt.entityPlayer, false);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.client.PlayerEventHandler
 * JD-Core Version:    0.6.0
 */