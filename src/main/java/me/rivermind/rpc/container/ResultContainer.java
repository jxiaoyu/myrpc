package me.rivermind.rpc.container;

/**
 * @author river
 * @date 2019-04-13
 */
public interface ResultContainer {

    void put(String id);

    void putResult(String id, Object result);

    Object getResult(String id);


}
