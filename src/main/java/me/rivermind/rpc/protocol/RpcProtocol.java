package me.rivermind.rpc.protocol;

/**
 * @author river
 * @date 2019-04-14
 */
public interface RpcProtocol {

    String genCallId();

    String encodeCall(MethodCall methodCall);

    MethodCall decodeCall(String msg);

    String encodeResult(MethodResult result);

    MethodResult decodeResult(String msg);

}
