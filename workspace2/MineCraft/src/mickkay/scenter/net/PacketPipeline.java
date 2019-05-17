/*     */ package mickkay.scenter.net;
/*     */ 
/*     */ import cpw.mods.fml.common.network.FMLEmbeddedChannel;
/*     */ import cpw.mods.fml.common.network.FMLOutboundHandler;
/*     */ import cpw.mods.fml.common.network.FMLOutboundHandler.OutboundTarget;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.internal.FMLProxyPacket;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelHandler.Sharable;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.handler.codec.MessageToMessageCodec;
/*     */ import io.netty.util.Attribute;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.EnumMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.NetHandlerPlayServer;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ @ChannelHandler.Sharable
/*     */ public class PacketPipeline extends MessageToMessageCodec<FMLProxyPacket, AbstractPacket>
/*     */ {
/*     */   private final Logger logger;
/*     */   private EnumMap<Side, FMLEmbeddedChannel> channels;
/*  42 */   private LinkedList<Class<? extends AbstractPacket>> packets = new LinkedList();
/*  43 */   private boolean isPostInitialised = false;
/*     */   private String channelName;
/*     */ 
/*     */   public PacketPipeline(Logger logger, String channelName)
/*     */   {
/*  48 */     this.logger = logger;
/*  49 */     this.channelName = channelName;
/*     */   }
/*     */ 
/*     */   public boolean registerPacket(Class<? extends AbstractPacket> clazz)
/*     */   {
/*  61 */     if (this.packets.size() > 256) {
/*  62 */       this.logger.error(String.format("Can't register packet %s. Number of packets exceeded 256.", new Object[0]));
/*  63 */       return false;
/*     */     }
/*     */ 
/*  66 */     if (this.packets.contains(clazz)) {
/*  67 */       this.logger.error(String.format("Can't register packet %s. Packet already registered.", new Object[0]));
/*  68 */       return false;
/*     */     }
/*     */ 
/*  71 */     if (this.isPostInitialised) {
/*  72 */       this.logger.error(String.format("Can't register packet %s. PacketPipeline has been already initialized.", new Object[0]));
/*  73 */       return false;
/*     */     }
/*     */ 
/*  76 */     this.packets.add(clazz);
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */   protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, List<Object> out)
/*     */     throws Exception
/*     */   {
/*  83 */     ByteBuf buffer = Unpooled.buffer();
/*  84 */     Class clazz = msg.getClass();
/*  85 */     if (!this.packets.contains(msg.getClass())) {
/*  86 */       throw new NullPointerException("No Packet Registered for: " + msg.getClass().getCanonicalName());
/*     */     }
/*     */ 
/*  89 */     byte discriminator = (byte)this.packets.indexOf(clazz);
/*  90 */     buffer.writeByte(discriminator);
/*  91 */     msg.encodeInto(ctx, buffer);
/*  92 */     FMLProxyPacket proxyPacket = new FMLProxyPacket(buffer.copy(), (String)ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get());
/*     */ 
/*  94 */     out.add(proxyPacket);
/*     */   }
/*     */ 
/*     */   protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out)
/*     */     throws Exception
/*     */   {
/* 100 */     ByteBuf payload = msg.payload();
/* 101 */     byte discriminator = payload.readByte();
/* 102 */     Class clazz = (Class)this.packets.get(discriminator);
/* 103 */     if (clazz == null) {
/* 104 */       throw new NullPointerException("No packet registered for discriminator: " + discriminator);
/*     */     }
/*     */ 
/* 107 */     AbstractPacket pkt = (AbstractPacket)clazz.newInstance();
/* 108 */     pkt.decodeInto(ctx, payload.slice());
/*     */     EntityPlayer player;
/* 111 */     switch (2.$SwitchMap$cpw$mods$fml$relauncher$Side[cpw.mods.fml.common.FMLCommonHandler.instance().getEffectiveSide().ordinal()]) {
/*     */     case 1:
/* 113 */       player = getClientPlayer();
/* 114 */       pkt.handleClientSide(player);
/* 115 */       break;
/*     */     case 2:
/* 118 */       INetHandler netHandler = (INetHandler)ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
/* 119 */       player = ((NetHandlerPlayServer)netHandler).field_147369_b;
/* 120 */       pkt.handleServerSide(player);
/* 121 */       break;
/*     */     }
/*     */ 
/* 126 */     out.add(pkt);
/*     */   }
/*     */ 
/*     */   public void initialise()
/*     */   {
/* 131 */     this.channels = NetworkRegistry.INSTANCE.newChannel(this.channelName, new ChannelHandler[] { this });
/*     */   }
/*     */ 
/*     */   public void postInitialise()
/*     */   {
/* 138 */     if (this.isPostInitialised) {
/* 139 */       return;
/*     */     }
/*     */ 
/* 142 */     this.isPostInitialised = true;
/* 143 */     Collections.sort(this.packets, new Comparator()
/*     */     {
/*     */       public int compare(Class<? extends AbstractPacket> clazz1, Class<? extends AbstractPacket> clazz2)
/*     */       {
/* 147 */         int com = String.CASE_INSENSITIVE_ORDER.compare(clazz1.getCanonicalName(), clazz2.getCanonicalName());
/* 148 */         if (com == 0) {
/* 149 */           com = clazz1.getCanonicalName().compareTo(clazz2.getCanonicalName());
/*     */         }
/*     */ 
/* 152 */         return com;
/*     */       } } );
/*     */   }
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   private EntityPlayer getClientPlayer() {
/* 159 */     return Minecraft.func_71410_x().field_71439_g;
/*     */   }
/*     */ 
/*     */   public void sendToAll(AbstractPacket message)
/*     */   {
/* 170 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
/*     */ 
/* 172 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).writeAndFlush(message);
/*     */   }
/*     */ 
/*     */   public void sendTo(AbstractPacket message, EntityPlayerMP player)
/*     */   {
/* 184 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
/*     */ 
/* 186 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
/* 187 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).writeAndFlush(message);
/*     */   }
/*     */ 
/*     */   public void sendToAllAround(AbstractPacket message, NetworkRegistry.TargetPoint point)
/*     */   {
/* 200 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
/*     */ 
/* 202 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
/* 203 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).writeAndFlush(message);
/*     */   }
/*     */ 
/*     */   public void sendToDimension(AbstractPacket message, int dimensionId)
/*     */   {
/* 215 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
/*     */ 
/* 217 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(Integer.valueOf(dimensionId));
/* 218 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).writeAndFlush(message);
/*     */   }
/*     */ 
/*     */   public void sendToServer(AbstractPacket message)
/*     */   {
/* 229 */     ((FMLEmbeddedChannel)this.channels.get(Side.CLIENT)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
/*     */ 
/* 231 */     ((FMLEmbeddedChannel)this.channels.get(Side.CLIENT)).writeAndFlush(message);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\【1.7.10】矿物追踪MOD.jar
 * Qualified Name:     mickkay.scenter.net.PacketPipeline
 * JD-Core Version:    0.6.0
 */