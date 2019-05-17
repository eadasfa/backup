/*    */ package mickkay.scenter.config;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class Target
/*    */ {
/* 11 */   private List<TargetPart> parts = new ArrayList();
/* 12 */   private Color color = Color.decode("#4DFF79");
/*    */   private String displayName;
/*    */ 
/*    */   public Color getColor()
/*    */   {
/* 20 */     return this.color;
/*    */   }
/*    */ 
/*    */   public void setColor(Color color) {
/* 24 */     this.color = color;
/*    */   }
/*    */ 
/*    */   public List<TargetPart> getParts() {
/* 28 */     return this.parts;
/*    */   }
/*    */ 
/*    */   public TargetPart getMatchingPart(Block blk, Integer metaData) {
/* 32 */     for (TargetPart part : this.parts) {
/* 33 */       if (part.matches(blk, metaData)) {
/* 34 */         return part;
/*    */       }
/*    */     }
/* 37 */     return null;
/*    */   }
/*    */ 
/*    */   public boolean hasParts() {
/* 41 */     return !this.parts.isEmpty();
/*    */   }
/*    */ 
/*    */   public ItemStack getItemStack()
/*    */   {
/* 46 */     return ((TargetPart)this.parts.get(0)).itemStack;
/*    */   }
/*    */ 
/*    */   public Block getBlock() {
/* 50 */     return ((TargetPart)this.parts.get(0)).block;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.config.Target
 * JD-Core Version:    0.6.0
 */