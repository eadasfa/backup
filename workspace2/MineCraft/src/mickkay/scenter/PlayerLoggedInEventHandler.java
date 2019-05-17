/*    */ package mickkay.scenter;
/*    */ 
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class PlayerLoggedInEventHandler
/*    */ {
/* 13 */   private final Logger logger = LogManager.getLogger(ScenterMod.class.getName());
/*    */ 
/* 17 */   @SubscribeEvent
/*    */   public void onLoggedIn(PlayerEvent.PlayerLoggedInEvent evt) { ScenterMod.proxy.onSingleplayerLoggedIn(evt);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.PlayerLoggedInEventHandler
 * JD-Core Version:    0.6.0
 */