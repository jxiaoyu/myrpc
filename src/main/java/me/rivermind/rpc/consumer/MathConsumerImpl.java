package me.rivermind.rpc.consumer;

import me.rivermind.rpc.client.NetworkClient;
import me.rivermind.rpc.container.ResultContainer;
import me.rivermind.rpc.MathService;
import me.rivermind.rpc.protocol.MethodCall;
import me.rivermind.rpc.protocol.RpcProtocol;

import javax.annotation.Resource;

/**
 * @author river
 * @date 2019-04-13
 */
public class MathConsumerImpl implements MathService {

    @Resource
    private NetworkClient networkClient;
    @Resource
    private ResultContainer resultContainer;
    @Resource
    private RpcProtocol rpcProtocol;

    @Override
    public Integer plus(Integer a, Integer b) {
        String callId = rpcProtocol.genCallId();
        String msg = toMsg(callId, a, b);

        resultContainer.put(callId);
        networkClient.sendMsg(msg);
        try {
            callId.wait();
        } catch (InterruptedException e) {
            // todo river exception
        }
        return (Integer)resultContainer.getResult(callId);
    }

    private String toMsg(String msgId, int a, int b) {
        MethodCall methodCall = new MethodCall();
        methodCall.setCallId(msgId);

        methodCall.setClassName("me.rivermind.rpc.MathService");
        methodCall.setMethodName("plus");

        Object[] args = new Object[2];
        args[0] = a;
        args[1] = b;

        methodCall.setArgs(args);

        return rpcProtocol.encodeCall(methodCall);
    }

}
