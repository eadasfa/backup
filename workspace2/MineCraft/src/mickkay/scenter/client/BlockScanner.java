/*     */ package mickkay.scenter.client;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import mickkay.scenter.CommonProxy;
/*     */ import mickkay.scenter.ScenterMod;
/*     */ import mickkay.scenter.config.Target;
/*     */ import mickkay.scenter.config.TargetPart;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class BlockScanner
/*     */ {
/*  20 */   private MyTarget target = new MyTarget();
/*     */ 
/*     */   public void setTarget(Target target)
/*     */   {
/*  74 */     this.target = new MyTarget(target);
/*     */   }
/*     */ 
/*     */   public TargetPart getTargetPart() {
/*  78 */     if (this.target.hasFound()) {
/*  79 */       return this.target.foundTarget;
/*     */     }
/*  81 */     return (TargetPart)this.target.target.getParts().get(0);
/*     */   }
/*     */ 
/*     */   public Color getTargetColor() {
/*  85 */     return this.target.target.getColor();
/*     */   }
/*     */ 
/*     */   public boolean hasFound() {
/*  89 */     return this.target.hasFound();
/*     */   }
/*     */ 
/*     */   public int getFoundX() {
/*  93 */     return this.target.getX();
/*     */   }
/*     */ 
/*     */   public int getFoundY() {
/*  97 */     return this.target.getY();
/*     */   }
/*     */ 
/*     */   public int getFoundZ() {
/* 101 */     return this.target.getZ();
/*     */   }
/*     */ 
/*     */   public double scan(World world, int chunkCoordX, int chunkCoordZ, int px, int py, int pz)
/*     */   {
/* 106 */     this.target.reset();
/* 107 */     int radius = ScenterMod.proxy.getDetectionRadius();
/* 108 */     int minX = -radius;
/* 109 */     int maxX = radius;
/* 110 */     int minZ = -radius;
/* 111 */     int maxZ = radius;
/* 112 */     for (int x = minX; x <= maxX; x++) {
/* 113 */       for (int z = minZ; z <= maxZ; z++) {
/* 114 */         Chunk chunk = world.func_72964_e(chunkCoordX + x, chunkCoordZ + z);
/* 115 */         scan(world, chunk, px, py, pz);
/*     */       }
/*     */     }
/* 118 */     return this.target.getDistance();
/*     */   }
/*     */ 
/*     */   private void scan(World world, Chunk chunk, int px, int py, int pz) {
/* 122 */     if (this.target != null)
/* 123 */       for (int y = 0; y < 255; y++)
/* 124 */         for (int z = 0; z < 16; z++)
/* 125 */           for (int x = 0; x < 16; x++) {
/* 126 */             Block blk = chunk.func_150810_a(x, y, z);
/* 127 */             int metaData = chunk.func_76628_c(x, y, z);
/* 128 */             TargetPart part = this.target.target.getMatchingPart(blk, Integer.valueOf(metaData));
/* 129 */             if (part != null) {
/* 130 */               int bx = chunk.field_76635_g * 16 + x;
/* 131 */               int bz = chunk.field_76647_h * 16 + z;
/*     */ 
/* 135 */               double dist = calculateDistance(bx, y, bz, px, py, pz);
/* 136 */               if (dist < this.target.distance) {
/* 137 */                 MyTarget.access$102(this.target, dist);
/* 138 */                 MyTarget.access$202(this.target, bx);
/* 139 */                 MyTarget.access$302(this.target, y);
/* 140 */                 MyTarget.access$402(this.target, bz);
/* 141 */                 MyTarget.access$002(this.target, part);
/*     */               }
/*     */             }
/*     */           }
/*     */   }
/*     */ 
/*     */   private double calculateDistance(int x1, int y1, int z1, int x2, int y2, int z3)
/*     */   {
/* 151 */     double dx = x1 - x2;
/* 152 */     double dy = y1 - (y2 - 1);
/* 153 */     double dz = z1 - z3;
/* 154 */     double result = Math.sqrt(dx * dx + dy * dy + dz * dz);
/* 155 */     return result;
/*     */   }
/*     */ 
/*     */   public static class MyTarget
/*     */   {
/*     */     public Target target;
/*     */     private TargetPart foundTarget;
/*  25 */     private int x = 0;
/*  26 */     private int y = 0;
/*  27 */     private int z = 0;
/*  28 */     private double distance = 1.7976931348623157E+308D;
/*     */ 
/*     */     public MyTarget() {
/*     */     }
/*     */     public MyTarget(Target target) {
/*  33 */       this.target = target;
/*     */     }
/*     */ 
/*     */     public boolean hasFound() {
/*  37 */       return this.foundTarget != null;
/*     */     }
/*     */ 
/*     */     public int getX() {
/*  41 */       return this.x;
/*     */     }
/*     */ 
/*     */     public int getY() {
/*  45 */       return this.y;
/*     */     }
/*     */ 
/*     */     public int getZ() {
/*  49 */       return this.z;
/*     */     }
/*     */ 
/*     */     public double getDistance() {
/*  53 */       return this.distance;
/*     */     }
/*     */ 
/*     */     public void reset() {
/*  57 */       this.foundTarget = null;
/*  58 */       this.distance = 1.7976931348623157E+308D;
/*  59 */       this.x = 0;
/*  60 */       this.y = 0;
/*  61 */       this.z = 0;
/*     */     }
/*     */ 
/*     */     public ItemStack getItemStack() {
/*  65 */       return ((TargetPart)this.target.getParts().get(0)).itemStack;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.client.BlockScanner
 * JD-Core Version:    0.6.0
 */