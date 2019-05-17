/*    */ package mickkay.scenter.config;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileReader;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class TargetsConfigFile
/*    */ {
/* 10 */   private final TargetsConfig config = new TargetsConfig();
/*    */   private File file;
/*    */ 
/*    */   public TargetsConfigFile(File file)
/*    */     throws IOException
/*    */   {
/* 14 */     this.file = file;
/* 15 */     if (file.exists())
/* 16 */       this.config.load(new FileReader(file));
/*    */   }
/*    */ 
/*    */   public TargetsConfig getConfig()
/*    */   {
/* 21 */     return this.config;
/*    */   }
/*    */ 
/*    */   public void save() throws IOException {
/* 25 */     FileWriter writer = new FileWriter(this.file);
/*    */     try {
/* 27 */       this.config.save(writer);
/*    */     } finally {
/* 29 */       writer.close();
/*    */     }
/*    */   }
/*    */ 
/*    */   public boolean exists() {
/* 34 */     return this.file.exists();
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.config.TargetsConfigFile
 * JD-Core Version:    0.6.0
 */