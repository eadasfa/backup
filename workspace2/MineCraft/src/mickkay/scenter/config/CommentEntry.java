/*    */ package mickkay.scenter.config;
/*    */ 
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class CommentEntry extends Entry
/*    */ {
/*  7 */   public static Pattern PATTERN = Pattern.compile("^\\s*#(.*)?");
/*    */   private String comment;
/*    */ 
/*    */   public CommentEntry(String line)
/*    */   {
/* 11 */     super(line);
/* 12 */     Matcher matcher = PATTERN.matcher(line);
/* 13 */     if (!matcher.matches()) {
/* 14 */       throw new IllegalArgumentException(String.format("Can't understand '%s'!", new Object[] { line }));
/*    */     }
/* 16 */     this.comment = matcher.group(1);
/*    */   }
/*    */ 
/*    */   public CommentEntry()
/*    */   {
/* 22 */     super("");
/*    */   }
/*    */ 
/*    */   public String getComment() {
/* 26 */     return this.comment;
/*    */   }
/*    */ 
/*    */   public void setComment(String comment) {
/* 30 */     this.comment = comment;
/*    */   }
/*    */ 
/*    */   public String getLine()
/*    */   {
/* 35 */     return "#" + this.comment;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.config.CommentEntry
 * JD-Core Version:    0.6.0
 */