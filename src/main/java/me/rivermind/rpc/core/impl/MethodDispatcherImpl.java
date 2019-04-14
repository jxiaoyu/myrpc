package me.rivermind.rpc.core.impl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import me.rivermind.rpc.client.NetworkClient;
import me.rivermind.rpc.core.MethodDispatcher;
import me.rivermind.rpc.protocol.MethodCall;
import me.rivermind.rpc.protocol.MethodResult;
import me.rivermind.rpc.protocol.RpcProtocol;
import me.rivermind.rpc.provider.MathProviderImpl;

import javax.annotation.Resource;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author river
 * @date 2019-04-14
 */
public class MethodDispatcherImpl implements MethodDispatcher {

    private static ExecutorService executor = new ThreadPoolExecutor(10, 50,
        0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10),
        new ThreadFactoryBuilder().setNameFormat("method-call-thread-pool-%d").build());

    @Resource
    private NetworkClient networkClient;
    @Resource
    private RpcProtocol protocol;

    @Override
    public void dispatch(MethodCall methodCall) {
        executor.submit(() -> dispatchCall(methodCall));
    }

    private void dispatchCall(MethodCall methodCall) {
        // todo river 用反射
        MathProviderImpl mathProvider = new MathProviderImpl();
        int result = mathProvider.plus((Integer) methodCall.getArgs()[0], (Integer) methodCall.getArgs()[1]);

        MethodResult methodResult = new MethodResult();
        methodResult.setCallId(methodCall.getCallId());
        methodResult.setResult(result);

        String msg = protocol.encodeResult(methodResult);
        networkClient.sendMsg(msg);
    }
}
