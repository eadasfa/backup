/*    */ package mickkay.scenter.config;
/*    */ 
/*    */ public class BlockElement
/*    */ {
/*    */   private Integer subtype;
/*    */   private String name;
/*    */ 
/*    */   public BlockElement(String name, Integer subtype)
/*    */   {
/*  9 */     this.name = name;
/* 10 */     this.subtype = subtype;
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 14 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 18 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public Integer getSubtype() {
/* 22 */     return this.subtype;
/*    */   }
/*    */ 
/*    */   public void setSubtype(Integer subtype) {
/* 26 */     this.subtype = subtype;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.config.BlockElement
 * JD-Core Version:    0.6.0
 */