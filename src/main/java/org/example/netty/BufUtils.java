package org.example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.internal.StringUtil;

/**
 * @auther wendongchao
 * @date 2023/5/30 21:00
 **/
public class BufUtils {
    // 打印ByteBuf中数据的方法
    public static void printBuffer(ByteBuf buffer) {
        // 读取ByteBuffer已使用的字节数
        int byteSize = buffer.readableBytes();
        // 基于byteSize来计算显示的行数
        int rows = byteSize / 16 + (byteSize % 15 == 0 ? 0 : 1) + 4;
        // 创建一个StringBuilder用来显示输出
        StringBuilder sb = new StringBuilder(rows * 80 * 2);
        // 获取缓冲区的容量、读/写指针信息放入StringBuilder
        sb.append("ByteBuf缓冲区信息：{");
        sb.append("读取指针=").append(buffer.readerIndex()).append(", ");
        sb.append("写入指针=").append(buffer.writerIndex()).append(", ");
        sb.append("容量大小=").append(buffer.capacity()).append("}");

        // 利用Netty框架自带的格式化方法、Dump方法输出缓冲区数据
        sb.append(StringUtil.NEWLINE);
        ByteBufUtil.appendPrettyHexDump(sb, buffer);
        System.out.println(sb.toString());
    }

}
