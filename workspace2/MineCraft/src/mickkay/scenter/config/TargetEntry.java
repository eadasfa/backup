/*     */ package mickkay.scenter.config;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class TargetEntry extends Entry
/*     */ {
/*     */   private static final String BLOCK_NAME = "(?:[^ /#]+|\"[^\"#]+\")";
/*  10 */   private static Pattern PATTERN = Pattern.compile("(\\s*)((?:[^ /#]+|\"[^\"#]+\")(?:/\\d+)?(?:\\s*,\\s*(?:[^ /#]+|\"[^\"#]+\")(?:/\\d+)?)*)((\\s+)([a-fA-F0-9]{6}))?((\\s*)#(.*))?");
/*     */ 
/*  12 */   private static Pattern PATTERN_PART = Pattern.compile("((?:[^ /#]+|\"[^\"#]+\"))(/(\\d+))?");
/*     */   private String idIndent;
/*  14 */   private List<BlockElement> blocks = new ArrayList();
/*     */   private String colorIndent;
/*     */   private String color;
/*     */   private String commentIndent;
/*     */   private String comment;
/*     */ 
/*     */   public TargetEntry()
/*     */   {
/*  21 */     super("");
/*  22 */     this.blocks.add(new BlockElement("air", null));
/*     */   }
/*     */ 
/*     */   public TargetEntry(String line) throws IllegalArgumentException {
/*  26 */     super(line);
/*  27 */     Matcher matcher = PATTERN.matcher(line);
/*  28 */     if (!matcher.matches()) {
/*  29 */       throw new IllegalArgumentException(String.format("Can't understand '%s'!", new Object[] { line }));
/*     */     }
/*  31 */     this.idIndent = matcher.group(1);
/*  32 */     this.blocks = parseBlocks(matcher.group(2));
/*  33 */     this.colorIndent = matcher.group(4);
/*  34 */     this.color = matcher.group(5);
/*  35 */     this.commentIndent = matcher.group(7);
/*  36 */     this.comment = matcher.group(8);
/*     */   }
/*     */ 
/*     */   private List<BlockElement> parseBlocks(String blocksStr)
/*     */   {
/*  41 */     String[] tokens = blocksStr.split(",");
/*  42 */     List result = new ArrayList();
/*  43 */     for (String token : tokens) {
/*  44 */       Matcher matcher = PATTERN_PART.matcher(token.trim());
/*  45 */       if (matcher.matches()) {
/*  46 */         String name = matcher.group(1);
/*  47 */         if (name.startsWith("\"")) {
/*  48 */           name = name.replaceAll("\"", "");
/*     */         }
/*  50 */         Integer subtype = toInteger(matcher.group(3));
/*  51 */         result.add(new BlockElement(name, subtype));
/*     */       }
/*     */     }
/*  54 */     if (result.isEmpty()) {
/*  55 */       throw new IllegalArgumentException(String.format("Can't find block data in token '%s'!", new Object[] { blocksStr }));
/*     */     }
/*  57 */     return result;
/*     */   }
/*     */ 
/*     */   private Integer toInteger(String text) {
/*  61 */     if ((text == null) || (text.length() == 0)) {
/*  62 */       return null;
/*     */     }
/*  64 */     return Integer.valueOf(Integer.parseInt(text));
/*     */   }
/*     */ 
/*     */   public List<BlockElement> getBlocks() {
/*  68 */     return this.blocks;
/*     */   }
/*     */ 
/*     */   public boolean hasBlockElement(String blockName) {
/*  72 */     for (BlockElement elem : this.blocks) {
/*  73 */       if (elem.getName().equals(blockName)) {
/*  74 */         return true;
/*     */       }
/*     */     }
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   public void addBlockElement(String blockName) {
/*  81 */     this.blocks.add(new BlockElement(blockName, null));
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  85 */     return ((BlockElement)this.blocks.get(0)).getName();
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  89 */     ((BlockElement)this.blocks.get(0)).setName(name);
/*  90 */     this.idIndent = "";
/*     */   }
/*     */ 
/*     */   public Integer getSubtype() {
/*  94 */     return ((BlockElement)this.blocks.get(0)).getSubtype();
/*     */   }
/*     */ 
/*     */   public void setSubtype(Integer subtype) {
/*  98 */     ((BlockElement)this.blocks.get(0)).setSubtype(subtype);
/*     */   }
/*     */ 
/*     */   public String getColor() {
/* 102 */     return this.color;
/*     */   }
/*     */ 
/*     */   public void setColor(String color) {
/* 106 */     this.color = color;
/* 107 */     if (color == null)
/* 108 */       this.colorIndent = null;
/*     */     else
/* 110 */       this.colorIndent = " ";
/*     */   }
/*     */ 
/*     */   public String getComment()
/*     */   {
/* 115 */     return this.comment;
/*     */   }
/*     */ 
/*     */   public void setComment(String comment) {
/* 119 */     this.comment = comment;
/* 120 */     if (comment == null)
/* 121 */       this.commentIndent = null;
/*     */     else
/* 123 */       this.commentIndent = " ";
/*     */   }
/*     */ 
/*     */   public String getCommentIndent()
/*     */   {
/* 128 */     return this.commentIndent;
/*     */   }
/*     */ 
/*     */   public void setCommentIndent(String commentIndent) {
/* 132 */     this.commentIndent = commentIndent;
/*     */   }
/*     */ 
/*     */   public String getLine()
/*     */   {
/* 137 */     StringBuilder builder = new StringBuilder();
/* 138 */     builder.append(this.idIndent);
/* 139 */     int count = 0;
/* 140 */     for (BlockElement elem : this.blocks) {
/* 141 */       count++;
/* 142 */       if (count > 1) {
/* 143 */         builder.append(",");
/*     */       }
/* 145 */       String name = elem.getName();
/* 146 */       if (name.contains(" ")) {
/* 147 */         name = "\"" + name + "\"";
/*     */       }
/* 149 */       builder.append(name);
/* 150 */       if (getSubtype() != null) {
/* 151 */         builder.append("/");
/* 152 */         builder.append(elem.getSubtype());
/*     */       }
/*     */     }
/* 155 */     if (this.color != null) {
/* 156 */       builder.append(this.colorIndent);
/* 157 */       builder.append(this.color);
/*     */     }
/* 159 */     if (this.comment != null) {
/* 160 */       builder.append(this.commentIndent);
/* 161 */       builder.append("#");
/* 162 */       builder.append(this.comment);
/*     */     }
/* 164 */     return builder.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.config.TargetEntry
 * JD-Core Version:    0.6.0
 */