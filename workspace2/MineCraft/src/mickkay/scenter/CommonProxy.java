/*    */ package mickkay.scenter;
/*    */ 
/*    */ import cpw.mods.fml.common.FMLCommonHandler;
/*    */ import cpw.mods.fml.common.eventhandler.EventBus;
/*    */ import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import mickkay.scenter.config.ScenterModConfig;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public abstract class CommonProxy
/*    */ {
/*    */   private static final String MINECRAFT_KEY_PREFIX = "minecraft:";
/* 16 */   protected final Logger logger = LogManager.getLogger(ScenterMod.class.getName());
/*    */   protected File standardConfigFile;
/* 20 */   protected ScenterModConfig config = null;
/*    */   private boolean enabled;
/*    */   private int detectionRadius;
/*    */ 
/*    */   public void init(File standardConfigFile)
/*    */     throws Exception
/*    */   {
/* 25 */     this.standardConfigFile = standardConfigFile;
/* 26 */     reloadConfiguration();
/*    */   }
/*    */ 
/*    */   protected void reloadConfiguration() throws IOException {
/* 30 */     this.logger.info(String.format("Loading configuration", new Object[0]));
/* 31 */     this.config = new ScenterModConfig(this.standardConfigFile);
/* 32 */     this.enabled = this.config.isEnabled();
/* 33 */     this.detectionRadius = this.config.getDetectionRadius();
/* 34 */     this.config.save();
/*    */   }
/*    */ 
/*    */   public void registerEventHandlers() {
/* 38 */     FMLCommonHandler.instance().bus().register(new PlayerLoggedInEventHandler());
/*    */   }
/*    */ 
/*    */   public boolean isEnabled()
/*    */   {
/* 44 */     return this.enabled;
/*    */   }
/*    */ 
/*    */   public void setEnabled(boolean enabled) {
/* 48 */     this.enabled = enabled;
/*    */   }
/*    */ 
/*    */   public int getDetectionRadius() {
/* 52 */     return this.detectionRadius;
/*    */   }
/*    */ 
/*    */   public void setDetectionRadius(int scanRadius) {
/* 56 */     this.detectionRadius = scanRadius;
/*    */   }
/*    */ 
/*    */   public abstract void onSingleplayerLoggedIn(PlayerEvent.PlayerLoggedInEvent paramPlayerLoggedInEvent);
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.CommonProxy
 * JD-Core Version:    0.6.0
 */