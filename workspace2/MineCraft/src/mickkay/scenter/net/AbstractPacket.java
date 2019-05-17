/*    */ package mickkay.scenter.net;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public abstract class AbstractPacket
/*    */ {
/*    */   public abstract void encodeInto(ChannelHandlerContext paramChannelHandlerContext, ByteBuf paramByteBuf);
/*    */ 
/*    */   public abstract void decodeInto(ChannelHandlerContext paramChannelHandlerContext, ByteBuf paramByteBuf);
/*    */ 
/*    */   public abstract void handleClientSide(EntityPlayer paramEntityPlayer);
/*    */ 
/*    */   public abstract void handleServerSide(EntityPlayer paramEntityPlayer);
/*    */ 
/*    */   protected void writeString(ByteBuf buffer, String string)
/*    */   {
/* 46 */     if (string == null) {
/* 47 */       buffer.writeInt(-1);
/*    */     } else {
/* 49 */       buffer.writeInt(string.length());
/* 50 */       buffer.writeBytes(string.getBytes());
/*    */     }
/*    */   }
/*    */ 
/*    */   protected String readString(ByteBuf buffer) {
/* 55 */     int len = buffer.readInt();
/* 56 */     if (len == -1) {
/* 57 */       return null;
/*    */     }
/* 59 */     byte[] bytes = new byte[len];
/* 60 */     buffer.readBytes(bytes);
/* 61 */     return new String(bytes);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.net.AbstractPacket
 * JD-Core Version:    0.6.0
 */