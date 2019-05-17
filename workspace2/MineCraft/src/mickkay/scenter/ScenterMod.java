/*    */ package mickkay.scenter;
/*    */ 
/*    */ import cpw.mods.fml.common.Mod;
/*    */ import cpw.mods.fml.common.Mod.EventHandler;
/*    */ import cpw.mods.fml.common.Mod.Instance;
/*    */ import cpw.mods.fml.common.SidedProxy;
/*    */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*    */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*    */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import mickkay.scenter.client.ClientProxy;
/*    */ import mickkay.scenter.net.ConfigMessage;
/*    */ import mickkay.scenter.net.PacketPipeline;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ @Mod(modid="Scenter", name="Scenter", version="1.7.10-3.2.0")
/*    */ public class ScenterMod
/*    */ {
/*    */   public static final String NAME = "Scenter";
/*    */   public static final String ID = "Scenter";
/*    */   private static final String CHANNEL_NAME = "Scenter";
/*    */   public static final String VERSION = "1.7.10-3.2.0";
/*    */   private static final String SCENTER_COMMON_PROXY = "mickkay.scenter.ServerProxy";
/*    */   private static final String SCENTER_CLIENT_PROXY = "mickkay.scenter.client.ClientProxy";
/*    */   private static final String PARTICLE_TYPE = "happyVillager";
/* 34 */   private final Logger logger = LogManager.getLogger(ScenterMod.class.getName());
/*    */ 
/*    */   @Mod.Instance("Scenter")
/*    */   public static ScenterMod instance;
/*    */ 
/*    */   @SidedProxy(clientSide="mickkay.scenter.client.ClientProxy", serverSide="mickkay.scenter.ServerProxy")
/*    */   public static CommonProxy proxy;
/* 42 */   public final PacketPipeline packetPipeline = new PacketPipeline(this.logger, "Scenter");
/*    */   private File standardConfigFile;
/*    */ 
/* 48 */   @Mod.EventHandler
/*    */   public void preInit(FMLPreInitializationEvent event) throws Exception { this.standardConfigFile = event.getSuggestedConfigurationFile();
/*    */     try {
/* 50 */       proxy.init(this.standardConfigFile);
/*    */     } catch (Exception ex) {
/* 52 */       this.logger.error(String.format("Can't initialize $s:", new Object[] { "Scenter" }), ex);
/* 53 */       throw ex;
/*    */     } }
/*    */ 
/*    */   @Mod.EventHandler
/*    */   public void load(FMLInitializationEvent event) {
/* 59 */     this.packetPipeline.initialise();
/* 60 */     this.packetPipeline.registerPacket(ConfigMessage.class);
/*    */ 
/* 62 */     proxy.registerEventHandlers();
/*    */   }
/*    */   @Mod.EventHandler
/*    */   public void postInit(FMLPostInitializationEvent event) throws IOException {
/* 67 */     this.packetPipeline.postInitialise();
/* 68 */     this.logger.info(String.format("** %s initialized. **", new Object[] { "Scenter" }));
/*    */   }
/*    */ 
/*    */   public String getVersion() {
/* 72 */     return "1.7.10-3.2.0";
/*    */   }
/*    */   @SideOnly(Side.CLIENT)
/*    */   public ClientProxy getClientProxy() {
/* 77 */     return (ClientProxy)proxy;
/*    */   }
/*    */   @SideOnly(Side.SERVER)
/*    */   public ServerProxy getServerProxy() {
/* 82 */     return (ServerProxy)proxy;
/*    */   }
/*    */ 
/*    */   public PacketPipeline getPacketPipeline() {
/* 86 */     return this.packetPipeline;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.ScenterMod
 * JD-Core Version:    0.6.0
 */