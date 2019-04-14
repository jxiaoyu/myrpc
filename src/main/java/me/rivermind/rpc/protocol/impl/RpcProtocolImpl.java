package me.rivermind.rpc.protocol.impl;

import com.alibaba.fastjson.JSON;
import me.rivermind.rpc.protocol.MethodCall;
import me.rivermind.rpc.protocol.MethodResult;
import me.rivermind.rpc.protocol.RpcProtocol;

/**
 * @author river
 * @date 2019-04-14
 */
public class RpcProtocolImpl implements RpcProtocol {

    @Override
    public String genCallId() {
        return System.currentTimeMillis() + "#" + "MathService.plus";
    }

    @Override
    public String encodeCall(MethodCall methodCall) {
        return JSON.toJSONString(methodCall);
    }

    @Override
    public MethodCall decodeCall(String msg) {
        return JSON.parseObject(msg, MethodCall.class);
    }

    @Override
    public String encodeResult(MethodResult result) {
        return JSON.toJSONString(result);
    }

    @Override
    public MethodResult decodeResult(String msg) {
        return JSON.parseObject(msg, MethodResult.class);
    }

}
