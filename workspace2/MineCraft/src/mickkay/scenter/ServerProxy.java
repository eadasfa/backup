/*    */ package mickkay.scenter;
/*    */ 
/*    */ import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mickkay.scenter.config.ScenterModConfig;
/*    */ import mickkay.scenter.net.ConfigMessage;
/*    */ import mickkay.scenter.net.PacketPipeline;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ 
/*    */ @SideOnly(Side.SERVER)
/*    */ public class ServerProxy extends CommonProxy
/*    */ {
/*    */   public void onSingleplayerLoggedIn(PlayerEvent.PlayerLoggedInEvent evt)
/*    */   {
/* 14 */     ConfigMessage message = new ConfigMessage();
/* 15 */     message.scenterVersionOnServer = ScenterMod.instance.getVersion();
/* 16 */     message.scenterIsEnabledOnServer = isEnabled();
/* 17 */     message.targetsConfigContent = this.config.getTargetsConfigAsString();
/* 18 */     message.detectionRadius = this.config.getDetectionRadius();
/* 19 */     EntityPlayerMP player = (EntityPlayerMP)evt.player;
/* 20 */     ScenterMod.instance.getPacketPipeline().sendTo(message, player);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.ServerProxy
 * JD-Core Version:    0.6.0
 */