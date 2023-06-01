package org.example.netty.adhesive;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @auther wendongchao
 * @date 2023/6/1 16:27
 **/
// 演示数据粘包问题的服务端
public class AdhesivePackageServer {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();

        server.group(group);
        server.channel(NioServerSocketChannel.class);
        server.childHandler(new ServerInitializer());

        server.bind("127.0.0.1",8888);
    }
}
