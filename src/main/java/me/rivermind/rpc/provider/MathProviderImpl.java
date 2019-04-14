package me.rivermind.rpc.provider;

import me.rivermind.rpc.MathService;

/**
 * @author river
 * @date 2019-04-13
 */
public class MathProviderImpl implements MathService {

    @Override
    public Integer plus(Integer a, Integer b) {
        return a + b;
    }
}
