/*    */ package mickkay.scenter.config;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class TargetPart
/*    */ {
/*    */   public final Integer metaData;
/*    */   public final Block block;
/*    */   public final ItemStack itemStack;
/*    */ 
/*    */   public TargetPart(Block block, Integer metaData, ItemStack itemStack)
/*    */   {
/* 15 */     this.metaData = metaData;
/* 16 */     this.block = block;
/* 17 */     this.itemStack = itemStack;
/*    */   }
/*    */ 
/*    */   public boolean matches(Block block, Integer metaData) {
/* 21 */     if (!this.block.equals(block)) {
/* 22 */       return false;
/*    */     }
/* 24 */     if (this.metaData == null)
/*    */     {
/* 27 */       return true;
/*    */     }
/* 29 */     return this.metaData.equals(metaData);
/*    */   }
/*    */ 
/*    */   public String getDisplayName()
/*    */   {
/* 34 */     String displayName = null;
/*    */     try {
/* 36 */       displayName = this.itemStack.func_82833_r();
/*    */     }
/*    */     catch (Exception ex) {
/*    */     }
/* 40 */     if ((displayName == null) || (displayName.trim().length() == 0)) {
/* 41 */       displayName = this.block.func_149732_F();
/*    */     }
/* 43 */     if (displayName == null) {
/* 44 */       displayName = "";
/*    */     }
/* 46 */     return displayName;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.config.TargetPart
 * JD-Core Version:    0.6.0
 */