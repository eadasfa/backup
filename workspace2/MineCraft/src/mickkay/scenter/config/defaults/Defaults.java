/*    */ package mickkay.scenter.config.defaults;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.RegistryNamespaced;
/*    */ 
/*    */ public class Defaults
/*    */ {
/*    */   private static final String COLOR_GOLD = "FFFF47";
/*    */   private static final String COLOR_IRON = "FFD9B3";
/*    */   private static final String COLOR_COAL = "000000";
/*    */   private static final String COLOR_LAPIS = "0A0AFF";
/*    */   private static final String COLOR_DIAMOND = "85FFFF";
/*    */   private static final String COLOR_REDSTONE = "FF0A0A";
/*    */   private static final String COLOR_GLOWSTONE = "FFD24D";
/*    */   private static final String COLOR_EMERALD = "80FF9F";
/*    */   private static final String COLOR_NETHER_QUARZ = "BAA882";
/* 41 */   private Map<String, BlockDefault> map = new LinkedHashMap();
/*    */ 
/*    */   public Defaults() {
/* 44 */     addBlock(Blocks.field_150365_q, "000000");
/* 45 */     addBlock(Blocks.field_150366_p, "FFD9B3");
/* 46 */     addBlock(Blocks.field_150352_o, "FFFF47");
/* 47 */     addBlock(Blocks.field_150482_ag, "85FFFF");
/* 48 */     addBlock(Blocks.field_150412_bA, "80FF9F");
/* 49 */     addBlock(Blocks.field_150369_x, "0A0AFF");
/* 50 */     addBlock(new Block[] { Blocks.field_150450_ax, Blocks.field_150439_ay }, "FF0A0A", "and Glowing Redstone");
/* 51 */     addBlock(Blocks.field_150426_aN, "FFD24D");
/* 52 */     addBlock(Blocks.field_150449_bY, "BAA882");
/*    */   }
/*    */ 
/*    */   private void addBlock(Block block, String color) {
/* 56 */     BlockDefault entry = new BlockDefault(new Block[] { block }, color);
/* 57 */     this.map.put(getName(block), entry);
/*    */   }
/*    */ 
/*    */   private void addBlock(Block block, String color, String comment) {
/* 61 */     BlockDefault entry = new BlockDefault(new Block[] { block }, color, comment);
/* 62 */     this.map.put(getName(block), entry);
/*    */   }
/*    */ 
/*    */   private void addBlock(Block[] block, String color, String comment) {
/* 66 */     BlockDefault entry = new BlockDefault(block, color, comment);
/* 67 */     this.map.put(getName(block[0]), entry);
/*    */   }
/*    */ 
/*    */   public BlockDefault getBlockDefault(int blockId) {
/* 71 */     return (BlockDefault)this.map.get(Integer.valueOf(blockId));
/*    */   }
/*    */ 
/*    */   public Collection<BlockDefault> getBlockDefaults() {
/* 75 */     return this.map.values();
/*    */   }
/*    */ 
/*    */   private String getName(Block block) {
/* 79 */     return Block.field_149771_c.func_148750_c(block);
/*    */   }
/*    */ 
/*    */   public static class BlockDefault
/*    */   {
/*    */     public final Block[] block;
/*    */     public final String comment;
/*    */     public final String color;
/*    */ 
/*    */     public BlockDefault(Block[] block, String color)
/*    */     {
/* 28 */       this.block = block;
/* 29 */       this.color = color;
/* 30 */       this.comment = null;
/*    */     }
/*    */ 
/*    */     public BlockDefault(Block[] block, String color, String comment)
/*    */     {
/* 35 */       this.block = block;
/* 36 */       this.comment = comment;
/* 37 */       this.color = color;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.config.defaults.Defaults
 * JD-Core Version:    0.6.0
 */