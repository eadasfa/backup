/*     */ package mickkay.scenter.client;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.eventhandler.EventBus;
/*     */ import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.awt.Color;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import java.lang.reflect.UndeclaredThrowableException;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import mickkay.scenter.CommonProxy;
/*     */ import mickkay.scenter.config.ScenterModConfig;
/*     */ import mickkay.scenter.config.Target;
/*     */ import mickkay.scenter.config.TargetPart;
/*     */ import mickkay.scenter.config.TargetsConfig;
/*     */ import mickkay.scenter.net.ConfigMessage;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.RegistryNamespaced;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ClientProxy extends CommonProxy
/*     */ {
/*  37 */   private ParticleTrailEffects particleTrailEffects = new ParticleTrailEffects();
/*     */   private static final String MINECRAFT_KEY_PREFIX = "minecraft:";
/*     */   private BlockScanner blockScanner;
/*     */   private List<Target> targets;
/*  43 */   private int targetIndex = 0;
/*     */   private double hudX;
/*     */   private double hudY;
/*  48 */   private boolean active = false;
/*  49 */   private boolean hasShownDisabledMessage = false;
/*  50 */   private boolean hasShownNoTargetsMessage = false;
/*     */ 
/*  52 */   public Double distance = null;
/*  53 */   public long lastUpdate = 0L;
/*     */ 
/*     */   public void init(File standardConfigFile) throws Exception {
/*  56 */     super.init(standardConfigFile);
/*  57 */     this.blockScanner = new BlockScanner();
/*     */   }
/*     */ 
/*     */   protected void reloadConfiguration() throws IOException
/*     */   {
/*  62 */     super.reloadConfiguration();
/*  63 */     this.hudX = this.config.getHudX();
/*  64 */     this.hudY = this.config.getHudY();
/*  65 */     this.config.save();
/*     */   }
/*     */ 
/*     */   public void registerEventHandlers()
/*     */   {
/*  70 */     super.registerEventHandlers();
/*  71 */     MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
/*  72 */     FMLCommonHandler.instance().bus().register(new TickEventHandler());
/*  73 */     FMLCommonHandler.instance().bus().register(new KeyEventHandler());
/*     */   }
/*     */ 
/*     */   private void reset() {
/*  77 */     this.targets = null;
/*  78 */     this.targetIndex = 0;
/*  79 */     this.hasShownDisabledMessage = false;
/*  80 */     this.hasShownNoTargetsMessage = false;
/*  81 */     this.active = false;
/*     */     try {
/*  83 */       reloadConfiguration();
/*     */     } catch (Exception ex) {
/*  85 */       this.logger.error(String.format("Can't reload configuration", new Object[0]), ex);
/*  86 */       throw new UndeclaredThrowableException(ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onMultiplayerServerMessage(ConfigMessage message) {
/*  91 */     reset();
/*  92 */     this.logger.info(String.format("Scenter %s is installed on this server", new Object[] { message.scenterVersionOnServer }));
/*  93 */     showOnConsole("Scenter is using server-side configuration");
/*     */ 
/*  95 */     setEnabled(message.scenterIsEnabledOnServer);
/*  96 */     setDetectionRadius(message.detectionRadius);
/*  97 */     loadTargetsConfigFromString(message.targetsConfigContent);
/*     */   }
/*     */ 
/*     */   public void onSingleplayerLoggedIn(PlayerEvent.PlayerLoggedInEvent evt)
/*     */   {
/* 102 */     reset();
/*     */   }
/*     */ 
/*     */   public double getHudX()
/*     */   {
/* 107 */     return this.hudX;
/*     */   }
/*     */ 
/*     */   public double getHudY() {
/* 111 */     return this.hudY;
/*     */   }
/*     */ 
/*     */   public boolean isActive() {
/* 115 */     return this.active;
/*     */   }
/*     */ 
/*     */   public void setActive(boolean value) {
/* 119 */     if (!isEnabled()) {
/* 120 */       if (!this.hasShownDisabledMessage) {
/* 121 */         showOnConsole("Can't activate Scenter since it has been disabled on this server!");
/* 122 */         this.hasShownDisabledMessage = true;
/*     */       }
/* 124 */       return;
/*     */     }
/* 126 */     if (value == this.active) {
/* 127 */       return;
/*     */     }
/* 129 */     if (this.targets == null) {
/* 130 */       showAllKnownBlocks();
/* 131 */       if (!initializeTargets()) {
/* 132 */         return;
/*     */       }
/* 134 */       showAllConfiguredTargets();
/*     */     }
/* 136 */     if ((value == true) && (this.targets.size() == 0)) {
/* 137 */       if (!this.hasShownNoTargetsMessage) {
/* 138 */         showOnConsole("Can't activate Scenter since no targets have been configured on this server!");
/* 139 */         this.hasShownNoTargetsMessage = true;
/*     */       }
/* 141 */       return;
/*     */     }
/* 143 */     if (value) {
/* 144 */       this.blockScanner.setTarget(getTarget(this.targetIndex));
/*     */     }
/* 146 */     this.active = value;
/* 147 */     showOnConsole("Scenter is " + (this.active ? "active" : "inactive"));
/* 148 */     findOres();
/*     */   }
/*     */ 
/*     */   private boolean initializeTargets() {
/*     */     try {
/* 153 */       this.targets = this.config.getTargets();
/* 154 */       this.targetIndex = 0;
/* 155 */       return true;
/*     */     } catch (RuntimeException e) {
/* 157 */       showOnConsole("Scenter can't read targets configuration: " + e.getMessage());
/* 158 */       this.logger.error("Scenter can't read targets configuration", e);
/* 159 */     }return false;
/*     */   }
/*     */ 
/*     */   private void showAllKnownBlocks()
/*     */   {
/* 168 */     this.logger.info("Showing all registered blocks...");
/*     */ 
/* 170 */     Set keys = Block.field_149771_c.func_148742_b();
/* 171 */     List listOfKeys = new LinkedList();
/* 172 */     for (Iterator i$ = keys.iterator(); i$.hasNext(); ) { Object key = i$.next();
/* 173 */       if ((key instanceof String)) {
/* 174 */         listOfKeys.add((String)key);
/*     */       }
/*     */     }
/* 177 */     logBlockNames("These blocks are registered:", listOfKeys);
/*     */   }
/*     */ 
/*     */   private void showAllConfiguredTargets() {
/* 181 */     if (this.targets == null) {
/* 182 */       this.logger.info("No targets have been configured!");
/* 183 */       return;
/*     */     }
/* 185 */     Set names = Sets.newHashSet();
/* 186 */     for (Target t : this.targets) {
/* 187 */       for (TargetPart part : t.getParts()) {
/* 188 */         String name = Block.field_149771_c.func_148750_c(part.block);
/* 189 */         names.add(name);
/*     */       }
/*     */     }
/* 192 */     logBlockNames("These targets have been configured:", Lists.newArrayList(names));
/*     */   }
/*     */ 
/*     */   private void logBlockNames(String prefix, List<String> listOfKeys) {
/* 196 */     if ((listOfKeys == null) || (listOfKeys.isEmpty())) {
/* 197 */       this.logger.info(prefix + " <none>");
/* 198 */       return;
/*     */     }
/* 200 */     Collections.sort(listOfKeys);
/* 201 */     StringBuilder builder = new StringBuilder();
/* 202 */     builder.append(prefix);
/* 203 */     builder.append(String.format("%n", new Object[0]));
/* 204 */     for (String key : listOfKeys) {
/* 205 */       if (key.startsWith("minecraft:")) {
/* 206 */         key = key.substring("minecraft:".length());
/*     */       }
/* 208 */       builder.append(key).append(String.format("%n", new Object[0]));
/*     */     }
/* 210 */     this.logger.info(builder.toString());
/*     */   }
/*     */ 
/*     */   public void nextBlockType() {
/* 214 */     this.targetIndex = ((this.targetIndex + 1) % this.targets.size());
/* 215 */     this.blockScanner.setTarget(getTarget(this.targetIndex));
/* 216 */     findOres();
/*     */   }
/*     */ 
/*     */   private Target getTarget(int index) {
/* 220 */     if (this.targets.size() <= index) {
/* 221 */       return null;
/*     */     }
/* 223 */     Target target = (Target)this.targets.get(index);
/* 224 */     return target;
/*     */   }
/*     */ 
/*     */   private void findOres() {
/* 228 */     if (!this.active) {
/* 229 */       return;
/*     */     }
/* 231 */     this.lastUpdate = System.currentTimeMillis();
/* 232 */     EntityPlayer player = FMLClientHandler.instance().getClient().field_71439_g;
/* 233 */     findOres(player, false);
/*     */   }
/*     */ 
/*     */   public boolean findOres(EntityPlayer p, boolean fixYPos) {
/* 237 */     if (!this.active) {
/* 238 */       return false;
/*     */     }
/* 240 */     double dy = fixYPos ? 1.5D : 0.0D;
/* 241 */     double minDist = this.blockScanner.scan(p.field_70170_p, p.field_70176_ah, p.field_70164_aj, (int)p.field_70165_t, (int)p.field_70163_u + (int)dy + 1, (int)p.field_70161_v);
/*     */ 
/* 244 */     if (minDist < 1.7976931348623157E+308D) {
/* 245 */       double fromX = p.field_70165_t;
/* 246 */       double fromY = p.field_70163_u;
/* 247 */       double fromZ = p.field_70161_v;
/* 248 */       Color color = this.blockScanner.getTargetColor();
/* 249 */       spawnParticleTrail(p.field_70170_p, fromX, fromY + dy, fromZ, this.blockScanner.getFoundX(), this.blockScanner.getFoundY(), this.blockScanner.getFoundZ(), color);
/*     */ 
/* 251 */       this.distance = Double.valueOf(minDist);
/* 252 */       this.lastUpdate = System.currentTimeMillis();
/* 253 */       return true;
/*     */     }
/* 255 */     this.distance = null;
/* 256 */     this.lastUpdate = System.currentTimeMillis();
/* 257 */     return false;
/*     */   }
/*     */ 
/*     */   private void spawnParticleTrail(World worldObj, double fromX, double fromY, double fromZ, double toX, double toY, double toZ, Color color)
/*     */   {
/* 263 */     this.particleTrailEffects.spawnParticleTrail(worldObj, fromX, fromY, fromZ, toX, toY, toZ, color);
/*     */   }
/*     */ 
/*     */   public ItemStack getTargetItemStack() {
/* 267 */     return this.blockScanner.getTargetPart().itemStack;
/*     */   }
/*     */ 
/*     */   public String getTargetDisplayName() {
/* 271 */     TargetPart targetPart = this.blockScanner.getTargetPart();
/* 272 */     return getDisplayName(targetPart);
/*     */   }
/*     */ 
/*     */   private String getDisplayName(TargetPart targetPart) {
/* 276 */     String displayName = targetPart.getDisplayName();
/* 277 */     if ((displayName == null) || (displayName.trim().length() == 0)) {
/* 278 */       displayName = Block.field_149771_c.func_148750_c(targetPart.block);
/*     */     }
/* 280 */     return displayName;
/*     */   }
/*     */ 
/*     */   private synchronized void showOnConsole(String msg) {
/* 284 */     if ((FMLClientHandler.instance().getClient() != null) && (FMLClientHandler.instance().getClient().field_71439_g != null))
/* 285 */       FMLClientHandler.instance().getClient().field_71439_g.func_145747_a(new ChatComponentText(msg));
/*     */   }
/*     */ 
/*     */   private void loadTargetsConfigFromString(String targetsConfigContent)
/*     */   {
/* 290 */     if (targetsConfigContent != null)
/*     */       try {
/* 292 */         TargetsConfig targetsConfig = new TargetsConfig();
/* 293 */         targetsConfig.load(new StringReader(targetsConfigContent));
/* 294 */         this.config.setTargetsConfig(targetsConfig);
/*     */       } catch (IOException e) {
/* 296 */         showOnConsole("Scenter can't read server's targets configuration: " + e.getMessage());
/* 297 */         this.logger.error("Scenter can't read server's targets configuration", e);
/* 298 */         setEnabled(false);
/*     */       }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.client.ClientProxy
 * JD-Core Version:    0.6.0
 */