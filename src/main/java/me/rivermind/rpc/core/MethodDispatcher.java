package me.rivermind.rpc.core;

import me.rivermind.rpc.protocol.MethodCall;

/**
 * @author river
 * @date 2019-04-14
 */
public interface MethodDispatcher {

    /**
     * dispatch
     *
     * @param methodCall
     */
    void dispatch(MethodCall methodCall);
}
