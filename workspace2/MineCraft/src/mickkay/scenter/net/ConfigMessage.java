/*    */ package mickkay.scenter.net;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import mickkay.scenter.ScenterMod;
/*    */ import mickkay.scenter.client.ClientProxy;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class ConfigMessage extends AbstractPacket
/*    */ {
/*    */   public String scenterVersionOnServer;
/*    */   public boolean scenterIsEnabledOnServer;
/*    */   public String targetsConfigContent;
/*    */   public int detectionRadius;
/*    */ 
/*    */   public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
/*    */   {
/* 17 */     writeString(buffer, this.scenterVersionOnServer);
/* 18 */     buffer.writeBoolean(this.scenterIsEnabledOnServer);
/* 19 */     writeString(buffer, this.targetsConfigContent);
/* 20 */     buffer.writeInt(this.detectionRadius);
/*    */   }
/*    */ 
/*    */   public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
/*    */   {
/* 25 */     this.scenterVersionOnServer = readString(buffer);
/* 26 */     this.scenterIsEnabledOnServer = buffer.readBoolean();
/* 27 */     this.targetsConfigContent = readString(buffer);
/* 28 */     this.detectionRadius = buffer.readInt();
/*    */   }
/*    */ 
/*    */   public void handleClientSide(EntityPlayer player)
/*    */   {
/* 33 */     ScenterMod.instance.getClientProxy().onMultiplayerServerMessage(this);
/*    */   }
/*    */ 
/*    */   public void handleServerSide(EntityPlayer player)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.net.ConfigMessage
 * JD-Core Version:    0.6.0
 */