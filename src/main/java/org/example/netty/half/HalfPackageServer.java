package org.example.netty.half;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.example.netty.adhesive.ServerInitializer;

/**
 * @auther wendongchao
 * @date 2023/6/1 16:37
 **/
// 演示半包问题的服务端
public class HalfPackageServer {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();

        server.group(group);
        server.channel(NioServerSocketChannel.class);
        // 调整服务端的接收窗口大小为四字节
        server.option(ChannelOption.SO_RCVBUF,4);
        server.childHandler(new ServerInitializer());
        server.bind("127.0.0.1",8888);
    }
}
