package org.example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @auther wendongchao
 * @date 2023/5/30 20:46
 **/
public class ByteBufPoolTest {

    public static void main(String[] args) {
//        byteBufferIsPooled();
        byteBufCapacityExpansion();
    }

    // 查看创建的缓冲区是否使用了池化技术
    private static void byteBufferIsPooled(){
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(16);
        System.out.println(buffer.getClass());
    }

    // 测试Netty-ByteBuf自动扩容机制
    private static void byteBufCapacityExpansion() {
        // 不指定默认容量大小为16
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(16);
        System.out.println("测试前的Buffer容量：" + buffer);
        // 使用StringBuffer来测试ByteBuf的自动扩容特性
        StringBuffer sb = new StringBuffer();
        // 往StringBuffer中插入17个字节的数据
        for (int i = 0; i < 17; i++) {
            sb.append("6");
        }
        // 将17个字节大小的数据写入缓冲区
        buffer.writeBytes(sb.toString().getBytes());
        BufUtils.printBuffer(buffer);
    }


}
