package org.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @auther wendongchao
 * @date 2023/5/30 18:14
 **/
// 服务端
public class HandlerServer {
    public static void main(String[] args) {
        // 0.准备工作：创建一个事件循环组、一个ServerBootstrap服务端
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();

        server
                // 1.绑定前面创建的事件循环组
                .group(group)
                // 2.声明通道类型为服务端NIO通道
                .channel(NioServerSocketChannel.class)
                // 3.通过ChannelInitializer完成通道的初始化工作
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nsc) throws Exception {
                        // 4.获取通道的ChannelPipeline处理器链表
                        ChannelPipeline pipeline = nsc.pipeline();
                        // 5.基于pipeline链表向通道上添加入站处理器
                        pipeline.addLast("In-①",new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg)
                                    throws Exception {
                                System.out.println("俺是第一个入站处理器...");
                                super.channelRead(ctx, msg);
                            }
                        });
                        pipeline.addLast("In-②",new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg)
                                    throws Exception {
                                System.out.println("我是第二个入站处理器...");
                                super.channelRead(ctx, msg);
                            }
                        });
                        pipeline.addLast("In-③",new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg)
                                    throws Exception {
                                System.out.println("朕是第三个入站处理器...");
                                ByteBuf buffer = ctx.channel().alloc().buffer();
                                buffer.writeBytes("朕收到了你的消息！".getBytes());
                                nsc.writeAndFlush(buffer);
                            }
                        });
                        // 基于pipeline链表向通道上添加出站处理器
                        pipeline.addLast("Out-A",new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)
                                    throws Exception {
                                System.out.println("在下是Out-A出站处理器...");
                                super.write(ctx, msg, promise);
                            }
                        });
                        pipeline.addLast("Out-B",new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)
                                    throws Exception {
                                System.out.println("鄙人是Out-B出站处理器...");
                                super.write(ctx, msg, promise);
                            }
                        });
                        pipeline.addLast("Out-C",new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)
                                    throws Exception {
                                System.out.println("寡人是Out-C出站处理器...");
                                super.write(ctx, msg, promise);
                            }
                        });
                    }
                })
                // 为当前启动的服务端绑定IP和端口地址
                .bind("127.0.0.1",8888);
    }
}

