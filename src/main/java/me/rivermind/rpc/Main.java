package me.rivermind.rpc;

import me.rivermind.rpc.consumer.MathConsumerImpl;

/**
 * @author river
 * @date 2019-04-13
 */
public class Main {

    public static void main(String[] args) {
        MathConsumerImpl proxy = new MathConsumerImpl();
        System.out.println(proxy.plus(333, 4444));
    }
}
