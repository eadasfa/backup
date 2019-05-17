/*     */ package mickkay.scenter.config;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import java.lang.reflect.UndeclaredThrowableException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class TargetsConfig
/*     */ {
/*  14 */   private List<Entry> entries = new ArrayList();
/*     */ 
/*     */   public List<Entry> getEntries()
/*     */   {
/*  19 */     return this.entries;
/*     */   }
/*     */ 
/*     */   public void load(Reader reader) throws IOException {
/*  23 */     BufferedReader bufReader = new BufferedReader(reader);
/*     */ 
/*  25 */     int lineNo = 0;
/*     */     try
/*     */     {
/*     */       String line;
/*  27 */       while ((line = bufReader.readLine()) != null) {
/*  28 */         lineNo++;
/*  29 */         if (line.trim().length() == 0) {
/*  30 */           this.entries.add(new EmptyLineEntry(line)); continue;
/*  31 */         }if (CommentEntry.PATTERN.matcher(line).matches()) {
/*  32 */           this.entries.add(new CommentEntry(line)); continue;
/*     */         }
/*  34 */         this.entries.add(new TargetEntry(line));
/*     */       }
/*     */     }
/*     */     catch (IllegalArgumentException e) {
/*  38 */       throw new IOException(String.format("Can't read line %s: %s", new Object[] { Integer.valueOf(lineNo), e.getMessage() }), e);
/*     */     } finally {
/*  40 */       bufReader.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void save(Writer writer) throws IOException {
/*  45 */     int lineNo = 0;
/*  46 */     for (Entry e : this.entries) {
/*  47 */       if (lineNo > 0) {
/*  48 */         writer.append(String.format("%n", new Object[0]));
/*     */       }
/*  50 */       lineNo++;
/*  51 */       writer.append(e.getLine());
/*     */     }
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  57 */     StringWriter writer = new StringWriter();
/*     */     try {
/*  59 */       save(writer);
/*  60 */       writer.close();
/*     */     } catch (IOException e) {
/*  62 */       throw new UndeclaredThrowableException(e);
/*     */     }
/*  64 */     return writer.toString();
/*     */   }
/*     */ 
/*     */   public TargetEntry addTarget(String blockName, String color) {
/*  68 */     TargetEntry newEntry = new TargetEntry();
/*  69 */     newEntry.setName(blockName);
/*  70 */     newEntry.setColor(color);
/*  71 */     getEntries().add(newEntry);
/*  72 */     return newEntry;
/*     */   }
/*     */ 
/*     */   public TargetEntry addTarget(String blockName, String color, String comment) {
/*  76 */     TargetEntry newEntry = new TargetEntry();
/*  77 */     newEntry.setName(blockName);
/*  78 */     newEntry.setColor(color);
/*  79 */     newEntry.setComment(comment);
/*  80 */     getEntries().add(newEntry);
/*  81 */     return newEntry;
/*     */   }
/*     */ 
/*     */   public TargetEntry addTarget(String blockName, Integer subtype, String color, String comment) {
/*  85 */     TargetEntry newEntry = new TargetEntry();
/*  86 */     newEntry.setName(blockName);
/*  87 */     newEntry.setSubtype(subtype);
/*  88 */     newEntry.setColor(color);
/*  89 */     newEntry.setComment(comment);
/*  90 */     getEntries().add(newEntry);
/*  91 */     return newEntry;
/*     */   }
/*     */ 
/*     */   public TargetEntry addTargetAfter(TargetEntry refEntry, String blockName, String color, String comment) {
/*  95 */     TargetEntry newEntry = new TargetEntry();
/*  96 */     newEntry.setName(blockName);
/*  97 */     newEntry.setColor(color);
/*  98 */     newEntry.setComment(comment);
/*     */ 
/* 100 */     int index = this.entries.indexOf(refEntry);
/* 101 */     getEntries().add(index + 1, newEntry);
/*     */ 
/* 103 */     return newEntry;
/*     */   }
/*     */ 
/*     */   public CommentEntry addComment(String comment) {
/* 107 */     CommentEntry newEntry = new CommentEntry();
/* 108 */     newEntry.setComment(comment);
/* 109 */     getEntries().add(newEntry);
/* 110 */     return newEntry;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty() {
/* 114 */     return getEntries().isEmpty();
/*     */   }
/*     */ 
/*     */   public boolean hasTargetEntry(String blockName) {
/* 118 */     return getTargetEntry(blockName) != null;
/*     */   }
/*     */ 
/*     */   public TargetEntry getTargetEntry(String blockName) {
/* 122 */     for (Entry entry : this.entries) {
/* 123 */       if ((entry instanceof TargetEntry)) {
/* 124 */         TargetEntry targetEntry = (TargetEntry)entry;
/* 125 */         if (targetEntry.getName().equals(blockName)) {
/* 126 */           return targetEntry;
/*     */         }
/*     */       }
/*     */     }
/* 130 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.config.TargetsConfig
 * JD-Core Version:    0.6.0
 */