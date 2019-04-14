package me.rivermind.rpc.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import me.rivermind.rpc.core.MethodDispatcher;
import me.rivermind.rpc.protocol.MethodCall;
import me.rivermind.rpc.protocol.RpcProtocol;

import javax.annotation.Resource;

/**
 * @author river
 * @date 2019-04-13
 */
public class RpcMsgHandler extends ChannelInboundHandlerAdapter {

    @Resource
    private RpcProtocol protocol;
    @Resource
    private MethodDispatcher dispatcher;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        StringBuilder sb = new StringBuilder();
        try {
            while (in.isReadable()) {
                sb.append((char)in.readableBytes());
            }
            MethodCall methodCall = protocol.decodeCall(sb.toString());
            dispatcher.dispatch(methodCall);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}