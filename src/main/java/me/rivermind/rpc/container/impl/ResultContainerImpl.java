package me.rivermind.rpc.container.impl;

import me.rivermind.rpc.container.ResultContainer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author river
 * @date 2019-04-13
 */
public class ResultContainerImpl implements ResultContainer {

    private Map<String, Object> map = new ConcurrentHashMap<String, Object>();

    public void put(String id) {
        map.put(id, null);
    }

    public void putResult(String id, Object result) {
        map.put(id, result);
    }

    public Object getResult(String id) {
        return map.get(id);
    }
}
