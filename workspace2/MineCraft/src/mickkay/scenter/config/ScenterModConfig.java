/*     */ package mickkay.scenter.config;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.awt.Color;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.StringWriter;
/*     */ import java.lang.reflect.UndeclaredThrowableException;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import mickkay.scenter.ScenterMod;
/*     */ import mickkay.scenter.config.defaults.Defaults;
/*     */ import mickkay.scenter.config.defaults.Defaults.BlockDefault;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.RegistryNamespaced;
/*     */ import net.minecraftforge.common.config.Configuration;
/*     */ import net.minecraftforge.common.config.Property;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ScenterModConfig
/*     */ {
/*     */   private static final String CFG_CATEGORY_GENERAL = "general";
/*     */   private static final String CFG_PROP_DETECTION_RANGE = "detectionRange";
/*     */   private static final String CFG_PROP_VERSION = "version";
/*     */   private static final String CFG_PROP_ENABLED = "enabled";
/*     */   private static final String CFG_CATEGORY_DISPLAY = "display";
/*     */   private static final String CFG_PROP_HUD_X = "hudX";
/*     */   private static final String CFG_PROP_HUD_Y = "hudY";
/*  44 */   private final Logger logger = LogManager.getLogger(ScenterMod.class.getName());
/*     */ 
/*  46 */   private final Defaults defaults = new Defaults();
/*     */   private final File configFile;
/*     */   private final Configuration config;
/*     */   private final TargetsConfigFile targetsConfigFile;
/*     */   private TargetsConfig targetsConfig;
/*     */   private List<Target> targets;
/*     */ 
/*     */   public ScenterModConfig(File aConfigFile)
/*     */     throws IOException
/*     */   {
/*  63 */     this.configFile = aConfigFile;
/*  64 */     this.config = new Configuration(aConfigFile);
/*  65 */     this.config.load();
/*  66 */     this.targetsConfigFile = new TargetsConfigFile(getTargetsConfigFile(this.configFile));
/*  67 */     this.targetsConfig = this.targetsConfigFile.getConfig();
/*     */ 
/*  69 */     upgradeConfigsToCurrentVersion();
/*  70 */     this.config.save();
/*     */   }
/*     */ 
/*     */   public void save() {
/*  74 */     this.config.save();
/*     */   }
/*     */ 
/*     */   public boolean isEnabled() {
/*  78 */     return getEnabledProperty().getBoolean(true);
/*     */   }
/*     */ 
/*     */   public double getHudX() {
/*  82 */     return getHudXProperty().getDouble(0.05D);
/*     */   }
/*     */ 
/*     */   public double getHudY() {
/*  86 */     return getHudYProperty().getDouble(0.05D); } 
/*     */   public int getDetectionRadius() { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial 145	mickkay/scenter/config/ScenterModConfig:getDetectionRangeProperty	()Lnet/minecraftforge/common/config/Property;
/*     */     //   4: invokevirtual 148	net/minecraftforge/common/config/Property:getString	()Ljava/lang/String;
/*     */     //   7: astore_1
/*     */     //   8: new 150	java/lang/StringBuilder
/*     */     //   11: dup
/*     */     //   12: invokespecial 151	java/lang/StringBuilder:<init>	()V
/*     */     //   15: ldc 153
/*     */     //   17: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   20: aload_1
/*     */     //   21: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   24: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   27: invokestatic 164	mickkay/scenter/config/ScenterModConfig$DetectionRange:valueOf	(Ljava/lang/String;)Lmickkay/scenter/config/ScenterModConfig$DetectionRange;
/*     */     //   30: getfield 168	mickkay/scenter/config/ScenterModConfig$DetectionRange:radius	I
/*     */     //   33: istore_2
/*     */     //   34: iload_2
/*     */     //   35: ireturn
/*     */     //   36: astore_2
/*     */     //   37: new 172	java/lang/IllegalStateException
/*     */     //   40: dup
/*     */     //   41: ldc 174
/*     */     //   43: iconst_1
/*     */     //   44: anewarray 4	java/lang/Object
/*     */     //   47: dup
/*     */     //   48: iconst_0
/*     */     //   49: aload_1
/*     */     //   50: aastore
/*     */     //   51: invokestatic 178	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   54: invokespecial 181	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
/*     */     //   57: athrow
/*     */     //
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   8	35	36	java/lang/IllegalArgumentException } 
/* 100 */   public List<Target> getTargets() { if (this.targets == null) {
/* 101 */       this.targets = loadTargets();
/*     */     }
/* 103 */     return this.targets; }
/*     */ 
/*     */   public void setTargetsConfig(TargetsConfig config)
/*     */   {
/* 107 */     this.targetsConfig = config;
/* 108 */     this.targets = null;
/*     */   }
/*     */ 
/*     */   public String getTargetsConfigAsString() {
/* 112 */     StringWriter writer = new StringWriter();
/*     */     try {
/* 114 */       this.targetsConfig.save(writer);
/*     */     }
/*     */     catch (IOException e) {
/* 117 */       throw new UndeclaredThrowableException(e);
/*     */     }
/* 119 */     return writer.toString();
/*     */   }
/*     */ 
/*     */   private List<Target> loadTargets() {
/* 123 */     List result = Lists.newArrayList();
/* 124 */     for (Entry entry : this.targetsConfig.getEntries()) {
/* 125 */       if ((entry instanceof TargetEntry)) {
/* 126 */         TargetEntry targetEntry = (TargetEntry)entry;
/* 127 */         Target target = new Target();
/* 128 */         if (targetEntry.getColor() != null) {
/* 129 */           Color color = Color.decode("#" + targetEntry.getColor());
/* 130 */           target.setColor(color);
/*     */         }
/* 132 */         for (BlockElement blockElem : targetEntry.getBlocks())
/*     */         {
/* 134 */           String blockName = blockElem.getName();
/* 135 */           Integer dmg = blockElem.getSubtype();
/* 136 */           Object obj = Block.field_149771_c.func_82594_a(blockName);
/* 137 */           if ((obj != null) && ((obj instanceof Block))) {
/* 138 */             Block block = (Block)obj;
/*     */ 
/* 140 */             ItemStack stack = new ItemStack(block, 1, dmg == null ? 0 : dmg.intValue());
/* 141 */             String idStr = blockName + (dmg == null ? "" : new StringBuilder().append("/").append(dmg).toString());
/* 142 */             this.logger.info(String.format("Scenter: Adding block %s.", new Object[] { idStr }));
/*     */ 
/* 144 */             target.getParts().add(new TargetPart(block, dmg, stack));
/*     */           } else {
/* 146 */             this.logger.warn(String.format("Scenter: Error - block with name '%s' not found!", new Object[] { blockName }));
/*     */           }
/*     */         }
/* 149 */         if (target.hasParts()) {
/* 150 */           result.add(target);
/*     */         }
/*     */       }
/*     */     }
/* 154 */     return result;
/*     */   }
/*     */ 
/*     */   private void upgradeConfigsToCurrentVersion() throws IOException {
/* 158 */     if (isEmpty(this.config))
/*     */     {
/* 160 */       makeStandardConfig(this.config);
/*     */     }
/* 162 */     else setConfigVersion("1.7.10-3.2.0");
/*     */ 
/* 165 */     if (!this.targetsConfigFile.exists())
/*     */     {
/* 167 */       makeStandardTargetsConfig(this.targetsConfig);
/* 168 */       this.targetsConfigFile.save();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void setConfigVersion(String aNewVersion)
/*     */   {
/* 177 */     Property val = getConfigVersionProperty();
/* 178 */     val.set(aNewVersion);
/*     */   }
/*     */ 
/*     */   private boolean isCurrentVersion(Configuration config) {
/* 182 */     if (!hasConfigVersion()) {
/* 183 */       return false;
/*     */     }
/* 185 */     String configVersion = getConfigVersion();
/*     */ 
/* 187 */     return (configVersion != null) && (configVersion.equals("1.7.10-3.2.0"));
/*     */   }
/*     */ 
/*     */   private boolean hasConfigVersion()
/*     */   {
/* 194 */     return this.config.hasKey("general", "version");
/*     */   }
/*     */ 
/*     */   private String getConfigVersion() {
/* 198 */     Property val = getConfigVersionProperty();
/* 199 */     return val.getString();
/*     */   }
/*     */ 
/*     */   private Property getConfigVersionProperty() {
/* 203 */     return this.config.get("general", "version", "", "Syntax version. Do NOT manually change this value!");
/*     */   }
/*     */ 
/*     */   private Property getEnabledProperty() {
/* 207 */     Property p = this.config.get("general", "enabled", true, "Set to 'true' to enable Scenter. Set to 'false' to disable Scenter.");
/*     */ 
/* 210 */     return p;
/*     */   }
/*     */ 
/*     */   private Property getHudXProperty() {
/* 214 */     Property p = this.config.get("display", "hudX", 0.05D, "The x coordinate of the HUD (Head-Up Display). Valid is anything between 0.0 (left) and 1.0 (right).");
/*     */ 
/* 218 */     return p;
/*     */   }
/*     */ 
/*     */   private Property getHudYProperty() {
/* 222 */     Property p = this.config.get("display", "hudY", 0.05D, "The y coordinate of the HUD (Head-Up Display). Valid is anything between 0.0 (top) and 1.0 (bottom).");
/*     */ 
/* 226 */     return p;
/*     */   }
/*     */ 
/*     */   private Property getDetectionRangeProperty() {
/* 230 */     Property p = this.config.get("general", "detectionRange", "3x3", "The number of chunks that are searched by Scenter. Valid values are 1, 3x3, 5x5, 7x7, 9x9, 11x11.");
/*     */ 
/* 234 */     return p;
/*     */   }
/*     */ 
/*     */   private void makeBackup(File fromFile) throws IOException {
/* 238 */     if (!fromFile.exists()) {
/* 239 */       return;
/* 241 */     }int num = 0;
/*     */     File backupFile;
/*     */     do {
/* 245 */       num++;
/* 246 */       String backupName = fromFile.getName() + ".bak" + num;
/* 247 */       backupFile = new File(fromFile.getParentFile(), backupName);
/* 248 */     }while (backupFile.exists());
/* 249 */     copyFile(fromFile, backupFile);
/*     */   }
/*     */ 
/*     */   private void copyFile(File fromFile, File toFile) throws IOException {
/* 253 */     FileInputStream fin = null;
/* 254 */     FileOutputStream fout = null;
/*     */     try
/*     */     {
/* 257 */       fin = new FileInputStream(fromFile);
/*     */ 
/* 260 */       fout = new FileOutputStream(toFile);
/*     */ 
/* 262 */       byte[] b = new byte[1024];
/* 263 */       int noOfBytes = 0;
/*     */ 
/* 266 */       while ((noOfBytes = fin.read(b)) != -1)
/* 267 */         fout.write(b, 0, noOfBytes);
/*     */     }
/*     */     finally
/*     */     {
/* 271 */       if (fout != null) {
/* 272 */         fout.close();
/*     */       }
/* 274 */       if (fin != null)
/* 275 */         fin.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean isEmpty(TargetsConfigFile targetsConfigFile)
/*     */   {
/* 282 */     return targetsConfigFile.getConfig().isEmpty();
/*     */   }
/*     */ 
/*     */   private File getTargetsConfigFile(File configFile) {
/* 286 */     String defaultConfigName = configFile.getName();
/* 287 */     String name = defaultConfigName.substring(0, defaultConfigName.indexOf('.'));
/* 288 */     String targetsConfigName = name + "3.targets.cfg";
/* 289 */     return new File(configFile.getParentFile(), targetsConfigName);
/*     */   }
/*     */ 
/*     */   private void makeStandardTargetsConfig(TargetsConfig targetsConfig) {
/* 293 */     addStandardHeaderComments(targetsConfig);
/* 294 */     for (Defaults.BlockDefault blockDef : this.defaults.getBlockDefaults())
/* 295 */       addTargetFor(targetsConfig, blockDef);
/*     */   }
/*     */ 
/*     */   private void addTargetFor(TargetsConfig cfg, Defaults.BlockDefault blockDef)
/*     */   {
/* 300 */     if (blockDef.block.length == 1)
/*     */     {
/* 302 */       cfg.addTarget(getName(blockDef.block[0]), blockDef.color);
/*     */     }
/*     */     else {
/* 305 */       TargetEntry target = cfg.addTarget(getName(blockDef.block[0]), blockDef.color);
/* 306 */       for (int i = 1; i < blockDef.block.length; i++)
/* 307 */         target.addBlockElement(getName(blockDef.block[i]));
/*     */     }
/*     */   }
/*     */ 
/*     */   private String getName(Block block)
/*     */   {
/* 313 */     String name = Block.field_149771_c.func_148750_c(block);
/* 314 */     if (name.startsWith("minecraft:")) {
/* 315 */       return name.substring("minecraft:".length());
/*     */     }
/* 317 */     return name;
/*     */   }
/*     */ 
/*     */   private void addStandardHeaderComments(TargetsConfig cfg) {
/* 321 */     cfg.addComment("## Scenter Targets Definitions");
/* 322 */     cfg.addComment("# During game play you can toggle between all defined targets by pressing 'O'.");
/* 323 */     cfg.addComment("# With this file you can define these targets.");
/* 324 */     cfg.addComment("#");
/* 325 */     cfg.addComment("# Each line adds one target to Scenter.");
/* 326 */     cfg.addComment("# A target definition consists of one ore more blocks and an optional color (for tinting the particle trail).");
/* 327 */     cfg.addComment("#");
/* 328 */     cfg.addComment("# The line format is:");
/* 329 */     cfg.addComment("# <block-list> [<color (hexadecimal)>]");
/* 330 */     cfg.addComment("#   where <block-list> is <block>[, <block>] ... ");
/* 331 */     cfg.addComment("#   and where <block> is <block-name>[/<subtype>]");
/* 332 */     cfg.addComment("#");
/* 333 */     cfg.addComment("# Example 1:");
/* 334 */     cfg.addComment("#  obsidian 000000");
/* 335 */     cfg.addComment("#  adds a target for obsidian, with a black (000000) particle trail color");
/* 336 */     cfg.addComment("# Example 2:");
/* 337 */     cfg.addComment("#  lava, flowing_lava FF0000 ");
/* 338 */     cfg.addComment("#  adds a target for lava and flowing lava, with a red (FF0000) particle trail color");
/* 339 */     cfg.addComment("# Example 3:");
/* 340 */     cfg.addComment("#  wool/14, wool/4, wool/6 FFFFFF ");
/* 341 */     cfg.addComment("#  adds a target for red (/14), yellow (/4), and pink (/6) wool, with a white (FFFFFF) particle trail color");
/* 342 */     cfg.addComment("");
/* 343 */     cfg.addComment(" Use '#' for comments");
/* 344 */     cfg.addComment("");
/*     */   }
/*     */ 
/*     */   private boolean isEmpty(Configuration config) {
/* 348 */     return config.getCategoryNames().isEmpty();
/*     */   }
/*     */ 
/*     */   private void makeStandardConfig(Configuration config) {
/* 352 */     setConfigVersion("1.7.10-3.2.0");
/*     */   }
/*     */ 
/*     */   static enum DetectionRange
/*     */   {
/*  55 */     A1(0), A3x3(1), A5x5(2), A7x7(3), A9x9(4), A11x11(5);
/*     */ 
/*     */     public final int radius;
/*     */ 
/*  58 */     private DetectionRange(int radius) { this.radius = radius;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.config.ScenterModConfig
 * JD-Core Version:    0.6.0
 */