package me.rivermind.rpc.client;

/**
 * @author river
 * @date 2019-04-13
 */
public interface NetworkClient {

    /**
     * send msg
     *
     * @param msg
     */
    void sendMsg(String msg);

}
